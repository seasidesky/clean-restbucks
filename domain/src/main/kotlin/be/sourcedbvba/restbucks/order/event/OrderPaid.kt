package be.sourcedbvba.restbucks.order.event

import be.sourcedbvba.restbucks.domain.event.DomainEvent

interface OrderPaid : DomainEvent {
    fun getId() : String
}

internal data class OrderPaidEvent(private val id: String) : OrderPaid {
    override fun getId(): String {
        return id
    }
}