package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.gateway.OrderGateway
import java.util.*

@UseCase
class PayOrderImpl(val orderGateway: OrderGateway) : PayOrder {
    override fun pay(request: PayOrderRequest) {
        val order = orderGateway.getOrder(request.orderId)
        order.pay()
    }
}