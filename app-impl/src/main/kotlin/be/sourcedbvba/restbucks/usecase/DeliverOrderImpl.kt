package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.gateway.OrderGateway
import java.util.*

@UseCase
class DeliverOrderImpl(val orderGateway: OrderGateway) : DeliverOrder {
    override fun deliver(request: DeliverOrderRequest) {
        val order = orderGateway.getOrder(request.orderId)
        order.deliver()
    }
}