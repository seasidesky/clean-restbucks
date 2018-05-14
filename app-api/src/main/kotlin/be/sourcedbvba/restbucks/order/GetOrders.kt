package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.Milk
import be.sourcedbvba.restbucks.Size
import be.sourcedbvba.restbucks.Status

interface GetOrders {
    fun <T> getOrders(presenter: (List<GetOrdersResponse>) -> T) : T
}

data class GetOrdersResponse(val id: String,
                             val customer: String,
                             val status: Status,
                             val items: List<GetOrdersResponseItem>)
data class GetOrdersResponseItem(val product: String,
                                 val quantity: Int,
                                 val size: Size,
                                 val milk: Milk)
