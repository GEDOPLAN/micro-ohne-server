package de.gedoplan.micro.service;

import de.gedoplan.micro.entity.Textblock;
import de.gedoplan.micro.persistence.TextblockRepository;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;

@ApplicationScoped
@Transactional(rollbackOn = Throwable.class)
public class InitTextblockDataService implements Serializable {
  @Inject
  TextblockRepository textblockRepository;

  @Inject
  private Log log;

  void createDemoData(@Observes @Initialized(ApplicationScoped.class) Object event) {
    try {
      if (this.textblockRepository.countAll() == 0) {
        this.textblockRepository.persist(new Textblock("Hi Dude"));
        this.textblockRepository.persist(new Textblock("Java EE rocks!"));
      }
    } catch (Exception e) {
      this.log.warn("Cannot create test data", e);
    }
  }
}
