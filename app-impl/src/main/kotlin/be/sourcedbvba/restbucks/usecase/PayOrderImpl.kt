package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.domain.gateway.OrderGateway

@UseCase
class PayOrderImpl(val orderGateway: OrderGateway) : PayOrder {
    override fun pay(request: PayOrderRequest) {
        val order = orderGateway.getOrder(request.orderId)
        order.pay()
    }
}