package de.gedoplan.micro.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeepThoughtService12121 {
  @Inject
  DeepThoughtService121211 s1;
  @Inject
  DeepThoughtService121212 s2;

  int getAnswer() {
    return this.s1.getAnswer() + this.s2.getAnswer();
  }
}
