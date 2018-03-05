package be.sourcedbvba.restbucks.domain.event

import be.sourcedbvba.restbucks.domain.Order

data class OrderCreated(val order: Order) : DomainEvent