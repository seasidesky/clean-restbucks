package be.sourcedbvba.restbucks.order.gateway

import be.sourcedbvba.restbucks.order.Order
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface OrderGateway {
    fun getOrders() : Flux<Order>
    fun getOrder(orderId: String) : Mono<Order>
}