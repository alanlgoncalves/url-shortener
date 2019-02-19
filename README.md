# URL-SHORTENER

## Objetivo

Projeto para realizar o encurtamento de URLs, realizando o redirecionamento para a URL original, e guardando estatísticas
da quantidade de acessos, ou dados dos últimos acessos realizados da URL encurtada.

## Tecnologias

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

Instalar o pluguin do google para sua IDE (Intellij ou Eclipse) para edições no código fonte:

https://github.com/google/google-java-format

## Arquitetura do Projeto

![Clean Architecture](http://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

Para a estrutura da aplicação, foi utilizada a Clean Architecture que deixa a aplicação separada em
camadas que mantem as camadas internas independentes das camadas externas e separada bem a responsabilidade de cada parte
do projeto.

* **Entities** - Camada que encapsula as entidades do negócio.
* **User cases** - Camada que contém as regras de negócios mais específicas do sistema.
* **Interface** - Camada que tem como finalidade converter dados da maneira mais acessível e conveniente possível para as 
camadas Entities e User Cases.
* **Frameworks** - Camada que é composta por ferramentas como banco de dados, interface do usuário, etc.

## Infraestrutura

Para manter a boa performance da aplicação, foi utilizado o docker como tecnologia de conteinerização para criar um 
servidor Load Balance (NGINX), que possui rota direta para um número N de instâncias do URL-SHORTENER apontados para um 
banco de dados (MongoDB) conforme imagem abaixo:

![URL Shortener Infra](https://i.imgur.com/EYpo3qw.png)

### Execução do projeto

##### Para execução do projeto, é necessário o maven, docker e o docker-compose

```
# cd  url-shortenet
# mvn clean package dockerfile:build && docker-compose -f ./docker/docker-compose-local.yml up --scale app=2 -d
```

O número de instâncias a ser executada é informada no final do comando, onde app=2 (Número de instâncias da aplicação).

Os usuários da aplicação serão criados automaticamente no início da aplicação.

## Rest API

Toda a documentação das APIs pode ser encontrada através do caminho /swagger-ui.html quando o projeto estiver sendo 
executado conforme imagem abaixo:

![URL Shortener Swagger-UI](https://i.imgur.com/1QJng4d.png)

### Usuários da aplicação:

**Usuário administrador:**

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

**Usuário comum:**

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

### Rotas da aplicação:

**Rotas públicas:**
```
GET - /short-url/{shortUrlId} - Get a Short URL from an ID


REQUEST:

http://35.198.6.215:8080/short-url/5c6c6c8e8b87bc0001567693

```

**Rotas autenticadas:**
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