package com.stratio.cex.java;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaCommandController {

    @RequestMapping("/java/getappointments")
    public JavaGetAppointmentsProcessor getAppointmentsProcessor(@RequestParam(value="id", defaultValue="") String id) {
        return new JavaGetAppointmentsProcessor(id);
    }
}