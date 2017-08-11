package com.stratio.cex.controller.daas

import com.stratio.cex.response.{Appointment, AppointmentList}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

@RestController
class AppointmentController {

  @GetMapping(value = Array("/appointments"))
  def getById(@RequestParam(value = "id", defaultValue = "") id: String): ResponseEntity[AppointmentList] = {
    new ResponseEntity(MockAppointmentList(id, 10), HttpStatus.OK)
  }

}

object MockAppointmentList {
  def apply(id: String, n: Int): AppointmentList = AppointmentList(
    (1 to n).toArray.map({ x =>
      Appointment(x.toString, s"Mocked appointment for ${id}")
    })
  )
}
