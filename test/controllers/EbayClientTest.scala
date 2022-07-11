package controllers

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.test.Helpers._
import sell.fulfillment.order.GetOrdersResponse
import scala.concurrent.ExecutionContext.Implicits.global

class EbayClientTest extends PlaySpec with GuiceOneServerPerSuite with ScalaFutures with IntegrationPatience {

  val ebayClient = app.injector.instanceOf(classOf[EbayClient])
  "EbayClientTest" must {
    "getOrders" in {
      val res: GetOrdersResponse = await(ebayClient.getOrders("").fold(l => l, r => r))
      res shouldBe "???"
    }
  }

}
