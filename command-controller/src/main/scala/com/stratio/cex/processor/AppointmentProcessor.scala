package com.stratio.cex.processor

import com.stratio.cex.response.{AppointmentList, HttpResponse, Appointment => AppointmentResponse}
import org.springframework.http.HttpStatus
import scala.beans.BeanProperty

case class AppointmentProcessor(@BeanProperty id: String) {
  def getAll: Option[AppointmentList] = this.id match {
    case x if !x.isEmpty => Some(MockAppointmentList.generate(id, 10))
    case _ => None
  }
}


object MockAppointmentList {
  def generate(id: String, n: Int): AppointmentList = AppointmentList(
    (1 to n).toArray.map({ x =>
      AppointmentResponse(x.toString, s"Mocked appointment for ${id}")
    })
  )
}
