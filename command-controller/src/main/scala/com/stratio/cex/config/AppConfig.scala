package com.stratio.cex.config

import com.stratio.cex.discovery.DiscoveryService
import com.stratio.cex.processor.daas.AppointmentProcessor
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {

  @Bean
  def appointmentProcessor: AppointmentProcessor = {
    new AppointmentProcessor
  }

  @Bean
  def discoveryService: DiscoveryService = {
    new DiscoveryService
  }

  @Bean
  def rest: RestTemplate = {
    new RestTemplate
  }

}
