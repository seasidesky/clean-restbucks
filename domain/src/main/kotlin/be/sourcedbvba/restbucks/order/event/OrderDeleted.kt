package be.sourcedbvba.restbucks.order.event

import be.sourcedbvba.restbucks.domain.event.DomainEvent

interface OrderDeleted : DomainEvent {
    fun getId() : String;
}

internal data class OrderDeletedEvent(private val id: String) : OrderDeleted {
    override fun getId(): String {
        return id
    }
}