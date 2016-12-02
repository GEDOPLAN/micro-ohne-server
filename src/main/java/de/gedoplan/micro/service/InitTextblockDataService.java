package de.gedoplan.micro.service;

import de.gedoplan.micro.entity.Textblock;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;

@Transactional(rollbackOn = Throwable.class)
public class InitTextblockDataService implements Serializable {
  @Inject
  EntityManager entityManager;

  @Inject
  private Log log;

  void createDemoData(@Observes @Initialized(ApplicationScoped.class) Object event) {
    if (this.entityManager == null) {
      this.log.warn("Cannot create test data: EntityManager==null (GlassFish/Payara bug?)");
      return;
    }

    try {
      if (this.entityManager.createQuery("select count(tb) from Textblock tb", Long.class).getSingleResult() == 0) {
        this.log.debug("Creating test data");
        this.entityManager.persist(new Textblock("Hi Dude!"));
        this.entityManager.persist(new Textblock("Java EE rocks!"));
      }
    } catch (Exception e) {
      this.log.warn("Cannot create test data", e);
    }
  }
}
