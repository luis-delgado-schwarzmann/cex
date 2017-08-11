package com.stratio.cex.controller

import com.stratio.cex.processor.daas.AppointmentProcessor
import com.stratio.cex.response.AppointmentList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

@RestController
class CommandController {

  @Autowired
  val appointmentProcessor: AppointmentProcessor = null

  @GetMapping(value=Array("/appointments"))
  def getAllAppointmets(@RequestParam(value="id", defaultValue="") id: String): ResponseEntity[AppointmentList] = {
    val (response, code) = id match {
      case x if ! x.isEmpty => appointmentProcessor.getAll(x) match {
        case Some(appointments) => (appointments, HttpStatus.OK)
        case _ => (AppointmentList.empty, HttpStatus.NOT_FOUND)
      }
      case _ => (AppointmentList.empty, HttpStatus.BAD_REQUEST)
    }

    new ResponseEntity(response, code)
  }
}
