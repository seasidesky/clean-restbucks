package be.sourcedbvba.restbucks.usecase.transaction

import be.sourcedbvba.restbucks.usecase.UseCase
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import java.util.function.Supplier

@Aspect
class TransactionalUseCaseAspect(private val transactionalUseCaseExecutor: TransactionalUseCaseExecutor) {

    @Pointcut("@within(useCase)")
    fun inUseCase(useCase: UseCase) {
    }

    @Around("inUseCase(useCase)")
    fun useCase(proceedingJoinPoint: ProceedingJoinPoint, useCase: UseCase): Any? {
        return transactionalUseCaseExecutor.executeInTransaction(Supplier { proceedingJoinPoint.proceed() })
    }
}
