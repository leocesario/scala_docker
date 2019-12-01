package com.leocesario.controller

import scala.util.Random

case class Context(headers: Map[String, String],
                   path: String,
                   requestHash: String)


object Context {

  def apply(headers: Map[String, String], requestPath: String): Context =
    Context(headers = headers,
            path = requestPath,
            requestHash = Random.alphanumeric.take(10).mkString)

}