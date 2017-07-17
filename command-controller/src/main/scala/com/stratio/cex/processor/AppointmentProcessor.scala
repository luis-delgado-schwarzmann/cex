package com.stratio.cex.processor

import com.stratio.cex.response.AppointmentList
import com.stratio.cex.response.{Appointment => AppointmentResponse}

import scala.beans.BeanProperty

case class AppointmentProcessor(@BeanProperty id: String) {
  def getAll: AppointmentList = {

    val responses: Array[AppointmentResponse] = this.id match {
      case x if ! x.isEmpty => (1 to 10).toArray
        .map({ x =>
          AppointmentResponse(x.toString, s"Mocked appointment for ${id}")
        })
      case _ => Array.empty[AppointmentResponse]
    }

    AppointmentList(responses)
  }
}
