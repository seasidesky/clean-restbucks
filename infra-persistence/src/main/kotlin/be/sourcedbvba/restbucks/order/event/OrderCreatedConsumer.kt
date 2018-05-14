package be.sourcedbvba.restbucks.order.event

import be.sourcedbvba.restbucks.order.Order
import be.sourcedbvba.restbucks.order.OrderItem
import be.sourcedbvba.restbucks.domain.event.DomainEventConsumer
import be.sourcedbvba.restbucks.order.gateway.OrderJpaRepository
import be.sourcedbvba.restbucks.order.OrderEntity
import be.sourcedbvba.restbucks.order.OrderItemEntity
import org.springframework.stereotype.Component

@Component
internal class OrderCreatedConsumer internal constructor(private val orderJpaRepository: OrderJpaRepository) : DomainEventConsumer<OrderCreated> {
    override fun consume(event: OrderCreated) {
        val orderEntity = event.getOrder().toJpa()
        orderJpaRepository.save(orderEntity)
    }

    internal fun Order.toJpa() : OrderEntity {
        return OrderEntity(getId(), getCustomer(), getStatus(), getCost(), getItems().map { it.toJpa() })
    }

    internal fun OrderItem.toJpa() : OrderItemEntity {
        return OrderItemEntity(null, getProduct(), getQuantity(), getSize(), getMilk())
    }
}
