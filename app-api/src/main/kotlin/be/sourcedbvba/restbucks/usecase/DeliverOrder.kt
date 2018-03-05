package be.sourcedbvba.restbucks.usecase

interface DeliverOrder {
    fun deliver(request: DeliverOrderRequest)
}

data class DeliverOrderRequest(val orderId: String)