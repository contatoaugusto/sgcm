# sgcm
******************** Pré-Requisitos **************************************

JDK 8
https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

NetBeans
https://netbeans.org/downloads/8.2

MySql - Versao 5.1.72
https://downloads.mysql.com/archives/community/

WorkBench Versão 6.3 (64 Bits)
https://downloads.mysql.com/archives/workbench/

******************** Configuração do recurso no Glassfish ******************

Configurar o jndi-name="jdbc/sgcmJNDI" na instância do GlassFish em:
http://localhost:4848/

Resources > Add Resources > Importar o arquivo glassfish-resources.xml que está no projeto
Dai o recurso criado aparecerá em: JDBC Resources
