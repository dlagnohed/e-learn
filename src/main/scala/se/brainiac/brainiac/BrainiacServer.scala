package se.brainiac.brainiac

import cats.effect.{Effect, IO}
import ciris.env
import fs2.StreamApp
import org.http4s.HttpService
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext

object BrainiacServer extends StreamApp[IO] {
  import scala.concurrent.ExecutionContext.Implicits.global

  def stream(args: List[String], requestShutdown: IO[Unit]): fs2.Stream[IO, StreamApp.ExitCode] = ServerStream.stream[IO]
}

object ServerStream {

  def brainiacService[F[_]: Effect]: HttpService[F] = new BrainiacService[F].service

  def stream[F[_]: Effect](implicit ec: ExecutionContext): fs2.Stream[F, StreamApp.ExitCode] =
    BlazeBuilder[F]
      .bindHttp(env[Int]("PORT").value.getOrElse(8080), "0.0.0.0")
      .mountService(brainiacService, "/")
      .serve
}
