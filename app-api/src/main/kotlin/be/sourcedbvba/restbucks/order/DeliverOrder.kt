package be.sourcedbvba.restbucks.order

interface DeliverOrder {
    fun deliver(request: DeliverOrderRequest)
}

data class DeliverOrderRequest(val orderId: String)