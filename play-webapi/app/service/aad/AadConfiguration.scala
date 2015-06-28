package service.aad

import play.api.Play
import play.api.Play.current

/**
 * Created by alain on 28/06/15.
 */
object AadConfiguration {
  lazy val tenant = Play.configuration.getString("aad.tenant").getOrElse("")
  lazy val clientId = Play.configuration.getString("aad.clientId").getOrElse("")
  lazy val clientSecret = Play.configuration.getString("aad.clientSecret").getOrElse("")
  lazy val authorityUrl = s"https://login.windows.net/${tenant}"
  lazy val authorityUrlOpenIdConfiguration = s"${authorityUrl}/.well-known/openid-configuration"
  lazy val audience = List(clientId)

  // Graph API
  object GraphApi {

    val graphUrl = "https://graph.windows.net"

    lazy val token = s"https://login.windows.net/${tenant}/oauth2/token?api-version=1.5"

    /**
     * To list all users
     * ie: https://graph.windows.net/mycloud.onmicrosoft.com/users?api-version=1.5
     */
    lazy val users: String = s"https://graph.windows.net/${tenant}/users?api-version=1.5"

    /**
     * To list all groups
     * ie : https://graph.windows.net/mycloud.onmicrosoft.com/groups?api-version=1.5
     */
    lazy val groups: String = s"https://graph.windows.net/${tenant}/groups?api-version=1.5"

    /**
     * To generate url for memberOf API request
     * ie: https://graph.windows.net/mycloud.onmicrosoft.com/users/foo.bar@mycloud.onmicrosoft.com/$links/memberOf?api-version=1.5"
     * @param upn
     * @return url: String
     */
    def memberOf(upn: String): String = s"${graphUrl}/${tenant}/users/${upn}/memberOf?api-version=1.5"

  }

}
