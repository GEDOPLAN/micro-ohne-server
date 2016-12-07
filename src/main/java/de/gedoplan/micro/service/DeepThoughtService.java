package de.gedoplan.micro.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeepThoughtService {
  @Inject
  DeepThoughtService11 s1;
  @Inject
  DeepThoughtService12 s2;

  public int getAnswer() {
    return this.s1.getAnswer() + this.s2.getAnswer();
  }
}
