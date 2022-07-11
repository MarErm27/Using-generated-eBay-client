package controllers

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.Materializer
import cats.data.EitherT
import com.google.inject.Inject
import sell.fulfillment.order.GetOrdersResponse
import scala.concurrent.{ExecutionContext, Future}

class EbayClient @Inject()()(implicit ec: ExecutionContext) {
  implicit val system = ActorSystem()
  implicit val mat: Materializer = Materializer(system)
  import system.dispatcher
  val singleRequestHttpClient = { (req: HttpRequest) =>
    Http().singleRequest(req)
  }
  val retryingHttpClient = { nextClient: (HttpRequest => Future[HttpResponse]) =>
    req: HttpRequest => nextClient(req).flatMap(resp => if (resp.status.intValue >= 500) nextClient(req) else Future.successful(resp))
  }

  // https://developer.ebay.com/api-docs/sell/fulfillment/resources/order/methods/getOrders
  def getOrders(userToken: String): EitherT[Future, Either[Throwable, HttpResponse], GetOrdersResponse] = {
    implicit val client = singleRequestHttpClient
    val orderClient = sell.fulfillment.order.OrderClient.httpClient(client)(ec, mat)
    orderClient.getOrders()
  }
}