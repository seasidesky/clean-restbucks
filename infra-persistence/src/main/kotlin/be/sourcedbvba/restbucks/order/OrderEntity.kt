package be.sourcedbvba.restbucks.order

import be.sourcedbvba.restbucks.Status
import java.math.BigDecimal
import javax.persistence.*

@Entity
internal data class OrderEntity(@Id val id: String,
                       val customerName: String,
                       @Enumerated var status: Status,
                       val cost: BigDecimal,
                       @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
                       @JoinColumn(name = "order_id")
                       val items: List<OrderItemEntity>)