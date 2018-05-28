package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.order.gateway.OrderGateway
import be.sourcedbvba.restbucks.usecase.UseCase
import reactor.core.publisher.Mono

@UseCase
internal class GetOrderStatusImpl(val orderGateway: OrderGateway) : GetOrderStatus {
    override fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T): Mono<T> {
        val order = orderGateway.getOrder(request.orderId)
        return order.map { presenter(GetOrderStatusResponse(it.status)) }
    }
}