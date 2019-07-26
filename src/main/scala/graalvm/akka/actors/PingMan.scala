package graalvm.akka.actors

import akka.actor.{Actor, ActorRef}
import graalvm.akka.actors.PingMan.Ping
import akka.pattern._
import akka.util.Timeout
import scala.concurrent.duration.DurationInt

class PingMan(pongMan: ActorRef) extends Actor{
  implicit val ec = context.dispatcher
  implicit val timeout = Timeout(10.seconds)
  override def receive: Receive = {
    case p@Ping(n) => (pongMan ? p).pipeTo(sender())
  }
}

object PingMan{
  case class Ping(n: Long)
}