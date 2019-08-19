package sk.essentialdata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Filip Bednárik
 * @since 4.4.2017
 */
@Service
public class EaDumpService {

  private final String pathToWebServer;
  private final String connectionString;
  private final boolean isSVN;
  private final String style;

  public EaDumpService(@Value("${app.path-to-webserver}") String pathToWebServer,
      @Value("${app.connection-string}") String connectionString,
      @Value("${app.isSVN:false}") boolean isSVN,
      @Value("${app.style:<default>}") String style) {
    this.pathToWebServer = pathToWebServer;
    this.connectionString = connectionString;
    this.isSVN = isSVN;
    this.style = style;
  }


  public void dump(String guid) {
    org.sparx.Repository r = new org.sparx.Repository();
    r.OpenFile(connectionString);
    if (isSVN) {
      r.GetPackageByGuid(guid).VersionControlGetLatest(false);
    }
    r.GetProjectInterface().RunHTMLReport(guid, pathToWebServer, "PNG", style, ".html");
    r.CloseFile();
  }
}
