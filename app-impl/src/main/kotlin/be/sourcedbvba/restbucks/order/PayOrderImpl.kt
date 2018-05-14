package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.order.gateway.OrderGateway
import be.sourcedbvba.restbucks.usecase.UseCase

@UseCase
internal class PayOrderImpl(val orderGateway: OrderGateway) : PayOrder {
    override fun pay(request: PayOrderRequest) {
        val order = orderGateway.getOrder(request.orderId)
        order.pay()
    }
}