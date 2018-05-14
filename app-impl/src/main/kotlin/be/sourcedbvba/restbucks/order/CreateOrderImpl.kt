package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.usecase.UseCase
import java.util.*

@UseCase
internal class CreateOrderImpl : CreateOrder {
    override fun <T> create(request: CreateOrderRequest, presenter: (CreateOrderResponse) -> T): T {
        val order = request.toOrder()
        order.create()
        return presenter(order.toResponse())
    }

    private fun CreateOrderRequest.toOrder() : Order {
        val id = UUID.randomUUID().toString()
        return Order.build(id, customer, Status.OPEN, items.map { it.toOrderItem() })
    }

    private fun CreateOrderRequestItem.toOrderItem(): OrderItem {
        return OrderItem.build(product, quantity, size, milk)
    }

    private fun Order.toResponse() : CreateOrderResponse {
        return CreateOrderResponse(getId(), getCustomer(), getCost())
    }
}