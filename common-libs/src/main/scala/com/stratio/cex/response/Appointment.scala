package com.stratio.cex.response

import scala.beans.BeanProperty

/**
  * Created by mariofernandez on 9/08/17.
  */
case class Appointment(@BeanProperty id: String, @BeanProperty desc: String) extends ProcessorResponse {
  def this() = this("unknown", "unknown")
  def this(id: String) = this(id, "unknown")
}
