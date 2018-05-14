package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.order.gateway.OrderGateway
import be.sourcedbvba.restbucks.usecase.UseCase

@UseCase
internal class GetOrderStatusImpl(val orderGateway: OrderGateway) : GetOrderStatus {
    override fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T {
        val order = orderGateway.getOrder(request.orderId)
        return presenter(GetOrderStatusResponse(order.getStatus()))
    }
}