package utils

import play.api.http.HeaderNames
import play.api.mvc.{Result, RequestHeader, Filter}
import controllers.Default

case class CorsFilter() extends Filter with HeaderNames {

  import scala.concurrent._
  import ExecutionContext.Implicits.global

  lazy val allowedDomain = play.api.Play.current.configuration.getString("cors.allowed.domain")

  private def isPreFlight(r: RequestHeader) = r.method.toLowerCase.equals("options") && r.headers.get("Access-Control-Request-Method").nonEmpty

  private def corsHeader(implicit request: RequestHeader) = List(
    ACCESS_CONTROL_ALLOW_ORIGIN -> allowedDomain.orElse(request.headers.get("Origin")).getOrElse(""),
    ACCESS_CONTROL_ALLOW_METHODS -> request.headers.get(ACCESS_CONTROL_REQUEST_METHOD).getOrElse("*"),
    ACCESS_CONTROL_ALLOW_HEADERS -> request.headers.get(ACCESS_CONTROL_REQUEST_HEADERS).getOrElse(""),
    ACCESS_CONTROL_ALLOW_CREDENTIALS -> "true",
    ACCESS_CONTROL_MAX_AGE -> "1728000"
  )

  def apply(f: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    if (isPreFlight(request)) {
      Future.successful(Default.NoContent.withHeaders(corsHeader(request):_*))
    } else {
      f(request).map{_.withHeaders(corsHeader(request):_*)}
    }
  }
}