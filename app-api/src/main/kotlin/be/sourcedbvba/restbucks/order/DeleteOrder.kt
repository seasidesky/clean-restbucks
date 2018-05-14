package be.sourcedbvba.restbucks.order

interface DeleteOrder {
    fun delete(request: DeleteOrderRequest)
}

data class DeleteOrderRequest(val orderId: String)