
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(/*7.17*/title),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css")),format.raw/*8.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*9.59*/routes/*9.65*/.Assets.at("images/favicon.png")),format.raw/*9.97*/("""">
        <script src=""""),_display_(/*10.23*/routes/*10.29*/.Assets.at("javascripts/hello.js")),format.raw/*10.63*/("""" type="text/javascript"></script>
    </head>
    <body>
        """),_display_(/*13.10*/content),format.raw/*13.17*/("""
    """),format.raw/*14.5*/("""</body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


}

/**/
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Sun Jun 28 14:38:32 CEST 2015
                  SOURCE: /Users/alain/Dev/alrouen/aad-js-play/play-webapi/app/views/main.scala.html
                  HASH: c161d310eb555488191ec9bade5d744477a19257
                  MATRIX: 530->1|655->31|683->33|760->84|785->89|873->151|887->157|941->191|1028->252|1042->258|1094->290|1146->315|1161->321|1216->355|1310->422|1338->429|1370->434
                  LINES: 20->1|25->1|27->3|31->7|31->7|32->8|32->8|32->8|33->9|33->9|33->9|34->10|34->10|34->10|37->13|37->13|38->14
                  -- GENERATED --
              */
          