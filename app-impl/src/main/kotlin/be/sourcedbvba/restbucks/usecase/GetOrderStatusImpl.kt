package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.domain.gateway.OrderGateway

@UseCase
class GetOrderStatusImpl(val orderGateway: OrderGateway) : GetOrderStatus {
    override fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T {
        val order = orderGateway.getOrder(request.orderId)
        return presenter(GetOrderStatusResponse(order.status))
    }
}