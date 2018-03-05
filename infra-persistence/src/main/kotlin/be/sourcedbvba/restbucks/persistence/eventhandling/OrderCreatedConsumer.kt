package be.sourcedbvba.restbucks.persistence.eventhandling

import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.event.DomainEventConsumer
import be.sourcedbvba.restbucks.domain.event.OrderCreated
import be.sourcedbvba.restbucks.persistence.OrderJpaRepository
import be.sourcedbvba.restbucks.persistence.entity.OrderEntity
import be.sourcedbvba.restbucks.persistence.entity.OrderItemEntity
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class OrderCreatedConsumer(val orderJpaRepository: OrderJpaRepository) : DomainEventConsumer<OrderCreated> {
    override fun consume(event: OrderCreated) {
        val orderEntity = event.order.toJpa()
        orderJpaRepository.save(orderEntity)
    }

    fun Order.toJpa() : OrderEntity {
        return OrderEntity(id, customer, status, cost, items.map { it.toJpa() })
    }

    fun OrderItem.toJpa() : OrderItemEntity {
        return OrderItemEntity(null, product, quantity, size, milk)
    }
}
