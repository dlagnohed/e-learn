package se.brainiac.brainiac

import cats.effect.Effect
import org.http4s.dsl.Http4sDsl
import org.http4s.{HttpService, _}

class BrainiacService[F[_]: Effect] extends Http4sDsl[F] {

  def static(file: String, request: Request[F]): F[Response[F]] = StaticFile.fromResource(s"/$file", Some(request)).getOrElseF(NotFound())

  val service: HttpService[F] = HttpService[F] {
    case request @ GET -> "static" /: path => StaticFile.fromResource(path.toString, Some(request)).getOrElseF(NotFound())
    case request @ GET -> Root / path if List(".js", ".css", ".map", ".html", ".webm", ".pdf").exists(path.endsWith) => static(path, request)
    case request @ GET -> Root  => static("index.html", request)
  }
}
