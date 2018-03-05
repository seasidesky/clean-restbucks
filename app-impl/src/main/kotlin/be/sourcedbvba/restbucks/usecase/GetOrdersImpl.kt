package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.gateway.OrderGateway

@UseCase
class GetOrdersImpl(val orderGateway: OrderGateway) : GetOrders {
    override fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T): T {
        val orders = orderGateway.getOrders()
        return presenter(orders.map { it.toResponse() })
    }

    fun Order.toResponse() : GetOrdersResponse {
        return GetOrdersResponse(id, customer, status, items.map { it.toResponse() })
    }

    fun OrderItem.toResponse() : GetOrdersResponseItem {
        return GetOrdersResponseItem(product, quantity, size, milk)
    }
}