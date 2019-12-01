
import com.leocesario.controller.TransactionController
import javax.servlet.ServletContext
import org.scalatra._

class ScalatraBootstrap extends LifeCycle {

  val BASE_PATH = "/*"

  override def init(context: ServletContext) {
    context.mount(new TransactionController, BASE_PATH)
  }

}

