
# Sobre DrogaPOP
_DrogaPOP é un programa que xestiona unha base de datos con información sobre unha empresa co mesmo nome, adicada ao sector da droguería, nela tén a información dos seus departamentos e empregados. Este software permite interactuar cunha base de datos para mostrar a información almacenada, insertar novos datos, actualizar os existentes e finalmente borralos cando non se necesiten.__

## Construído con
A estrutura do programa creouse utilizando as seguintes ferramentas:
- [PGModeler 0.9.4-beta1](https://pgmodeler.io/) Modelador de bases de datos PostgreSQL.
- [pgAdmin 4 6.4](https://www.pgadmin.org/) Xestor de bases de datos PostgreSQL.
- [PostgreSQL 14](https://www.postgresql.org/) Sistema de xestión de bases de datos relacionais orientado a obxectos.
- [Docker](https://www.docker.com/) Despliegue de aplicaciones dentro de contenedores de software.
- [Hibernate ORM 5.6.4.Final](http://handlebarsjs.com/) Ferramenta de mapeo obxecto-relacional para Java.
- [Apache NetBeans IDE 12.4](https://netbeans.apache.org/download/index.html) Entorno de desenvolvemento (IDE) para Java.
- [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) Entorno de desenvolvemento (IDE) para o desenvolvemento de software.
- [VS Code](https://code.visualstudio.com/) Editor de código fonte.
- [Maven](https://maven.apache.org/)  Manexador de dependencias.

## Base de datos
A primeira elección ao crear este software é escoller o sistema xestor de base de datos. Tiñamos a opción inicial de utilizar **Oracle** pero a liberdade de utilizar calquera outro sistema mentres se xustificara. Neste caso a base de datos está despregada en **PostgreSQL** como se indicou no apartado anterior: [Construído con](https://github.com/PauloVazquezAcosta/DrogaPOP/blob/readme/README.md#constru%C3%ADdo-con). 

### Motivos de elección de PostgreSQL

#### Funcionalidade
-   _Alta disponibilidade, está despregada nun contenedor [Docker](https://www.docker.com/)  nun servidor remoto propio, á que accedemos a través de [pgAdmin](https://www.pgadmin.org/)._
- _Compatible con ACID._
####  Escalabilidade 
-   _Máis escalable debido ás súas características de código aberto._
-   _As bases de datos acomodan calquera volume de datos._
-   _Despregouse nun [Docker](https://www.docker.com/) nun servidor propio, polo que a instalación e a súa expansión é totalmente gratuita._
#### Seguridade
-   _Ofrece roles e roles herdados que permiten estabelecer permisos._
-   _Admite SSL nativo que axuda a cifrar as comunicacións do servidor._
-   _Proporciona controis de acceso adicionais a través de [SE-PostgreSQL](https://wiki.postgresql.org/wiki/SEPostgreSQL_Documentation) que se basean na política de seguridade de [SELinux](https://www.redhat.com/es/topics/linux/what-is-selinux)._
#### Apoio
- _Comunidade activa que ofrece soporte en liña gratuito a través de blogs, correos electrónicos, código e outros canles._
-   _O custo de contratar programadores da comunidade de PostgreSQL para soporte premium é menor que o dun especialista comparable de Oracle._
-   _Tamén hai dispoñibles proveedores de soporte de terceiros, como [EnterpriseDB](https://www.enterprisedb.com/) y 2nd [Quadrant](https://www.2ndquadrant.com/es/)._
#### Compatibilidade e replicación
-  _ Alta disponibilidade a través de Streaming Replication no caso de implementarse._
-   _A replicación master-slave proporciona aos programadores un rendemeento impecable durante a copia de seguridade, a asignación de tarefas e a agrupación en clústeres._
-  _Compatibilidade co marco **ORM**._
-  _ Soporte para un grupo máis grande de API que Oracle, o que o fai máis compatible con moitas aplicacións, complementos e entornos SQL._
-   _Compatibilidade con bibliotecas JDBC, ODBC, OLEDB e .Net._
#### Cumprimento SQL
-  _Sintaxe SQL máis sinxela, xa que PostgreSQL segue o estándar SQL._
-   _A extensións de procedemento incorporadas non estándar están dispoñibles a través de [pg/SQL](https://es.wikipedia.org/wiki/PL/PgSQL)._
#### Copia de seguridade e recuperación
-   _O proceso de recuperación de datos é sinxelo._
-   _PGdump e pgbasebackup son solucións de respaldo de bases de datos simples e directas._

**PostgreSQL** tén unha maior compatibilidade con API, un soporte máis económico e unha escalabilidade máis robusta.  Necesitamos unha base de datos fácil de usar que poida personalizar as operacións, cun custo total de propiedade baixo. Como administradores de bases de datos, cremos que é o sistema óptimo tendo en conta as características do proxecto.

### Estrutura da base de datos
![Modelo ER DrogaPOP](https://raw.githubusercontent.com/PauloVazquezAcosta/DrogaPOP/readme/images/Modelo%20ER%20DrogaPOP.png)


## Comezando
_Estas instrucións permitirán ter unha copia do proxecto en funcionamento na túa máquina local para propósitos de desenvolvemento e probas._
### Pre-requisitos
Instalar un IDE para abrir o proxecto, aquí temos os que se utilizaron para desenvolver este código:
> - [Apache NetBeans](https://netbeans.apache.org/download/nb124/nb124.html)
> - [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/#section=windows)
> - [VS Code](https://code.visualstudio.com/Download)

### Instalación
1. Clonar o repositorio de GitHub:

  ```sh
  git clone https://github.com/PauloVazquezAcosta/DrogaPOP.git
  ```
  2. Se non estiveran instaladadas, configurar as dependencias de ORM Hibernate
```sh
<dependency>
   <groupId>org.hibernate</groupId>
   <artifactId>hibernate-core</artifactId>
   <version>5.6.4.Final</version>
</dependency>
```

## Versionado 📌

Usamos [SemVer](https://semver.org/lang/es/) para o versionado. Para todas as versións dispoñibles, mira os [tags no repositorio](https://github.com/PauloVazquezAcosta/DrogaPOP/tags).

## Autores
-   **Yudaisy Ramos**  -  _Programación_  -  [YudaRamos](https://github.com/YudaRamos)
-   **Nicolás Gómez**  -  _Programación_  -  [45NGC](https://github.com/45NGC)
-   **Miguel Díaz**  -  _Programación_  -  [mayco1](https://github.com/mayco1)
-  **Paulo Vázquez**  -  _Documentación e Backend_  -  [PauloVazquezAcosta](https://github.com/PauloVazquezAcosta)

## Expresións de Gratitude 🎁

* Comenta a outros profes sobre este proxecto. 📢
* Invita una cervexa 🍺 a alguén do equipo.
* Dá as grazas publicamente. 🤓
* Ponnos unha notaza de fin de curso por este Readme.
* Dálle like a este proxecto :thumbsup:

