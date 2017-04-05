package sk.essentialdata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Filip Bednárik
 * @since 4.4.2017
 */
@Service
public class EaDumpService {
    @Value("${app.path-to-webserver}")
    private String pathToWebServer;

    @Value("${app.connection-string}")
    private String connectionString;

    public void dump(String guid){
        org.sparx.Repository r = new org.sparx.Repository();
        r.OpenFile(connectionString);
        r.GetProjectInterface().RunHTMLReport(guid, pathToWebServer, "PNG", "<default>", ".html");
        r.CloseFile();
    }
}
