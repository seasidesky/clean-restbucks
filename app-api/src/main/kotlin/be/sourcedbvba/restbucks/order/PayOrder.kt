package be.sourcedbvba.restbucks.order

interface PayOrder {
    fun pay(request: PayOrderRequest)
}

data class PayOrderRequest(val orderId: String)
