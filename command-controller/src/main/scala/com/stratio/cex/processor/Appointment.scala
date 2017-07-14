package com.stratio.cex.processor

import com.stratio.cex.response.AppointmentList
import com.stratio.cex.response.{Appointment => AppointmentResponse}

import scala.beans.BeanProperty

case class Appointment(@BeanProperty id: String) {
  def getAll: AppointmentList = AppointmentList(
    (1 to 10).toArray.map(x => AppointmentResponse(x.toString, s"Mocked appointment for ${id}"))
  )
}
