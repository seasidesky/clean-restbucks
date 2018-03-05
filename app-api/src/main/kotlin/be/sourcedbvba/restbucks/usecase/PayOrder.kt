package be.sourcedbvba.restbucks.usecase

interface PayOrder {
    fun pay(request: PayOrderRequest)
}

data class PayOrderRequest(val orderId: String)
