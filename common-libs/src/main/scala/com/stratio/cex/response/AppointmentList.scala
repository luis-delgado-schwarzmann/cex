package com.stratio.cex.response

import scala.beans.BeanProperty

/**
  * Created by mariofernandez on 9/08/17.
  */
object AppointmentList {
  def empty: AppointmentList = new AppointmentList(Array.empty[Appointment])
}

case class AppointmentList(@BeanProperty appointments: Array[Appointment]) extends ProcessorResponse {
  def this() = this(Array.empty[Appointment])
}