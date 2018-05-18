package be.sourcedbvba.restbucks.order.gateway

import be.sourcedbvba.restbucks.order.Order
import be.sourcedbvba.restbucks.order.OrderItem
import be.sourcedbvba.restbucks.order.OrderEntity
import be.sourcedbvba.restbucks.order.OrderItemEntity
import org.springframework.stereotype.Component

@Component
internal class JpaOrderGateway internal constructor(private val orderJpaRepository: OrderJpaRepository) : OrderGateway {
    override fun getOrder(orderId: String): Order {
        return orderJpaRepository.getOne(orderId).toDomain()
    }

    override fun getOrders(): List<Order> {
        return orderJpaRepository.findAll().map { it.toDomain() }
    }

    internal fun OrderEntity.toDomain() : Order {
        return Order(id, customerName, status, items.map { it.toDomain() })
    }

    internal fun OrderItemEntity.toDomain() : OrderItem {
        return OrderItem(product, quantity, size, milk)
    }
}