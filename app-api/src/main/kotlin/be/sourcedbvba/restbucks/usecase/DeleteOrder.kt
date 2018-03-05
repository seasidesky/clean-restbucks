package be.sourcedbvba.restbucks.usecase

interface DeleteOrder {
    fun delete(request: DeleteOrderRequest)
}

data class DeleteOrderRequest(val orderId: String)