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
      println("Accessing to " + daas)
      val response = rest.getForEntity(s"${daas}/appointments?id=${id}", classOf[AppointmentList])
      Some(response.getBody)
    }
    case _ => None
  }
}
