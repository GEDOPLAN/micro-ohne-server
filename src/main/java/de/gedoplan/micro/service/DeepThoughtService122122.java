package de.gedoplan.micro.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeepThoughtService122122 {
  @Inject
  DeepThoughtService1221221 s1;
  @Inject
  DeepThoughtService1221222 s2;

  int getAnswer() {
    return this.s1.getAnswer() + this.s2.getAnswer();
  }
}
