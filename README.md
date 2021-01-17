# Sistema WEB para controle de cartórios.

Sistema WEB construido para consumir uma API que fornece serviços para controle de cartórios.

API: [https://github.com/mss1569/cartorio](https://github.com/mss1569/cartorio)

### Como rodar:

#### Usando Docker:
```docker run --name cartorio-front -p 8080:8080 mss1569/cartorio-front```

#### Usando Maven:
>```
>mvn -B package --file pom.xml
>java -jar target/cartorio-front-0.0.1-SNAPSHOT.jar
>```

### Utilizando:

A pagina inicial no sistema se encontra em [http://localhost:8080/](http://localhost:8080/), tendo uma listagem dos cartórios cadastrados, e opções editar, excluir e vizualizar, alem de um botão para cadastrar novos.
![list-notary](https://user-images.githubusercontent.com/33636621/104855561-39317200-58ec-11eb-8bbb-5e1654895db0.png)

Ao clicar na opção para exibir um cartório, será exibida uma pagina com as informações basicas do cartório e suas certidões, podendo editalas, excluilas, ou cadastrar novas.
![show-notary](https://user-images.githubusercontent.com/33636621/104855606-785fc300-58ec-11eb-9f4a-6e4d1a3bab02.png)

### Tecnologias:

- [x] Java 11
- [x] Maven
- [x] Spring Boot 2.4
- [x] Spring WEB
- [x] Thymeleaf
- [x] Lombok
- [x] ModelMapper
