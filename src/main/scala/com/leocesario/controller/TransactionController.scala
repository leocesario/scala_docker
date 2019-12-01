package com.leocesario.controller

import com.leocesario.monad.Just
import org.scalatra.{Get, Post}

class TransactionController extends BaseController {

  action(Get, "/"){ implicit context =>
    Test("testing", 3)
    info(s"Testing.....$requestPath")
    //halt(status = 500,TestJson("error",1))

    Just(Test("testing", 3))
  }

  actionWithBody(Post, "/"){ t: Test => { implicit context =>
      info(s"Testing.... $t")
      Just(Test("a", 3))
    }
  }

  action(Get, "/s") { implicit context =>
    Just(Test("anotherService", 10))
  }

  action(Get,"/a") { implicit context =>
    Just(Test("anotherService", 10))
  }

  action(Get, "/d") { implicit context =>
    Just(Test("anotherService", 10))
    //notFound{ TestJson("Not Found", 404)}

    info(s"Request path $requestPath")
    Just(Test("Not Found", 404))
  }

}

case class Test(value: String, number: Int)
