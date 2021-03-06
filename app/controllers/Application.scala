package controllers

import com.google.inject.Inject
import play.api.mvc.Controller
import play.api.{Configuration, Environment}

class Application @Inject()(val environment: Environment,
                            val configuration: Configuration) extends Controller with Secured {

  def index = userAction { implicit request =>
      Ok(views.html.index())
  }

  def dashboard = adminAction { implicit request =>
      Ok(views.html.dashboard())
  }
}
