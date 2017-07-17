package com.stratio.cex.controller

import com.stratio.cex.processor.AppointmentProcessor
import com.stratio.cex.response.{AppointmentList, Appointment => AppointmentResponse}
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}

@RestController
class CommandController {
  @RequestMapping(value=Array("/appointments"), method=Array(RequestMethod.GET))
  def getAllAppointmets(@RequestParam(value="id", defaultValue="") id: String): AppointmentList =
    AppointmentProcessor(id).getAll
}
