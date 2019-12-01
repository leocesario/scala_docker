package com.leocesario.logging

import com.leocesario.controller.Context
import org.slf4j.LoggerFactory

trait Logging {

  private lazy val LOGGER = LoggerFactory.getLogger(this.getClass)

  protected def info(message: => String)(implicit ctx: Context): Unit = LOGGER.info(createMessage(message))

  protected def warn(message: => String)(implicit ctx: Context): Unit = LOGGER.warn(createMessage(message))

  protected def debug(message: => String)(implicit ctx: Context): Unit = LOGGER.debug(createMessage(message))

  protected def error(message: => String)(implicit ctx: Context): Unit = LOGGER.error(createMessage(message))

  protected def error(message: => String, exception: Throwable)(implicit ctx: Context): Unit = LOGGER.error(createMessage(message), exception)

  protected def createMessage(message:  => String)(implicit ctx: Context): String = {
    s"[path:${ctx.path} | req:${ctx.requestHash}] - $message"
  }

}
