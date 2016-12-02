package de.gedoplan.micro.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {
  @PersistenceContext(unitName = "showcase-ee")
  @Produces
  EntityManager entityManager;
}
