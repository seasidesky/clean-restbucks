package be.sourcedbvba.restbucks.domain

import be.sourcedbvba.restbucks.Milk
import be.sourcedbvba.restbucks.Size
import be.sourcedbvba.restbucks.Status
import be.sourcedbvba.restbucks.domain.event.OrderCreated
import be.sourcedbvba.restbucks.domain.event.OrderDeleted
import be.sourcedbvba.restbucks.domain.event.OrderDelivered
import be.sourcedbvba.restbucks.domain.event.OrderPaid
import java.math.BigDecimal
import java.util.*

class Order(val id: String, val customer: String, status: Status, val items: List<OrderItem>) {
    lateinit var cost : BigDecimal
    var status : Status = status
        private set

    fun calculateCost() {
        cost = BigDecimal(Random().nextInt(20))
    }

    fun create() {
        calculateCost()
        return OrderCreated(this).sendEvent()
    }

    fun delete() {
        return OrderDeleted(id).sendEvent()
    }

    fun pay() {
        if(status == Status.OPEN) {
            status = Status.PAID
            return OrderPaid(id).sendEvent()
        } else {
            throw IllegalStateException("Order should be open in order to be paid")
        }
    }

    fun deliver() {
        if(status == Status.PAID) {
            return OrderDelivered(id).sendEvent()
        } else {
            throw IllegalStateException("Order has not been paid yet")
        }
    }
}

data class OrderItem(val product: String, val quantity: Int, val size: Size, val milk: Milk)