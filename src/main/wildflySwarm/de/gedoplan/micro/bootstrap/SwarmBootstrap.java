package de.gedoplan.micro.bootstrap;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;

public class SwarmBootstrap {

  public static void main(String[] args) throws Exception {
    Swarm swarm = new Swarm();

    System.setProperty("swarm.context.path", "micro-ohne-server");

    swarm.fraction(new DatasourcesFraction()
        .dataSource("jdbc/showcase", ds -> ds
            .jndiName("java:/jdbc/showcase")
            .driverName("h2")
            .connectionUrl("jdbc:h2:~/h2/showcase;AUTO_SERVER=TRUE")
            .userName("showcase")
            .password("showcase")))
        .start()
        .deploy();
  }

}
