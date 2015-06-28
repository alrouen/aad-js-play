package controllers

import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.mvc._

import service.aad.AadTokenService

/**
 * Created by alain on 28/06/15.
 */
trait AadAuthentication extends Controller {

  class AadUserRequest[A](val username: String, request: Request[A]) extends WrappedRequest[A](request)

  object AadUserAction extends ActionBuilder[AadUserRequest] {
    def invokeBlock[A](request: Request[A], block: (AadUserRequest[A]) => Future[Result]) = {

      request.headers.get("Authorization") match {
        case Some(authorizationHeader) => {

          val jwt = authorizationHeader.substring(7) //To remove the "Bearer" prefix

          AadTokenService.jwtToClaims(jwt).flatMap { _.fold(

            //TODO: manage in a better way service errors
            authError => {
              Logger.error(s"token service returned an error: ${authError.error}")
              Future.successful(Unauthorized)
            },

            //TODO: so far we only use upn from claims...
            claims => {
              block(new AadUserRequest(claims.getStringClaimValue("upn"), request))
            }
          )}

        }
        case None => Future.successful(Unauthorized)
      }

    }
  }

}
