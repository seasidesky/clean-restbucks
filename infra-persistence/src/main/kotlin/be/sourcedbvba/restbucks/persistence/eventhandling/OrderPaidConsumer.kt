package be.sourcedbvba.restbucks.persistence.eventhandling

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.event.*
import be.sourcedbvba.restbucks.persistence.OrderJpaRepository
import be.sourcedbvba.restbucks.persistence.entity.OrderEntity
import be.sourcedbvba.restbucks.persistence.entity.OrderItemEntity
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class OrderPaidConsumer(val orderJpaRepository: OrderJpaRepository) : DomainEventConsumer<OrderPaid> {
    override fun consume(event: OrderPaid) {
        val order = orderJpaRepository.getOne(event.id)
        order.status = Status.PAID
        orderJpaRepository.save(order)
    }
}