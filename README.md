# URL-SHORTENER
[![Build Status](https://travis-ci.com/alanlgoncalves/url-shortener.svg?branch=master)](https://travis-ci.com/alanlgoncalves/url-shortener)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=alanlgoncalves_url-shortener&metric=coverage)](https://sonarcloud.io/dashboard?id=alanlgoncalves_url-shortener)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=alanlgoncalves_url-shortener&metric=alert_status)](https://sonarcloud.io/dashboard?id=alanlgoncalves_url-shortener)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=alanlgoncalves_url-shortener&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=alanlgoncalves_url-shortener)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=alanlgoncalves_url-shortener&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=alanlgoncalves_url-shortener)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=alanlgoncalves_url-shortener&metric=security_rating)](https://sonarcloud.io/dashboard?id=alanlgoncalves_url-shortener)

## Objective

Project to shorten URLs, redirect to original URL, and save statistics the number of hits, or data of the last hits made from the shortened URL.

## Technologies

* Servidor de aplicação: Tomcat (Spring Boot)
* Banco de dados: MongoDB
* Java 8 (Streams)
* Spring Data
* Spring Security
* Spring Cloud Security
* Spring Cloud Oauth2
* String Boot Test
* Swagger - SpringFox
* Mongobee (Data Migration)
* Load Balancer: NGINX
* Docker
* Docker Compose

#### Formatter

Install the google-java-formatter plugin for your IDE (Intellij or Eclipse) for source code edits:

https://github.com/google/google-java-format

## Project Archtecture

![Clean Architecture](http://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

For the application structure, the Clean Architecture was used which leaves the application separate in layers that keep the inner layers independent of the outer layers and separate well the responsibility of each part from the project.

* **Entities** - Layer that encapsulate the business entities and has the specific business rules.
* **User cases** - Layer that encapsulate all application rules.
* **Interface** - Layer that has the purpose of convert data in the most accessible and convenient way possible to the Entities and User Cases layers.
* **Frameworks** - Layer that has tools like database, user interface, etc.

## Infrastructure

To maintain good application performance, the docker was used as a containerization technology to create a Load Balance server (NGINX), which has a direct route to a number N of URL-SHORTENER instances pointed to a database (MongoDB) as the image below:

![URL Shortener Infra](https://i.imgur.com/B8pH6dm.png)

### Project Startup

##### Project startup requires maven, docker and docker-compose

```
# cd  url-shortener
# mvn clean package dockerfile:build && docker-compose -f ./docker/docker-compose-local.yml up --scale app=2 -d
```

The number of instances to be executed is reported at the end of the command, where app=2 (Number of application instances).

Application users will be created automatically at the start of the application.

## Rest API

All API documentation can be found via the /swagger-ui.html path when the project is being executed as below image:

![URL Shortener Swagger-UI](https://i.imgur.com/1QJng4d.png)

**Example:** http://35.198.6.215:8080/swagger-ui.html

### Application Users:

**Admin User:**

```
username: admin
password: !@AdminPassword#$123

CURL de exemplo para login:

curl -X POST \
  http://35.198.6.215:8080/oauth/token \
  -H 'authorization: Basic dXJsLXNob3J0ZW5lcjpFZHZYQ2Y4NDVRbE4=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'grant_type=password&username=admin&password=!%40AdminPassword%23%24123'

```

**Common User:**

```
username: user
password: !@UserPassword#$123

CURL de exemplo para login:

curl -X POST \
  http://35.198.6.215:8080/oauth/token \
  -H 'authorization: Basic dXJsLXNob3J0ZW5lcjpFZHZYQ2Y4NDVRbE4=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -d 'grant_type=password&username=user&password=!%40UserPassword%23%24123'

```

### Appication Paths:

**Public Paths:**
```
GET - /short-url/{shortUrlId} - Get a Short URL from an ID


REQUEST:

http://35.198.6.215:8080/short-url/5c6c6c8e8b87bc0001567693

```

**Authenticated Paths:**
```
POST - /short-url/create - Create a Short URL (USER E ADMIN)

REQUEST:

curl -X POST \
  http://35.198.6.215:8080/short-url/create \
  -H 'authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTA2MTMxMTEsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMWFjOTdmMzUtYzAxMy00MmIxLTgwODQtMTExMjliNGExMjM4IiwiY2xpZW50X2lkIjoidXJsLXNob3J0ZW5lciIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSIsInRydXN0Il19.NFrLqSwu9wXUZfUUmcJyzM4X0ZGTosI1xwEWgjW5nsN7ngTYYxABh1f4v6rnOv0UWj6-5bBq_u6zQWDKH0gUyFp9ERXIp0PHtXknLPsIfbGWZooUB5_Cg2hkFyJKvBBz9giDhgk4bXuFYZrVybxDTcnOpMuJGiPXHUOVhy0CmQHM3MTmEx3H9TWh7-DL2yXD2zoV3diNtcXHDgVICZgl9vmgZtenXsTpv30-w3mWIwG9v2noguYnM3Kt5qUTGsBMZXTSS1quTOXpX9otgIMmZoAvKMJihjcym74CcRPNJf_MtusHX9OTaiTC-m7QaAhhHK52kdAC2WRLXp21fnd7LA' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"url": "https://start.spring.io/"
}'

RESPONSE: 

{
    "shortUrl": "http://35.198.6.215:8080/short-url/5c6c6c8e8b87bc0001567693"
}
```

```
GET - /short-url/admin/statistics - Return Short URL statistics (ADMIN)

REQUEST:

curl -X GET \
  'http://35.198.6.215:8080/short-url/admin/statistics?shortUrl=http%3A%2F%2F35.198.6.215%3A8080%2Fshort-url%2F5c6c6c8e8b87bc0001567693' \
  -H 'authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTA2MTMyOTcsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiIxYTI2ZTYwYy1lZGNkLTQwM2EtOGNhNy02NTY5MGJmZjg2ZDQiLCJjbGllbnRfaWQiOiJ1cmwtc2hvcnRlbmVyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXX0.NlRxbkYHZfDhlwd2HyrwER6bBMwNIU-eEKjtRamqct1A31GnxBaBeZlPUC_V7u0Q93k-dPVQXMqTGvTLXXiRigLFR6-hKA8ni_BFngydxg3Wr3rWRmRvpfY8DapOM2JeiwmPS24yVfH0sS9gIX-RCmyHC7zkPgzzKIJaEJGZoPI1-dsXfOEBFTZEOPSHNyG9qWtIXykfu41laXDWQahvNxFv7r0pP6IG5T8QmsPHgPYp4_QlgtCSr7cnYv9JWSdev0mf-Fqv0BWZ2W6bJ1tyHeENDDV6WTaka06ozVjfz15E9r6y7h3X-8wewl9Bnja_hBuE5h7co7bRZIU6ER8v7A' \
  -H 'cache-control: no-cache'

RESPONSE:

{
    "shortUrl": {
        "url": "https://start.spring.io/"
    },
    "numberOfRequests": 3,
    "lastRequest": {
        "requestDateTime": "2019-02-19T20:54:38.216"
    },
    "lastRequests": [
        {
            "requestDateTime": "2019-02-19T20:54:12.404"
        },
        {
            "requestDateTime": "2019-02-19T20:54:34.671"
        },
        {
            "requestDateTime": "2019-02-19T20:54:38.216"
        }
    ]
}

```
