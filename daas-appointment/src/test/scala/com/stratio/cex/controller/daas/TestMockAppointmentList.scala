package com.stratio.cex.controller.daas

import com.stratio.cex.daas.BaseTest
import com.stratio.cex.response.{Appointment, AppointmentList}

/**
  * Created by mariofernandez on 25/07/17.
  */
class TestMockAppointmentList extends BaseTest {

  info("As an appointment processor")
  info("I want to be able to generate a list of appointments")

  feature("generate a mocked AppointmentList with a given identifier") {
    val id = "identifier"

    scenario("appointment processor calls generate function for 0 elements") {
      Given("an accessible static method for generating AppointmentList")

      When("is called with an identifier for generating 0 elements")
      val result = MockAppointmentList(id, 0)

      Then("resulting AppointmentList should be empty")
      result shouldBe an [AppointmentList]
      result.appointments shouldBe empty
    }

    scenario("appointment processor calls generate function for 4 elements") {
      Given("an accessible static method for generating AppointmentList")

      When("is called with an identifier for generating 4 elements")
      val result = MockAppointmentList(id, 4)

      Then("resulting AppointmentList should contain 4 Appointment elements")
      result shouldBe an [AppointmentList]
      result.appointments should have length 4
      result.appointments.foreach(a => a shouldBe an [Appointment])
    }
  }
}
