package be.sourcedbvba.restbucks.domain.gateway

import be.sourcedbvba.restbucks.domain.Order

interface OrderGateway {
    fun getOrders() : List<Order>
    fun getOrder(orderId: String) : Order
}