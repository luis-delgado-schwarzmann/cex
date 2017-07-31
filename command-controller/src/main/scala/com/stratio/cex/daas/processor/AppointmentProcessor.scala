package com.stratio.cex.daas.processor

import com.stratio.cex.response.AppointmentList
import org.springframework.web.client.RestTemplate

import scala.beans.BeanProperty

case class AppointmentProcessor(@BeanProperty id: String) {
  def getAll: Option[AppointmentList] = this.id match {
    case x if !x.isEmpty => {
      val response = (new RestTemplate()).getForObject(s"http://localhost:9001/appointments?id=${id}", classOf[AppointmentList])
      Some(response)
    }
    case _ => None
  }
}
