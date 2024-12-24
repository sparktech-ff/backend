# Feature Flag Application

## Requirements
- Java 20 or later
- Postgres database
- GCP access
- Firebase access
- Kubernetes cluster
- GitHub actions access

## Set up environment
- Install latest version of java development kit JDK
- Set up local sql database, recommended postgres
- Install IDE (optional), recommended IntelliJ
- Create application-local.properties file inside resources folders with database details and other placeholders
    - Also, you can use any profile name like developer name
- Start application and continue with development
    - If you want to use some another profile name just add parameter during startup `-Dspring.profiles.active=<profileName>`
- Docker is required only for tests

### Application configurations
The application uses environment variables for configuration or application-local.properties inside java/resources folder
```
spring.datasource.url=jdbc:postgresql://localhost:5432/ff
spring.datasource.username=ff
spring.datasource.password=ff
```
Fill out all values.

## Open API
- Swagger UI: /api/v1/public/swagger-ui/index.html
- Open API: /api/v1/public/api-docs

### Hibernate logging can be enabled
```
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate=info
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.orm.jdbc.bind=trace
logging.level.org.hibernate.stat=debug
logging.level.org.hibernate.SQL_SLOW=info
logging.level.org.hibernate.cache=debug
```

## Database migration
In case that we change *Entity classes we have to apply same changes to database.
We can generate migration scripts with liquibase-maven-plugin

### Set up liquibase maven plugin
- Create liquibase-local.properties inside java/resources folder
- Fill out local database details
- Example:
```
url=jdbc:postgresql://localhost:5432/ff
username=ff
password=ff
driver=org.postgresql.Driver
excludeObjects="table:databasechangelog*"
outputChangeLogFile=src/main/resources/db/changelog/2022/test.yaml
changeLogFile=classpath:/db/changelog/db.changelog-master.xml
diffChangeLogFile=src/main/resources/db/changelog/2023/08-changelog.yaml
referenceUrl=hibernate:spring:com.sparktechcode.ff?dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Generate new script
- Increase migration file name index inside liquibase-local.properties
- Generate new file
  ```
    mvn clean package liquibase:diff -P local -DskipTests -Dliquibase.diffExcludeObjects="table:databasechangelog*"
  ```
- Or without build
  ```
    mvn liquibase:diff -P local -Dliquibase.diffExcludeObjects="table:databasechangelog*"
  ```
- Or without params
  ```
  mvn clean package liquibase:diff -P local -D skipTests
  ```

## Build, test and run
- Package ```mvn clean package -DskipTests```
- Install ```mvn clean install -DskipTests```
- Test ```mvn clean test```
- Run ```mvn spring-boot:run```
-
## Build and push docker images
- docker build -t ${user}/${image}:${tag} .
- docker push ${user}/${image}:${tag}

# Uploading a Video through Vimeo API (Resumable Approach)

## Step 1: Make a POST Request

Make a **POST** request to `https://api.vimeo.com/users/{userId}/videos`

### Headers:
- **Authorization**: bearer {access_token}
- **Content-Type**: application/json
- **Accept**: application/vnd.vimeo.*+json;version=3.4

