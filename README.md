# totvs-backend

## Configuração de ambiente para o Backend

Requisitos:

* JDK 17+
* Apache Maven 3.9.2 
* preferencialmente IntelliJ
* OS: preferencialmente win32 x64

Instruções:

1- Instale o JDK, baixando-o no site oficial da Oracle, ou equivalente.
  * Utilize o isntalador ou caso opte pelo ZIP, é preciso configurar as varaveis de ambiente.
  * Verifique se a versão do JDK foi instalada corretamente com o comando ```java -version```
  
2- Instale o Maven
  * Caso opte pelo ZIP, é preciso configurar as varaveis de ambiente.
  * Verifique se a versão do Maven foi instalada corretamente com o comando ```mvn -v```

3- Clone o projeto pelo repositorio em uma pasta de sua escolha.
  * Abra pasta escolhida pelo IntelliJ ou IDE de sua escolha e depois acesse o diretorio do backend e aguarde os processo de indexação da IDE.
  * Inicie o projeto acessando pela classe main BackendApplication;
  * Caso as dependencias Maven não sejam carregadas corretamente, rode o comando ```mvn clean package install```
  * Por padrão, a aplicação ficara disponivel na porta 8080

# Testando a aplicação
É possivel testar as principais features da aplicação pelo:
  * Frontend: **http://localhost:4200/** onde é possivel cadastrar um cliente e visualizar a lista de clientes cadastrados.
  * Swagger: **http://localhost:8080/swagger-ui/index.html** onde é possivel testar as as principais e outras funcionalidades não requisitadas, portando não presentes no frontend.

**Obs.:** A aplicação esta associada ao um **banco de memoria H2**, portanto não há necessidade de configurar uma base de dados externa. Também esta implementada na aplicação a criação das tabelas e povoamento do banco com o flyway.
