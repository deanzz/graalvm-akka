package graalvm.akka.actors

import akka.actor.Actor
import graalvm.akka.actors.PingMan.Ping
import graalvm.akka.actors.PongMan.Pong

class PongMan() extends Actor{
  override def receive: Receive = {
    case Ping(n) =>
      val res = (0 to 500000).flatMap(i => fibSeq(i.toLong)).sum
      sender() ! Pong(res.toString)
  }

  def fibSeq(n: Long): List[Long] = {
    var ret = scala.collection.mutable.ListBuffer[Long](1, 2)
    while (ret.last < n) {
      val temp = ret.last + ret(ret.length - 2)
      if (temp >= n) {
        return ret.toList
      }
      ret += temp
    }
    ret.toList
  }
}

object PongMan{
  case class Pong(s: String)
}
