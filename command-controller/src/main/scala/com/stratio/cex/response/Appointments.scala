package com.stratio.cex.response

import scala.beans.BeanProperty

case class Appointment(@BeanProperty id: String, @BeanProperty desc: String)

case class AppointmentList(@BeanProperty appointments: Array[Appointment])
