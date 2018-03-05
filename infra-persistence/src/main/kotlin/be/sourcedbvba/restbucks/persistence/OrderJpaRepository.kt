package be.sourcedbvba.restbucks.persistence

import be.sourcedbvba.restbucks.persistence.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderEntity, String>