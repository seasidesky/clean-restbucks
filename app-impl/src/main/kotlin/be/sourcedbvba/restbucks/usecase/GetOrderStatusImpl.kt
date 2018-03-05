package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.gateway.OrderGateway
import java.util.*

@UseCase
class GetOrderStatusImpl(val orderGateway: OrderGateway) : GetOrderStatus {
    override fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): T {
        val order = orderGateway.getOrder(request.orderId)
        return presenter(GetOrderStatusResponse(order.status))
    }
}