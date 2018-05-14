package be.sourcedbvba.restbucks.domain.event.spring

import be.sourcedbvba.restbucks.domain.event.EventPublisher
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
internal class EventPublisherHolderPopulator(private val eventPublisher: EventPublisher) : InitializingBean  {
    override fun afterPropertiesSet() {
        EventPublisher.Locator.eventPublisher = eventPublisher
    }
}
