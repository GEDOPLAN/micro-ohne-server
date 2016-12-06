package de.gedoplan.micro.platform;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Alternative
@Priority(1)
@ApplicationScoped
public class ThreadLocalEntityManagerProducer {
  @PersistenceUnit(unitName = "showcase-ee")
  EntityManagerFactory entityManagerFactory;

  ThreadLocal<EntityManager> entityManagerHolder = new ThreadLocal<>();

  @Produces
  @Dependent
  EntityManager getEntityManager() {
    EntityManager entityManager = this.entityManagerHolder.get();
    if (entityManager == null) {
      entityManager = this.entityManagerFactory.createEntityManager();
      this.entityManagerHolder.set(entityManager);
    }
    return entityManager;
  }

  void closeEntityManager(@Disposes EntityManager entityManager) {
    this.entityManagerHolder.set(null);
    entityManager.close();
  }
}
