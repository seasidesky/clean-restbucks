package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.domain.gateway.OrderGateway

@UseCase
class DeleteOrderImpl(private val orderGateway: OrderGateway) : DeleteOrder {
    override fun delete(request: DeleteOrderRequest) {
        val order = orderGateway.getOrder(request.orderId)
        order.delete()
    }

}