package be.sourcedbvba.restbucks.domain.event.spring

import be.sourcedbvba.restbucks.domain.event.DomainEvent
import be.sourcedbvba.restbucks.domain.event.EventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
internal class SpringApplicationEventPublisher(private val applicationEventPublisher: ApplicationEventPublisher) : EventPublisher {
    override fun publishEvent(event: DomainEvent) {
        applicationEventPublisher.publishEvent(event);
    }
}
