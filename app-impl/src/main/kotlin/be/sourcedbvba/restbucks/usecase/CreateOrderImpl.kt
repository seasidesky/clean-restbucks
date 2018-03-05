package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import java.util.*

@UseCase
class CreateOrderImpl : CreateOrder {
    override fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T {
        val order = request.toOrder()
        order.create()
        return presenter(order.toResponse())
    }

    fun CreateOrderRequest.toOrder() : Order {
        val id = UUID.randomUUID().toString()
        return Order(id, customer, Status.OPEN, items.map { it.toOrderItem() })
    }

    fun CreateOrderRequestItem.toOrderItem(): OrderItem {
        return OrderItem(product, quantity, size, milk)
    }

    fun Order.toResponse() : CreateOrderResponse {
        return CreateOrderResponse(id, customer, cost)
    }
}