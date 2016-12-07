package de.gedoplan.micro.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeepThoughtService111212 {
  @Inject
  DeepThoughtService1112121 s1;
  @Inject
  DeepThoughtService1112122 s2;

  int getAnswer() {
    return this.s1.getAnswer() + this.s2.getAnswer();
  }
}
