package com.stratio.cex.response

import scala.beans.BeanProperty

case class Health(@BeanProperty alive: Boolean)

object Health {
  def OK = Health(true)
  def NOK = Health(false)
}
