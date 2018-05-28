package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.order.gateway.OrderGateway
import be.sourcedbvba.restbucks.usecase.UseCase
import reactor.core.publisher.Flux

@UseCase
internal class GetOrdersImpl(val orderGateway: OrderGateway) : GetOrders {
    override fun <T> getOrders(presenter: (GetOrdersResponse) -> T): Flux<T> {
        return orderGateway.getOrders()
                .map { presenter(it.toResponse()) }
//        val list = orderGateway.getOrders()
//                .map { presenter(it.toResponse()) }
//                .collectList().block()!!
//        return Flux.fromIterable(list)
    }

    private fun Order.toResponse() : GetOrdersResponse {
        return GetOrdersResponse(id, customer, status, items.map { it.toResponse() })
    }

    private fun OrderItem.toResponse() : GetOrdersResponseItem {
        return GetOrdersResponseItem(product, quantity, size, milk)
    }
}