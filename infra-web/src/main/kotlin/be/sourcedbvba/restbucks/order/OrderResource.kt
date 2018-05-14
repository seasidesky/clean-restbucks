package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.order.*
import be.sourcedbvba.restbucks.usecase.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RequestMapping("/order")
@RestController
internal class OrderResource(val createOrder: CreateOrder,
                    val getOrders: GetOrders,
                    val getOrderStatus: GetOrderStatus,
                    val deleteOrder: DeleteOrder,
                    val deliverOrder: DeliverOrder,
                    val payOrder: PayOrder) {

    @PostMapping(produces = arrayOf("application/hal+json"))
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest) : CreateOrderResponseBody {
        return createOrder.create(createOrderRequest) {
            it.toResponseBody()
        }
    }

    @GetMapping
    fun getOrders() : List<GetOrdersResponseBody> {
        return getOrders.getOrders() {
            it.map { it.toResponseBody() }
        }
    }

    @GetMapping("/{orderId}/status")
    fun getOrderStatus(@PathVariable orderId: String): String {
        return getOrderStatus.getStatus(GetOrderStatusRequest(orderId)) {
            it.status.name.toLowerCase()
        }
    }

    @PostMapping("/{orderId}/payment")
    fun payForOrder(@PathVariable orderId: String) {
        payOrder.pay(PayOrderRequest(orderId))
    }


    @DeleteMapping("/{orderId}")
    fun deleteOrder(@PathVariable orderId: String) {
        deleteOrder.delete(DeleteOrderRequest(orderId))
    }

    @PostMapping("/{orderId}/delivery")
    fun deliverOrder(@PathVariable orderId: String) {
        deliverOrder.deliver(DeliverOrderRequest(orderId))
    }
}

data class HalLink(val href: String)

data class CreateOrderResponseBody(val id: String, val customer: String, val amount: BigDecimal, val _links: Map<String, HalLink>)
fun CreateOrderResponse.toResponseBody(): CreateOrderResponseBody {
    val links = mapOf(Pair("status", HalLink("/${id}/status")))
    return CreateOrderResponseBody(id, customer, amount, links)
}

data class GetOrdersResponseBody(val id: String, val customer: String, val status: String)
fun GetOrdersResponse.toResponseBody() : GetOrdersResponseBody {
    return GetOrdersResponseBody(id, customer, status.name.toLowerCase())
}




