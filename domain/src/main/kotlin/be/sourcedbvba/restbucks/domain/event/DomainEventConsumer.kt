package be.sourcedbvba.restbucks.domain.event

interface DomainEventConsumer<E : DomainEvent> {
    fun consume(event: E)
}
