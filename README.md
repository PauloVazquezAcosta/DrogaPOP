<h1 align="center"> DrogaPOP </h1> 

## Contidos
- [Introdución](#introduci%C3%B3n)
- [Construído con](#constru%C3%ADdo-con)
- [Comezando](#comezando)
	- [Pre-requisitos](#pre-requisitos)
	- [Instalación](#instalaci%C3%B3n)
- [Base de datos](#base-de-datos)
- [Código](#Código)
- [Roadmap](#roadmap)
- [Estado do proxecto](#estado-do-proxecto)
- [Contribucións](#contribuci%C3%B3ns)
- [Licenza](#licenza)
- [Versionado](#versionado-)
- [Autores](#autores)
- [Expresións de Gratitude](#expresi%C3%B3ns-de-gratitude-)

## Introdución
_DrogaPOP é un programa que xestiona unha base de datos con información sobre unha empresa co mesmo nome, adicada ao sector da droguería, nela tén a información dos seus departamentos e empregados. Este software permite interactuar cunha base de datos para mostrar a información almacenada, insertar novos datos, actualizar os existentes e finalmente borralos cando non se necesiten.__

## Construído con
A estrutura do programa creouse utilizando as seguintes ferramentas:
- [Microsoft Paint](https://support.microsoft.com/es-es/windows/abrir-microsoft-paint-ead1dc5c-abc4-fd2c-d81e-ebb013fbc113) Editor de imaxes utilizado para realizar o deseño do modelo UML.
- [PGModeler 0.9.4-beta1](https://pgmodeler.io/) Modelador de bases de datos PostgreSQL.
- [pgAdmin 4 6.4](https://www.pgadmin.org/) Xestor de bases de datos PostgreSQL.
- [PostgreSQL 14](https://www.postgresql.org/) Sistema de xestión de bases de datos relacionais orientado a obxectos.
- [Docker](https://www.docker.com/) Despliegue de aplicaciones dentro de contenedores de software.
- [Hibernate ORM 5.6.4.Final](http://handlebarsjs.com/) Ferramenta de mapeo obxecto-relacional para Java.
- [Apache NetBeans IDE 12.4](https://netbeans.apache.org/download/index.html) Entorno de desenvolvemento (IDE) para Java.
- [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) Entorno de desenvolvemento (IDE) para o desenvolvemento de software.
- [VS Code](https://code.visualstudio.com/) Editor de código fonte.
- [Maven](https://maven.apache.org/)  Manexador de dependencias.

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
  3. Abre o proxecto no IDE e compila o código.

## Base de datos
A base de datos está detallada no documento [DATABASE.md](https://github.com/PauloVazquezAcosta/DrogaPOP/blob/main/docs/DATABASE.md)

## Código
O código pode ser visto directamente no [repositorio](https://github.com/PauloVazquezAcosta/DrogaPOP/tree/main/DrogaPOP/src/main/java/drogapop).

### Diagrama UML
O diagrama UML, feito en **Paint** polo noso [equipo especializado en arte](https://github.com/45NGC), que serviu de base para iniciar este proxecto é o seguinte:
![Diagrama UML DrogaPOP](https://raw.githubusercontent.com/PauloVazquezAcosta/DrogaPOP/main/images/diagrama_UML.png)

## Roadmap
- [x] Análise do problema
- [x] Deseño dunha solución con modelo ER e diagrama UML
- [x] Despregada a infraestrutura da base de datos
- [x] Introducidos os datos na base de datos
- [x] Importada a librería ORM Hibernate
- [x] Creadas en Java as clases que representan a cada fila da base de datos
- [x] Programación dos métodos que se comunicarán co usuario no Main
- [x] Programación dos métodos que se comunicarán coa base de datos en Connect
- [x] Probas de funcionamento do programa
- [x] Documentación

## Estado do proxecto
O proxecto está coa versión 1.0.0 funcional.

## Contribucións
As contribucións son o que fai que a comunidade de código aberto sexa un lugar incrible para aprender, inspirar e crear. Calquera contribución que fagas é **moi apreciada**.

Se tes unha suxerencia para mellorar isto, por favor, fai un  fork do repo e crea un pull  request. Tamén podes abrir un issue coa etiqueta _#mellora_.

Non esquezas dar unha estrela ao proxecto. Grazas de novo!

1. Bifurca o proxecto
2. Crea a túa rama de funcionalidades (`git checkout -b FantasticaFuncionalidade`)
3. Fai Commit dos teus cambios (`git commit -m 'Engadida unha funcionalidade fantástica'`)
4. Fai Push á rama (`git push origin FantasticaFuncionalidade`)
5. Abre unha Pull Request

## Licenza
Distribuído baixo _[GNU General Public License](https://www.gnu.org/licenses/gpl-3.0.html)_. Ver [`LICENSE.md`](https://github.com/PauloVazquezAcosta/DrogaPOP/blob/main/docs/LICENSE.md) para máis información.

## Código de conduta
Este Código de Conducta é unha adaptación do [Contributor Covenant](https://www.contributor-covenant.org/es/version/2/0/code_of_conduct.html), versión 2.0. Ver [`CODIGO_CONDUTA.md`](https://github.com/PauloVazquezAcosta/DrogaPOP/blob/main/docs/CONTRIBUTOR_COVENANT.md) para máis información.

## Versionado 📌

Usamos [SemVer](https://semver.org/lang/es/) para o versionado. Para todas as versións dispoñibles, mira os [tags no repositorio](https://github.com/PauloVazquezAcosta/DrogaPOP/tags).

## Autores
-   **Yudaisy Ramos**  -  _Programación_  -  [YudaRamos](https://github.com/YudaRamos)
-   **Nicolás Gómez**  -  _Programación_  -  [45NGC](https://github.com/45NGC)
-   **Miguel Díaz**  -  _Hibernate_  -  [mayco1](https://github.com/mayco1)
-  **Paulo Vázquez**  -  _Backend e Documentación_  -  [PauloVazquezAcosta](https://github.com/PauloVazquezAcosta)

## Expresións de Gratitude 🎁

* Comenta a outros profes sobre este proxecto. 📢
* Invita una cervexa 🍺 a alguén do equipo.
* Dá as grazas publicamente. 🤓
* Ponnos unha notaza de fin de curso por este Readme.
* Dálle like a este proxecto :thumbsup:
