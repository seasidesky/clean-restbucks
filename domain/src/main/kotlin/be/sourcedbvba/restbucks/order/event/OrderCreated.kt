package be.sourcedbvba.restbucks.order.event

import be.sourcedbvba.restbucks.order.Order
import be.sourcedbvba.restbucks.domain.event.DomainEvent

interface OrderCreated : DomainEvent {
    fun getOrder() : Order
}

internal data class OrderCreatedEvent(private val order: Order) : OrderCreated {
    override fun getOrder(): Order {
        return order
    }
}