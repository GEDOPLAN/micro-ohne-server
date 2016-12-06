package de.gedoplan.micro.bootstrap;

import com.kumuluz.ee.EeApplication;
import com.kumuluz.ee.common.config.EeConfig;
import com.kumuluz.ee.common.config.ServerConfig;

/**
 * Bootstrapper for KumuluzEE.
 *
 * This claas can be used for starting the applicaion instead of the default {@link EeApplication}, using a different context path.
 *
 * @author dw
 */
public class KumuluzEEBootstrap {

  public static void main(String[] args) {
    new EeApplication(new CustomizedEeConfig());
  }

  private static class CustomizedEeConfig extends EeConfig {
    private ServerConfig serverConfig = new CustomizedServerConfig();

    @Override
    public ServerConfig getServerConfig() {
      return this.serverConfig;
    }
  }

  private static class CustomizedServerConfig extends ServerConfig {

    @Override
    public String getContextPath() {
      return "/micro-ohne-server";
    }

  }
}
