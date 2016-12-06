package de.gedoplan.micro.platform;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION + 1)
public class TransactionalInterceptor {
  @Inject
  EntityManager entityManager;

  @AroundInvoke
  public Object manageTx(InvocationContext invocationContext) throws Exception {
    EntityTransaction transaction = this.entityManager.getTransaction();
    if (transaction.isActive()) {
      return invocationContext.proceed();
    }

    try {
      transaction.begin();
      Object result = invocationContext.proceed();
      transaction.commit();
      return result;
    } catch (Exception exception) {
      try {
        transaction.rollback();
      } catch (Throwable throwable) {
      }
      throw exception;
    }
  }
}
