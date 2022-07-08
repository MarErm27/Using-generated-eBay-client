package controllers

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.test.Helpers.{await, defaultAwaitTimeout}
import play.api.test.Helpers._

class EbayClientTest extends PlaySpec with GuiceOneServerPerSuite with ScalaFutures with IntegrationPatience {
  val ebayClient = app.injector.instanceOf(classOf[EbayClient])
  "AdyenClientTest" must {
    "capture" in {
      val res = await(ebayClient.getOrders(""))
      res shouldBe "???"
    }
  }

}
