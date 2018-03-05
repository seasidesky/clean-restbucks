package be.sourcedbvba.restbucks.persistence.gateway

import be.sourcedbvba.restbucks.domain.Order
import be.sourcedbvba.restbucks.domain.OrderItem
import be.sourcedbvba.restbucks.domain.gateway.OrderGateway
import be.sourcedbvba.restbucks.persistence.OrderJpaRepository
import be.sourcedbvba.restbucks.persistence.entity.OrderEntity
import be.sourcedbvba.restbucks.persistence.entity.OrderItemEntity
import org.springframework.stereotype.Component

@Component
class JpaOrderGateway(val orderJpaRepository: OrderJpaRepository) : OrderGateway {
    override fun getOrder(orderId: String): Order {
        return orderJpaRepository.getOne(orderId).toDomain()
    }

    override fun getOrders(): List<Order> {
        return orderJpaRepository.findAll().map { it.toDomain() }
    }

    fun OrderEntity.toDomain() : Order {
        return Order(id, customerName, status, items.map { it.toDomain() })
    }

    fun OrderItemEntity.toDomain() : OrderItem {
        return OrderItem(product, quantity, size, milk)
    }
}