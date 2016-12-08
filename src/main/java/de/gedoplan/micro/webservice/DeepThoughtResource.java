package de.gedoplan.micro.webservice;

import de.gedoplan.micro.service.DeepThoughtService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("answer")
public class DeepThoughtResource {
  @Inject
  DeepThoughtService svc;

  @GET
  public String get() {
    return "The answer is " + this.svc.getAnswer();
  }
}
