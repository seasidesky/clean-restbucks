package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.order.gateway.OrderGateway
import be.sourcedbvba.restbucks.usecase.UseCase

@UseCase
internal class GetOrdersImpl(val orderGateway: OrderGateway) : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        val orders = orderGateway.getOrders()
        return presenter(orders.map { it.toResponse() })
    }

    private fun Order.toResponse() : GetOrdersResponse {
        return GetOrdersResponse(getId(), getCustomer(), getStatus(), getItems().map { it.toResponse() })
    }

    private fun OrderItem.toResponse() : GetOrdersResponseItem {
        return GetOrdersResponseItem(getProduct(), getQuantity(), getSize(), getMilk())
    }
}