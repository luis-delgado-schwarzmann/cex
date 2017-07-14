package com.stratio.cex.controller

import com.stratio.cex.processor.Appointment
import com.stratio.cex.response.{AppointmentList, Appointment => AppointmentResponse}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}

@RestController
class Command {
  @RequestMapping(value=Array("/appointments"), method=Array(RequestMethod.GET))
  def getAllAppointmets(@RequestParam(value="id", defaultValue="") id: String): AppointmentList = id match {
    case x if !x.isEmpty => Appointment(id).getAll
    case _ => AppointmentList(Array.empty[AppointmentResponse])
  }
}
