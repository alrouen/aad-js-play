
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/alain/Dev/alrouen/aad-js-play/play-webapi/conf/routes
// @DATE:Sun Jun 28 23:38:48 CEST 2015

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

object Routes extends Routes

class Routes extends GeneratedRouter {

  import ReverseRouteContext.empty

  override val errorHandler: play.api.http.HttpErrorHandler = play.api.http.LazyHttpErrorHandler

  private var _prefix = "/"

  def withPrefix(prefix: String): Routes = {
    _prefix = prefix
    router.RoutesPrefix.setPrefix(prefix)
    
    this
  }

  def prefix: String = _prefix

  lazy val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation: Seq[(String, String, String)] = List(
    ("""GET""", prefix, """controllers.Application.index"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sayHello""", """controllers.Application.sayHello"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """groups""", """controllers.Application.groups"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """memberOf""", """controllers.Application.memberOf"""),
    ("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""", """controllers.Assets.at(path:String = "/public", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_Application_index0_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_Application_index0_invoker = createInvoker(
    controllers.Application.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "index",
      Nil,
      "GET",
      """ Home page""",
      this.prefix + """"""
    )
  )

  // @LINE:7
  private[this] lazy val controllers_Application_sayHello1_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHello")))
  )
  private[this] lazy val controllers_Application_sayHello1_invoker = createInvoker(
    controllers.Application.sayHello,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "sayHello",
      Nil,
      "GET",
      """""",
      this.prefix + """sayHello"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_Application_groups2_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("groups")))
  )
  private[this] lazy val controllers_Application_groups2_invoker = createInvoker(
    controllers.Application.groups,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "groups",
      Nil,
      "GET",
      """""",
      this.prefix + """groups"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Application_memberOf3_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("memberOf")))
  )
  private[this] lazy val controllers_Application_memberOf3_invoker = createInvoker(
    controllers.Application.memberOf,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Application",
      "memberOf",
      Nil,
      "GET",
      """""",
      this.prefix + """memberOf"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_Assets_at4_route: Route.ParamsExtractor = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
    controllers.Assets.at(fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/$file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_Application_index0_route(params) =>
      call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
      }
  
    // @LINE:7
    case controllers_Application_sayHello1_route(params) =>
      call { 
        controllers_Application_sayHello1_invoker.call(controllers.Application.sayHello)
      }
  
    // @LINE:8
    case controllers_Application_groups2_route(params) =>
      call { 
        controllers_Application_groups2_invoker.call(controllers.Application.groups)
      }
  
    // @LINE:9
    case controllers_Application_memberOf3_route(params) =>
      call { 
        controllers_Application_memberOf3_invoker.call(controllers.Application.memberOf)
      }
  
    // @LINE:12
    case controllers_Assets_at4_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at4_invoker.call(controllers.Assets.at(path, file))
      }
  }
}