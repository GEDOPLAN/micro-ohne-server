package de.gedoplan.micro.web;

import de.gedoplan.baselibs.utils.util.ApplicationProperties;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class Index extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String platform = ApplicationProperties.getProperty("platform");
    if (platform == null || platform.isEmpty()) {
      String jbossHomeDir = System.getProperty("jboss.home.dir");
      if (jbossHomeDir != null) {
        platform = "JBoss/WildFly (" + jbossHomeDir + ")";
      }
    }
    if (platform == null || platform.isEmpty()) {
      String productRoot = System.getProperty("com.sun.aas.productRoot");
      if (productRoot != null) {
        platform = "GlassFish/Payara (" + productRoot + ")";
      }
    }
    if (platform == null || platform.isEmpty()) {
      platform = System.getProperties().toString();
    }
    response.setContentType("text/html;charset=UTF-8");

    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<h2>micro-ohne-server</h2>");
    out.println("<table>");
    out.println("  <tr>");
    out.println("    <td>Powered by</td>");
    out.println("    <td>" + platform + "</td>");
    out.println("  </tr>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");

  }

}
