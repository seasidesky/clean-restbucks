package be.sourcedbvba.restbucks.domain.event.spring

import be.sourcedbvba.restbucks.domain.event.DomainEvent
import be.sourcedbvba.restbucks.domain.event.DomainEventConsumer
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationListener
import org.springframework.context.PayloadApplicationEvent
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class SpringDomainEventConsumerRegistrar(val applicationEventMulticaster: ApplicationEventMulticaster,
                                         val eventConsumers: List<DomainEventConsumer<*>>) : InitializingBean {
    override fun afterPropertiesSet() {
        eventConsumers.forEach(this::registerEventConsumer)
    }

    private fun registerEventConsumer(it: DomainEventConsumer<*>) {
        val klass = determineEventClass(it)
        applicationEventMulticaster.addApplicationListener(EventConsumerListener(klass, it))
    }

    private fun determineEventClass(eventConsumer: DomainEventConsumer<*>): KClass<*> {
        return eventConsumer::class.members.first { it.name == "consume" }.parameters.get(1).type.classifier as KClass<*>
    }


    class EventConsumerListener<E : DomainEvent>(val klass: KClass<*>, val domainEventConsumer: DomainEventConsumer<E>) :
            ApplicationListener<PayloadApplicationEvent<E>> {
        override fun onApplicationEvent(event: PayloadApplicationEvent<E>?) {
            if(klass.isInstance(event!!.payload)) {
                domainEventConsumer.consume(event.payload)
            }
        }
    }
}
