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

* *Entities* - Camada que encapsula as entidades do negócio.
* *User cases* - Camada que contém as regras de negócios mais específicas do sistema.
* *Interface* - Camada que tem como finalidade converter dados da maneira mais acessível e conveniente possível para as 
camadas Entities e User Cases.
* *Frameworks* - Camada que é composta por ferramentas como banco de dados, interface do usuário, etc.

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

#### Usuários da aplicação:

*Usuário administrador:*

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

*Usuário comum:*

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


