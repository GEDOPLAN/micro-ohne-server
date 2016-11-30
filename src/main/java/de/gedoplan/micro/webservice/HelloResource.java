package de.gedoplan.micro.webservice;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello")
@ApplicationScoped
public class HelloResource {

  @GET
  public String get() {
    return "hello!";
  }
}
