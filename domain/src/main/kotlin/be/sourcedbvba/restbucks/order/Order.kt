package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.Milk
import be.sourcedbvba.restbucks.Size
import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.order.event.*
import java.math.BigDecimal
import java.util.*

interface Order : OrderState {
    fun create()
    fun delete()
    fun pay()
    fun deliver()

    companion object {
        fun build(id: String,  customer: String, status: Status,  items: List<OrderItem>) : Order {
            return OrderImpl(id, customer, status, items)
        }
    }
}

interface OrderState {
    fun getId() : String
    fun getCustomer() : String
    fun getStatus(): Status
    fun getItems() : List<OrderItem>
    fun getCost() : BigDecimal
}

interface OrderItem : OrderItemState {
    companion object {
        fun build(product: String, quantity: Int, size: Size, milk: Milk) : OrderItem {
            return OrderItemImpl(product, quantity, size, milk)
        }
    }
}

interface OrderItemState {
    fun getProduct() : String
    fun getQuantity() : Int
    fun getSize() : Size
    fun getMilk() : Milk
}


internal class OrderImpl(private val id: String,
                         private val customer: String,
                         private var status: Status,
                         private val items: List<OrderItem>) : Order {
    private lateinit var cost : BigDecimal

    private fun calculateCost() {
        cost = BigDecimal(Random().nextInt(20))
    }

    override fun create() {
        calculateCost()
        return OrderCreatedEvent(this).sendEvent()
    }

    override fun delete() {
        return OrderDeletedEvent(id).sendEvent()
    }

    override fun pay() {
        if(status == Status.OPEN) {
            status = Status.PAID
            return OrderPaidEvent(id).sendEvent()
        } else {
            throw IllegalStateException("Order should be open in order to be paid")
        }
    }

    override fun deliver() {
        if(status == Status.PAID) {
            return OrderDeliveredEvent(id).sendEvent()
        } else {
            throw IllegalStateException("Order has not been paid yet")
        }
    }

    override fun getId(): String {
        return id
    }

    override fun getCustomer(): String {
        return customer
    }

    override fun getStatus(): Status {
        return status
    }

    override fun getItems(): List<OrderItem> {
        return items
    }

    override fun getCost(): BigDecimal {
        return cost
    }
}

internal class OrderItemImpl(private val product: String, private val quantity: Int, private val size: Size, private val milk: Milk) : OrderItem {
    override fun getProduct(): String {
        return product
    }

    override fun getQuantity(): Int {
        return quantity
    }

    override fun getSize(): Size {
        return size
    }

    override fun getMilk(): Milk {
        return milk
    }

}