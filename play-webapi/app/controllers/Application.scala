package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import play.api.mvc._
import play.api.libs.json.Json

object Application extends AadAuthentication {

  def index = Action {
    Ok("index")
  }

  def sayHello = AadUserAction.async { request =>
    Future.successful( Ok( Json.obj( "username" -> request.username ) ) )
  }

  def groups = AadUserAction.async { request =>
    service.aad.AadGraphApi.groups.map { groups =>
      Ok( Json.obj("groups" -> groups) )
    }
  }

  def memberOf = AadUserAction.async { request =>
    service.aad.AadGraphApi.memberOf(request.username).map { memberOf =>
      Ok( Json.obj("memberOf" -> memberOf) )
    }
  }

}
