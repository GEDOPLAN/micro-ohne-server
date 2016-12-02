package de.gedoplan.micro.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {
  @PersistenceContext(unitName = "showcase-ee")
  EntityManager entityManager;

  @Produces
  EntityManager getEntityManager() {
    return this.entityManager;
  }
}
