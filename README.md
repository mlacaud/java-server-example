# java-server-example

Simple java server using spring, jersey and a mysql database.

In Eclipse, use "Import an existing Maven Project" and select this repository. The server is in the project example-javaserver. Launch it using the "MddashApplication.java" class.

## Install java 8 if you don't have this version
```
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections

sudo apt-get install oracle-java8-installer

echo javax.xml.accessExternalSchema = all | sudo tee  /usr/lib/jvm/java-8-oracle/jre/lib/jaxp.properties > /dev/null
```

## Parameters
Some parameters, like the address, the username and the password of the database can be found in the file "src/resources/application.yml".

If you don't have a db running and if you have docker installed on your computer, you can start a simple db using:
```
docker run -d --name db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=msstream -e MYSQL_USER=msstream -e MYSQL_PASSWORD=msstream mysql:5.6
```

## Building and running the server without Eclipse
Go to the repo and build using maven:

```
cd java-server-example
mvn clean package
```
The **mvn clean package** command also create a new docker image. If you don't have docker installed, the build will return an error. However, if you have a new jar in **docker/javaserver/javaserver.jar**, the build is ok.

Launch the server using the .jar:
```
java -jar docker/javaserver/javaserver.jar
```

## Playing with endpoints
Some endpoints are available. They can be used to understand how the link with the db are working with spring and jpa.

GET the list of contents:
```
curl http://localhost:9000/api/content
```

POST a new Content and specify the name:
```
curl -X POST http://localhost:9000/api/content
```

PUT a content, to modify the video name for example:
```
curl -X PUT -H "Content-Type: application/json" -d '{"videoName":"newName","uuid":"<uuid of the video>"}' http://localhost:9000/api/content
```

DELETE all of the contents:
```
curl -X DELETE http://localhost:9000/api/content/all
```

DELETE a specific content:
```
curl -X DELETE http://localhost:9000/api/content/<uuid of the content>
```

## End points and access to the database in the code
The end points are in **example-javaserver/src/main/java/net/viotech/msstream/javaserver/endpoints/ContentEndPoints.java**.
The databse is called using jpa, a java tool that is responsible for the conversion of java object and functions into sql queries. The object to put in the database are in the package **example-javaserver/src/main/java/net/viotech/msstream/javaserver/models/**. The classes responsible for the conversion of objects into the database are in **example-javaserver/src/main/java/net/viotech/msstream/javaserver/models/**.

As you can see in the end points, the usual calls of the database are very easy using jpa, because you just have to use:
```
contentRepository.save(Content); // to save a content in the DB, or update an existing content
contentRepository.findAll(); // to get all of the contents
contentRepository.findOneByUuid(uuid); // to get a specific content thanks to its uuid. This method is in ContentRepository.java and jpa understand the name of the function as a query to get one content with a specific uuid. So, the developper doesn't need to write the SQL query.
```

In the end points, the configuration is easy to because the java code can retrieve java objects from json or xml inputs using the **@Consumes** annotation (as long as the java object has a **@XmlRootElement**). With the same idea, java object can be returned as json (or xml) using the **@Produces** annotation.
