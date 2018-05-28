package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.usecase.UseCase
import reactor.core.publisher.Mono
import java.util.*

@UseCase
internal class CreateOrderImpl : CreateOrder {
    override fun <T> create(request: Mono<CreateOrderRequest>, presenter: (CreateOrderResponse) -> T): Mono<T> {
        return request.map {
            var order = it.toOrder()
            order.create();
            presenter(order.toResponse())
        }
    }

    private fun CreateOrderRequest.toOrder() : Order {
        val id = UUID.randomUUID().toString()
        return Order(id, customer, Status.OPEN, items.map { it.toOrderItem() })
    }

    private fun CreateOrderRequestItem.toOrderItem(): OrderItem {
        return OrderItem(product, quantity, size, milk)
    }

    private fun Order.toResponse() : CreateOrderResponse {
        return CreateOrderResponse(id, customer, cost)
    }
}