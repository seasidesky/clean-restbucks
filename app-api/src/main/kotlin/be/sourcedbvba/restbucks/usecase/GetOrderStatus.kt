package be.sourcedbvba.restbucks.usecase

import be.sourcedbvba.restbucks.Status

interface GetOrderStatus {
    fun <T> getStatus(request: GetOrderStatusRequest, presenter: (GetOrderStatusResponse) -> T) : T
}

data class GetOrderStatusRequest(val orderId: String)
data class GetOrderStatusResponse(val status: Status)
