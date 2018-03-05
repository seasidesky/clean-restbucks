package be.sourcedbvba.restbucks.persistence.eventhandling

import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.event.DomainEventConsumer
import be.sourcedbvba.restbucks.domain.event.OrderCreated
import be.sourcedbvba.restbucks.domain.event.OrderDeleted
import be.sourcedbvba.restbucks.persistence.OrderJpaRepository
import be.sourcedbvba.restbucks.persistence.entity.OrderEntity
import be.sourcedbvba.restbucks.persistence.entity.OrderItemEntity
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class OrderDeletedConsumer(val orderJpaRepository: OrderJpaRepository) : DomainEventConsumer<OrderDeleted> {
    override fun consume(event: OrderDeleted) {
        orderJpaRepository.deleteById(event.id)
    }
}
