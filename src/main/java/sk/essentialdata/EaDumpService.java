package sk.essentialdata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EaDumpService {

    private final String pathToWebServer;
    private final String connectionString;
    private final boolean isSVN;

    public EaDumpService(@Value("${app.path-to-webserver}") String pathToWebServer,
                         @Value("${app.connection-string}") String connectionString,
                         @Value("${app.isSVN}") boolean isSVN) {
        this.pathToWebServer = pathToWebServer;
        this.connectionString = connectionString;
        this.isSVN = isSVN;
    }

    public void dump(String guid) {
        org.sparx.Repository r = new org.sparx.Repository();
        r.OpenFile(connectionString);
        if (isSVN) {
            r.GetPackageByGuid(guid).VersionControlGetLatest(false);
        }
        r.GetProjectInterface().RunHTMLReport(guid, pathToWebServer, "PNG", "<default>", ".html");
        r.CloseFile();
    }
}
