
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/alain/Dev/alrouen/aad-js-play/play-webapi/conf/routes
// @DATE:Sun Jun 28 23:38:48 CEST 2015


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
