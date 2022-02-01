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
### Conclusión
**PostgreSQL** tén unha maior compatibilidade con API, un soporte máis económico e unha escalabilidade máis robusta.  Necesitamos unha base de datos fácil de usar que poida personalizar as operacións, cun custo total de propiedade baixo. Como administradores de bases de datos, cremos que é o sistema óptimo tendo en conta as características do proxecto.

### Estrutura da base de datos
A estrutura da base de datos constaba nun inicio de dúas táboas: Empregados e Departamentos, no camiño de análise do problema houbo diversos cambios que deron noutra estrutura dos datos.

As novas táboas representan aos empregados, departamentos e as diferentes sedes da empresa. Cada táboa tén a súa chave primaria que non se poderá repetir, isto terase en conta á hora de programar en Java. Tamén terán chaves foráneas xa que cada departamento ten un xefe que tén que estar na lista de empregados e cada empregado pertence a un departamento. Isto é importante porque hai que verirficar que o xefe do departamento sexa empregado e que o departamento existe cando se introduce un empregado. Tamén temos que ter en conta de que o departamento se cree nunha sede xa existente, ou que se engada esta sede para que esta relación sexa completa.

O **modelo Entidade-Relación** é o seguinte:
![Modelo ER DrogaPOP](https://raw.githubusercontent.com/PauloVazquezAcosta/DrogaPOP/readme/images/Modelo%20ER%20DrogaPOP.png)
As táboas na base de datos teñen os atributos que se mostran seguidamente, na seguinte relación podemos ver o tipo de dato de cada táboa nas dúas linguaxes que usaremos: SQL e Java. Tamén o tipo de restrición que tén cada atributo de cara a poder programar tendo en conta a estrutura da base de datos.

#### Táboa Departamentos
Representa aos departamentos da empresa, nesta táboa estará o id do departamente, o seu nome, o xefe que será un dos empregados da empresa, a cidade de ubicación do departamento e o seu número de teléfono de contacto.
| Nome de columna  | Tipo de dato SQL | Tipo de dato Java| Características|
| ------------- | ------------- | ------------- | ------------- |
| id  | serial| int| Primary Key|
| nome  | character varying(25)  | String| Not NULL|
| xefe| smallint| int| Foreign Key / Not Null|
| ubicacion  | integer  | String| Foreign Key / Not NULL|
| telefono  | character varying(20)  | String|Not NULL|

#### Táboa Empregados
Representa aos empregados da empresa, nesta táboa poderemos atopar o id de empregado co seu dni, nome e apelidos, en que departamento traballa, cal é o seu posto de traballo, o teléfono e correo de contacto, a data do seu nacemento e o seu número da seguridade social.
| Nome de columna  | Tipo de dato SQL | Tipo de dato Java| Características|
| ------------- | ------------- | ------------- | ------------- |
| id  | integer| int| Primary Key|
| dni| character(9)  | String| UNIQUE / Not NULL|
| nome| character varying(25)| String| Not NULL|
| apelidos| character varying(25) | String| Not NULL|
| deptno| smallint  | int| Foreign Key / Not NULL|
| cargo| character varying(25)  | String | Not NULL|
| telefono| character(9)  | String| Not NULL|
| data_nacemento| date  | Date| Not NULL|
| email| character varying(30)  | String| Not NULL|
| numero_seg_social| character(12)  | String| Not NULL|

#### Táboa Sedes
Representa ás sedes da empresa, é a táboa á que fai referencia a táboa departamentos para definir a súa ubicación. Testa táboa poderemos atopar o id da sede, o nome da cidade na que se ubica e o teléfono de contacto xeral da sede.
| Nome de columna  | Tipo de dato SQL | Tipo de dato Java| Características|
| ------------- | ------------- | ------------- | ------------- |
| id  | serial| int| Primary Key|
| ubicacion  | character varying(20)  | String| Not NULL|
| telefono  | character varying(20)  | String|Not NULL|

#### Táboa Contratos
Representa os contratos de cada empregado da empresa. Nesta táboa podemos atopar o id de cada contrato, a que empregado pertence, en que data se comezou e en que data finalizará o mesmo,no caso de ser contrato indefinido este campo será NULL. Tamén se marca o salario mensual asociado ao contrato, que tipo de contrato é, os meses de duración dos contratos que sexan temporais e as horas semanais da xornada laboral.
| Nome de columna  | Tipo de dato SQL | Tipo de dato Java| Características|
| ------------- | ------------- | ------------- | ------------- |
| id  | integer| int| Primary Key|
| empregado| integer  | int| Foreign Key / Not NULL|
| data_inicio| date| Date| Not NULL|
| data_fin| date | Date| |
| salario| money  | Money| Not NULL|
| tipo| integer  | int | Foreign Key / Not NULL|
| meses_duracion| integer  | int| |
| horas_xornada_semanal| smallint  | int | Not NULL|

#### Táboa Contratos
Representa os tipos de contratos cos que a empresa pode contratar a empregados, son os 4 permitidos pola lexislación laboral española. Nesta táboa podemos atopar o id do contrato e o nome do tipo de contrato.
| Nome de columna  | Tipo de dato SQL | Tipo de dato Java| Características|
| ------------- | ------------- | ------------- | ------------- |
| id  | integer| int| Primary Key|
| nome  | character varying(20)| String| Not NULL|


