package de.gedoplan.micro.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeepThoughtService122112 {
  @Inject
  DeepThoughtService1221121 s1;
  @Inject
  DeepThoughtService1221122 s2;

  int getAnswer() {
    return this.s1.getAnswer() + this.s2.getAnswer();
  }
}
