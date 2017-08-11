package com.stratio.cex.discovery

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.{Component}

import scala.collection.JavaConversions._

@Component
class DiscoveryService {
  @Autowired
  val discoveryClient: DiscoveryClient = null

  def discover(service: String): Option[ServiceInstance] = {
    val instances = discoveryClient.getInstances(service).toList
    instances match {
      case head :: tail => Some(head)
      case _ => None
    }
  }
}


