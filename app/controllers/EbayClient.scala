package controllers

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.stream.Materializer
import cats.data.EitherT
import com.google.inject.Inject
import sell.fulfillment.order.GetOrdersResponse

import scala.concurrent.{ExecutionContext, Future}

class EbayClient @Inject()()(implicit ec: ExecutionContext) {
  implicit val system = ActorSystem()
  implicit val mat: Materializer = Materializer(system)
  import system.dispatcher

  // https://developer.ebay.com/api-docs/sell/fulfillment/resources/order/methods/getOrders
  def getOrders(userToken: String): Future[GetOrdersResponse] = {
    implicit val client = Http().singleRequest(_)
    val orderClient = sell.fulfillment.order.OrderClient.httpClient(client)(ec, mat)
    orderClient.getOrders()
  }
}