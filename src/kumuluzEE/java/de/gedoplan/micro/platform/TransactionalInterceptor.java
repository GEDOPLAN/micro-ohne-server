package de.gedoplan.micro.platform;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

/**
 * Interceptor class for binding {@link Transactional @Transactional}.
 *
 * This interceptor controls transactions as entity manager local transactions. This works in combination with {@link ThreadLocalEntityManagerProducer} only
 * because all injection poins must receive the same entity manager within the same thread.
 * 
 * @author dw
 *
 */
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
