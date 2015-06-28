package service.aad

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.json.{JsValue, Json}

/**
 * Created by alain on 28/06/15.
 */
object AadGraphApi {

  case class GraphTokenRequest(client_id:String, client_secret:String, resource: String, grant_type: String = "client_credentials")
  case class GraphTokenResponse(token_type:String, expires_in: String, expires_on: String, not_before: String, resource: String, access_token: String)
  object GraphTokenResponse { implicit val graphTokenResponseFormat = Json.format[GraphTokenResponse] }

  val payload = Map(
    "client_id" -> Seq(AadConfiguration.clientId),
    "client_secret" -> Seq(AadConfiguration.clientSecret),
    "grant_type" -> Seq("client_credentials"),
    "resource" -> Seq(AadConfiguration.GraphApi.graphUrl)
  )

  private def getAccessToken:Future[String] = {
    WS.url(AadConfiguration.GraphApi.token).post(payload).map { r => r.json.as[GraphTokenResponse].access_token }
  }

  def groups: Future[JsValue] = {
    for {
      accessToken <- getAccessToken
      groups <- WS.url(AadConfiguration.GraphApi.groups).withHeaders(("Authorization", s"Bearer ${accessToken}")).get()
    } yield {
      groups.json
    }
  }

  def memberOf(upn: String): Future[JsValue] = {
    for {
      accessToken <- getAccessToken
      groups <- WS.url(AadConfiguration.GraphApi.memberOf(upn)).withHeaders(("Authorization", s"Bearer ${accessToken}")).get()
    } yield {
      groups.json
    }
  }

}
