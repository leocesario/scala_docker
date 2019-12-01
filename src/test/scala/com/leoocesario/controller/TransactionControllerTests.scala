package com.leoocesario.controller

import com.leocesario.controller.{Context, TransactionController}
import org.scalatra.test.scalatest._

class TransactionControllerTests extends ScalatraFunSuite {

  addServlet(classOf[TransactionController], "/*")

  //implicit val ctx = Context(Map.empty, "test")

  test("GET / on TransactionController should return status 200") {
    get("/") {
      status should equal (200)
      println(s" --------------------------------------- ${response.body}")
    }
  }

  test("GET /s on TransactionController should return status 200 with specific body") {
    get("/s"){
      status should equal (200)
      response.body should equal ("{\"value\":\"anotherService\",\"number\":10}")
    }
  }

}
