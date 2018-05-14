package be.sourcedbvba.restbucks.usecase.validation

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import javax.validation.ConstraintViolationException
import javax.validation.Validator

@Aspect
internal class UseCaseValidatonAspect(val validator: Validator) {

    @Pointcut("within(@be.sourcedbvba.restbucks.usecase.UseCase *)")
    fun useCase() {
    }

    @Around("useCase()")
    fun useCase(proceedingJoinPoint: ProceedingJoinPoint): Any? {
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
