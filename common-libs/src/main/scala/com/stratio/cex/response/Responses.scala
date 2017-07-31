package com.stratio.cex.response

import scala.beans.BeanProperty

trait ProcessorResponse

case class Appointment(@BeanProperty id: String, @BeanProperty desc: String) extends ProcessorResponse {
  def this() = this("unknown", "unknown")
  def this(id: String) = this(id, "unknown")
}

object AppointmentList {
  def empty: AppointmentList = new AppointmentList(Array.empty[Appointment])
}

case class AppointmentList(@BeanProperty appointments: Array[Appointment]) extends ProcessorResponse {
  def this() = this(Array.empty[Appointment])
}


