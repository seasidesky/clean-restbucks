package be.sourcedbvba.restbucks.usecase.validation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import javax.validation.Validator

@Configuration
@EnableAspectJAutoProxy
internal class UseCaseValidationConfiguration {
    @Bean
    fun useCaseValidationAspect(validator: Validator) = UseCaseValidatonAspect(validator)
}