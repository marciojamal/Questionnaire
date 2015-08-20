
## Requisitos

Mysql Server 5.5 ou superior
JDK 1.6 ou superio
Glassfish 3.1

## Build

Instale todos os softwares de requisito mínimo.
### Mysql
Executa o script.sql para carregar as tabelas e o SCHEMA ou o dump_test.sql (com dados de teste), ambos na raiz do projeto.
*Opcional para testes: crie um usuário para o SCHEMA questionnaire, com permissões de select, update, delete, etc.

### Glassfish
Adicione o glassfiss na IDE, no caso do eclipse é necessário adicionar o Glassfish Tools.
Faça o download do mysql-connector-java e adicione em ../ glassfish3/glassfish/lib/
Inicie o Glassfish
No browser vá em http://localhost:4848/ e entre em Resource > JDBC > JDBC Connections Pools e clique em NEW, no POOL name coloque o nome de sua preferência para conexão, Resource Type: java.sql.driver, Database Driver Vendor: mysql e depois NEXT, no inferior da pagina preencha a tabela com os dados:
URL: jdbc:mysql://localhost:3306/ questionnaire?zeroDateTimeBehavior=convertToNull
User: seu user (root ou questionnaire)
Password: seu PASSWORD
E SAVE.
Em JDBC Resources, clique em NEW. E preencha
JNDI Name: jdbc/questionnaire
Pool Name: seu pool name criado anteriormente.
Status: Enabled
E SAVE.

Restart Server.
	
### IDE
Clone o projeto em sua workspace de preferência, usando a sua IDE de preferência importa o projeto como questionnaire e seus descendentes como projeto maven. No caso do eclipse “Existing maven Project”.
Execute: update maven Project e maven install(jUnit não implementado).

Adicione o projeto no servidor e no browser vá até http://localhost:8080/questionnaireWeb/

Em Administração use o usuário: marcio@jamal.re senha:jamal


Atenciosamente,
Márcio Jamal Resende


