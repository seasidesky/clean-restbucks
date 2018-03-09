package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.domain.gateway.OrderGateway

@UseCase
class DeliverOrderImpl(val orderGateway: OrderGateway) : DeliverOrder {
    override fun deliver(request: DeliverOrderRequest) {
        val order = orderGateway.getOrder(request.orderId)
        order.deliver()
    }
}