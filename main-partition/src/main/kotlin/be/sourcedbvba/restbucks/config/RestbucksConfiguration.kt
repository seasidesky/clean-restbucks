package be.sourcedbvba.restbucks.config

import be.sourcedbvba.restbucks.usecase.UseCase
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.util.concurrent.Executors

@Configuration
@ComponentScan(basePackages = ["be.sourcedbvba.restbucks"],
        includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION,
        value = [UseCase::class])])
internal class RestbucksConfiguration {

    @Bean @Qualifier("jpa")
    fun jpaScheduler(@Value("\${spring.datasource.hikari.maximum-pool-size}") connectionPoolSize : Int) : Scheduler {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}