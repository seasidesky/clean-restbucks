package be.sourcedbvba.restbucks.order.gateway

import be.sourcedbvba.restbucks.order.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.stream.Stream

internal interface OrderJpaRepository : JpaRepository<OrderEntity, String> {
    @Query("SELECT p FROM OrderEntity p")
    fun findAllAsStream() : Stream<OrderEntity>
}