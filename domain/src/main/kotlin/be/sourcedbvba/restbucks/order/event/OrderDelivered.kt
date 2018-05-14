package be.sourcedbvba.restbucks.order.event

import be.sourcedbvba.restbucks.domain.event.DomainEvent

interface OrderDelivered : DomainEvent {
    fun getId() : String
}

internal data class OrderDeliveredEvent(private val id: String) : OrderDelivered {
    override fun getId(): String {
        return id
    }
}