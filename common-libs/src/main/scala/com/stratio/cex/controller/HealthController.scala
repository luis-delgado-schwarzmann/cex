package com.stratio.cex.controller

import com.stratio.cex.response.Health
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class HealthController {
  @GetMapping(value=Array("/health"))
  def get: ResponseEntity[Health] = {
    new ResponseEntity(Health.OK, HttpStatus.OK)
  }
}
