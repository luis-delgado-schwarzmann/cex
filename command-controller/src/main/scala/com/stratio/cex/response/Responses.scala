package com.stratio.cex.response

import scala.beans.BeanProperty

trait ProcessorResponse

case class Appointment(@BeanProperty id: String, @BeanProperty desc: String) extends ProcessorResponse

object AppointmentList {
  def empty: AppointmentList = AppointmentList(Array.empty[Appointment])
}
case class AppointmentList(@BeanProperty appointments: Array[Appointment]) extends ProcessorResponse

