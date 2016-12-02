package de.gedoplan.micro.bootstrap;

import java.net.URL;

import org.wildfly.swarm.Swarm;

public class SwarmBootstrap {

  public static void main(String[] args) throws Exception {
    Swarm swarm = new Swarm();

    System.setProperty("swarm.context.path", "micro-ohne-server");

    URL standaloneFullXml = SwarmBootstrap.class.getClassLoader().getResource("configuration/standalone-full.xml");

    swarm
        .withXmlConfig(standaloneFullXml)
        .start()
        .deploy();
  }

}
