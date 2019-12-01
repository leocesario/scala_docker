package com.leocesario.controller

import javax.servlet.http.HttpServletRequest
import com.leocesario.logging.Logging
import com.leocesario.monad.Maybe
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.{ActionResult, HttpMethod, Route, RouteTransformer, ScalatraServlet}

import scala.jdk.CollectionConverters._

trait BaseController extends ScalatraServlet
                        with JacksonJsonSupport
                        with ResultHandler
                        with Logging {

  protected implicit val jsonFormats: Formats = DefaultFormats.withBigDecimal

  def action(method: HttpMethod, transformer: RouteTransformer)
               (someAction: Context => Maybe[_]): Route = {
    addRoute(method, transformer :: Nil, { executeRequest(someAction)})
  }

  def actionWithBody[T : Manifest](method: HttpMethod, transformer: RouteTransformer)
               (someAction: T => Context => Maybe[_]): Route = {
    addRoute(method, transformer :: Nil, { executeRequest(someAction(parsedBody.extract[T]))})
  }

  protected def executeRequest(someAction: Context => Maybe[_])(implicit req: HttpServletRequest): ActionResult = {
    val headers = req.getHeaderNames.asScala.toList.map{ headerName => headerName -> req.getHeader(headerName)}.toMap
    implicit val ctx: Context = Context(headers, requestPath(req))
    val currentMillis = System.currentTimeMillis()
    info(s"Request arrived")
    val result = someAction(ctx)
    info(s"Request-Time ${System.currentTimeMillis() - currentMillis}ms")
    debug(s"Request-Result $result")
    result
  }

  before() {
    contentType = formats("json")
  }

  after(){
  }
}