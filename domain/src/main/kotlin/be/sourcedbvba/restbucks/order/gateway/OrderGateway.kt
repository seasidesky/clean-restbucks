package be.sourcedbvba.restbucks.order.gateway

import be.sourcedbvba.restbucks.order.Order

interface OrderGateway {
    fun getOrders() : List<Order>
    fun getOrder(orderId: String) : Order
}