package be.sourcedbvba.restbucks.persistence.eventhandling

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.event.DomainEventConsumer
import be.sourcedbvba.restbucks.domain.event.OrderCreated
import be.sourcedbvba.restbucks.domain.event.OrderDeleted
import be.sourcedbvba.restbucks.domain.event.OrderDelivered
import be.sourcedbvba.restbucks.persistence.OrderJpaRepository
import be.sourcedbvba.restbucks.persistence.entity.OrderEntity
import be.sourcedbvba.restbucks.persistence.entity.OrderItemEntity
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class OrderDeliveredConsumer(val orderJpaRepository: OrderJpaRepository) : DomainEventConsumer<OrderDelivered> {
    override fun consume(event: OrderDelivered) {
        val order = orderJpaRepository.getOne(event.id)
        order.status = Status.DELIVERED
        orderJpaRepository.save(order)
    }
}
