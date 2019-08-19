package sk.essentialdata;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
public class EaExporterApplication extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(EaExporterApplication.class, args);
  }

  private final String pathToWebserver;
  private final EaDumpService eaDumpService;

  public EaExporterApplication(@Value("${app.path-to-webserver}") String pathToWebserver,
      EaDumpService eaDumpService) {
    this.pathToWebserver = pathToWebserver;
    this.eaDumpService = eaDumpService;
  }

  @RequestMapping("/generate")
  public String generate(
      @RequestParam(value = "guid", defaultValue = "${app.default-guid}") String guid) {
    eaDumpService.dump(guid);
    return "Success";
  }

  @RequestMapping("/")
  void handleFoo(HttpServletResponse response) throws IOException {
    response.sendRedirect("/index.html");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("file:///" + pathToWebserver + "/")
        .setCachePeriod(0);
  }
}
