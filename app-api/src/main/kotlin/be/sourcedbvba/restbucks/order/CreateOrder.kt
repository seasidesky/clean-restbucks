package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.Milk
import be.sourcedbvba.restbucks.Size
import reactor.core.publisher.Mono
import java.math.BigDecimal

interface CreateOrder {
    fun <T> create(request: Mono<CreateOrderRequest>, presenter: (CreateOrderResponse) -> T) : Mono<T>
}

data class CreateOrderRequest(val customer: String, val items: List<CreateOrderRequestItem>)
data class CreateOrderRequestItem(val product: String, val quantity: Int, val size: Size, val milk: Milk)
data class CreateOrderResponse(val id: String, val customer: String, val amount: BigDecimal)
