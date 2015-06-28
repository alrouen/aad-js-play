package service.aad

import org.jose4j.jwt.JwtClaims
import org.jose4j.jwt.consumer.JwtConsumerBuilder
import org.jose4j.keys.resolvers.X509VerificationKeyResolver


import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import java.security.cert.X509Certificate
import org.jose4j.keys.X509Util

import play.api.Play.current
import play.api.libs.json.Json
import play.api.libs.ws._

/**
 * Created by alain on 28/06/15.
 */

//Todo: 1) use caching to store Public keys for something like few minute to avoid too many request against the Azure Open ID configuration service
//Todo: 2) manage errors from remote web service
object AadTokenService {

  private val x509 = new X509Util()

  case class AuthenticationError(error: String)

  private case class AadOpenIdConfiguration(issuer: String, authorization_endpoint: String, token_endpoint: String, jwks_uri: String)
  private object AadOpenIdConfiguration { implicit val aadOpenIdConfigurationFormat = Json.format[AadOpenIdConfiguration]}

  private case class AadKey(kty: String, use: String, kid: String, x5t: String, n: String, e: String, x5c: List[String])
  private object AadKey { implicit val aadKey = Json.format[AadKey]}

  private def getJWTPublicKeys: Future[List[AadKey]] = {

    for {
      authorityConf <- WS.url(AadConfiguration.authorityUrlOpenIdConfiguration).get().map { r => r.json.as[AadOpenIdConfiguration] }
      authorityPubKeys <- WS.url(authorityConf.jwks_uri).get.map { r =>
        (r.json \ "keys").as[List[AadKey]]
      }
    } yield {
      authorityPubKeys
    }

  }

  def jwtToClaims(jwt: String):Future[Either[AuthenticationError, JwtClaims]] = {

    getJWTPublicKeys.map { publicKeys =>
      val publicCerts = publicKeys.foldLeft(List[X509Certificate]())( (certs, aadKey) => {
        aadKey.x5c.map { x509.fromBase64Der(_) } ::: certs
      })

      val certResolver = new X509VerificationKeyResolver(publicCerts)
      val jwtConsumer = new JwtConsumerBuilder()
        .setVerificationKeyResolver(certResolver)
        .setRequireExpirationTime()
        .setAllowedClockSkewInSeconds(30)
        .setRequireSubject()
        .setExpectedAudience(AadConfiguration.audience:_*)
        .build()

      try {
        val jwtClaims = jwtConsumer.processToClaims(jwt)
        Right(jwtClaims)
      } catch {
        case e:Throwable => {
          Left(AuthenticationError(e.toString))
        }
      }
    }

  }

}
