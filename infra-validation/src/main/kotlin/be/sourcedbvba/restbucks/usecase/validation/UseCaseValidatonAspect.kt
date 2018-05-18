package be.sourcedbvba.restbucks.usecase.validation

import be.sourcedbvba.restbucks.usecase.UseCase
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import javax.validation.ConstraintViolationException
import javax.validation.Validator

@Aspect
internal class UseCaseValidatonAspect(val validator: Validator) {

    @Pointcut("@within(useCase)")
    fun inUseCase(useCase: UseCase) {
    }

    @Around("inUseCase(useCase)")
    fun useCase(proceedingJoinPoint: ProceedingJoinPoint, useCase: UseCase): Any? {
        if(proceedingJoinPoint.args.size > 1) {
            validateUseCaseArgument(proceedingJoinPoint.args[0]);
        }
        return proceedingJoinPoint.proceed()
    }

    private fun validateUseCaseArgument(arg: Any) {
        val validate = validator.validate(arg)
        if(validate.isNotEmpty()) {
            throw ConstraintViolationException(validate)
        }
    }
}
