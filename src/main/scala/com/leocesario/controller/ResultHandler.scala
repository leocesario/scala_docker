package com.leocesario.controller

import com.leocesario.monad._
import org.scalatra.{ActionResult, InternalServerError, NoContent, Ok}

trait ResultHandler {

  implicit def asResult(maybe: Maybe[_])(implicit ctx: Context): ActionResult = {
    maybe match {
      case Just(()) => NoContent(ctx.headers)
      case Just(x) => Ok(x,ctx.headers)
      case _ => InternalServerError(headers = ctx.headers)
    }
  }

}
