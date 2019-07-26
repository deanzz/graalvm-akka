package graalvm.akka
import java.io.File

import akka.actor.{ActorSystem, Props}
import graalvm.akka.actors.{PingMan, PongMan}
import graalvm.akka.actors.PingMan.Ping
import graalvm.akka.actors.PongMan.Pong
import akka.pattern._
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt}

object Main {

  def main(args: Array[String]): Unit = {
    //val confPath = if (args.length > 0) args(0) else "./reference.conf"
    //val confFile = new File(confPath)
    //val conf = ConfigFactory.parseFile(confFile).resolve()
    val system = ActorSystem("graalvm-akka"/*, conf*/)
    val pongMan = system.actorOf(Props[PongMan], "pong-man")
    val pingMan = system.actorOf(Props(classOf[PingMan], pongMan),"ping-man")

    implicit val ec = system.dispatcher
    implicit val timeout = Timeout(10.seconds)
    val n = if (args.length == 1) args(0).toLong else 100000000
    val start = System.currentTimeMillis()
    val pong = Await.result(pingMan ? Ping(n), Duration.Inf).asInstanceOf[Pong]
    println(s"res = ${pong.s}, used ${System.currentTimeMillis() - start}ms")
    System.exit(0)
  }

}
