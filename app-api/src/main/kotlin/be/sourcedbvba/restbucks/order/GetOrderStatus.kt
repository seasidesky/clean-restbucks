package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.Status
import reactor.core.publisher.Mono

interface GetOrderStatus {
    fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T) : Mono<T>
}

data class GetOrderStatusRequest(val orderId: String)
data class GetOrderStatusResponse(val status: Status)
