package de.gedoplan.micro.bootstrap;

import java.net.URL;

import org.wildfly.swarm.Swarm;

public class SwarmBootstrap {

  public static void main(String[] args) throws Exception {
    System.setProperty("swarm.context.path", "micro-ohne-server");

    Swarm swarm = new Swarm();

    URL standaloneFullXml = SwarmBootstrap.class.getClassLoader().getResource("configuration/standalone-full.xml");

    swarm
        .withXmlConfig(standaloneFullXml)
        .start()
        .deploy();
  }

}
