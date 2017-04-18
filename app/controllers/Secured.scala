package controllers

import com.lunatech.openconnect.GoogleSecured
import play.api.mvc.{RequestHeader, Result, Results}

trait Secured extends GoogleSecured {

  override def onUnauthorized(request: RequestHeader): Result = Results.Redirect(routes.Authentication.login())

  override def onForbidden(request: RequestHeader): Result = Results.Redirect(routes.Application.index()).flashing("error" -> "Not allowed!")
}
