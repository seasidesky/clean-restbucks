package be.sourcedbvba.restbucks.domain.event

interface EventPublisher {
    fun publishEvent(event: DomainEvent)

    object Locator {
        lateinit var eventPublisher: EventPublisher
    }
}