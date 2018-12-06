## Setup
### Download eaapi.jar
### Install eaapi.jar
```
mvn install:install-file -Dfile=eaapi.jar -DgroupId=org.sparx -DartifactId=eaapi -Dversion=1.0.0 -Dpackaging=jar
```
### Package the jar
```
mvn clean package
```
### Configure the connection and report folder
Create your own application.properties file and edit the configuration

| Config name           | Description |
|-----------------------|-------------|
| app.path-to-webserver | This folder must exist. Provide absolute path                                    |
| app.connection-string | Connection string to your EA repository                                           |
| app.default-guid      | GUID of the root element for example model                                        |
| app.isSVN             | If set to true, when generating reports ea-exporter will get all latest changes   |

## Run the application
```
java -jar ea-exporter.jar application.properties
```
### Generate the HTML report
By visiting http://your-server:port/generate
### View the result
By visiting http://your-server:port/
### Reference a diagram or element
By copying its GUID and calling http://your-server:port/?guid={guid}