package be.sourcedbvba.restbucks.order.gateway

import be.sourcedbvba.restbucks.order.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface OrderJpaRepository : JpaRepository<OrderEntity, String>