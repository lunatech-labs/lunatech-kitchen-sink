package controllers

import com.google.inject.Inject
import com.lunatech.openconnect.Authenticate
import play.api.mvc.{Action, Controller}
import play.api.{Configuration, Environment, Mode}

import scala.concurrent.{ExecutionContext, Future}

class Authentication @Inject()(val environment: Environment,
                               val configuration: Configuration,
                               auth: Authenticate)(implicit ec: ExecutionContext) extends Controller with Secured {

  /**
    * Login page.
    */
  def login = Action { implicit request =>
    if (environment.mode == Mode.Dev) {
      val clientId = configuration.getString("google.clientId").get
      val state = auth.generateState
      Ok(views.html.login(clientId, state)(request.flash)).withSession("state" -> state)
    } else {
      Redirect(routes.Application.index()).withSession("email" -> "developer@lunatech.com")
    }
  }

  /**
    * OAuth callback
    */
  def authenticate(code: String, idToken: String, accessToken: String, state: String) = Action.async { implicit request =>
    if (request.session.get("state").getOrElse("") == state)
      auth.authenticateToken(code, idToken, accessToken).map {
        case Left(parameters) => Redirect(routes.Application.index()).withSession(parameters.toArray: _*)
        case Right(message) => Redirect(routes.Authentication.login()).withNewSession.flashing("error" -> message.toString())
      } else {
      Future.successful(Redirect(routes.Authentication.login()).withNewSession.flashing("error" -> "Invalid state"))
    }
  }

  /**
    * Logout and clean the session.
    */
  def logout = Action {
    Redirect(routes.Authentication.login()).withNewSession.flashing("success" -> "You've been logged out")
  }

}
