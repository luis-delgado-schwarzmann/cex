package com.stratio.cex.processor.daas

import com.stratio.cex.discovery.DiscoveryService
import com.stratio.cex.response.AppointmentList
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class AppointmentProcessor {

  @Autowired
  val rest: RestTemplate = null

  @Autowired
  val discoveryService: DiscoveryService = null

  @Value("${dependencies.daas.appointment}")
  val daas: String = null

  def getAll(id: String): Option[AppointmentList] = id match {
    case x if !x.isEmpty => {
      val instance = discoveryService.discover(daas)
      instance match {
        case Some(x) => {
          println("Accessing to " + x.getUri)
          val response = rest.getForEntity(s"${x.getUri}/appointments?id=${id}", classOf[AppointmentList])
          Some(response.getBody)
        }
        case _ => {
          println(s"No service ${daas} found in consul ")
          None
        }
      }
    }
    case _ => None
  }
}
