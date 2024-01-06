La presente aplicacion esta desarrollada con un Arquitectura de desarrollo de tres capas.

Para iniciar el desarrollo se debe contar con una base de datos desarrollada en base a un analisis de negocio

El presente proyecto esta desarrollado con las siguentes herramientas:
    -Entorno de desarrollo: Apache Netbeans
    -Lenguaje de programacion: Java
    -Plantilla de proyecto: Java Ant
    -Base de datos: MySQL
    -Servidor: MariaDB
    -Control de version: GitHUb
    -Patron de desarrollo: Singleton

En este caso la base de datos es una base de datos MySQL que funciona con un servidor MariaDB

La estructura del proyecto es la siguente

Folder PATH listing for volume SSTRSSD1
Volume serial number is 9639-35BE
D:.
├───build
│   └───classes
│       ├───basedatos
│       ├───clases
│       ├───datos
│       │   └───interfaces
│       ├───entidades
│       ├───files
│       │   └───usuarios
│       ├───negocio
│       └───presentacion
│           └───imagenes
├───documentacion
│   └───RespaldoBaseDatos
├───nbproject
│   └───private
├───src
│   ├───basedatos
│   ├───clases
│   ├───datos
│   │   └───interfaces
│   ├───entidades
│   ├───files
│   │   └───usuarios
│   ├───negocio
│   └───presentacion
│       └───imagenes
└───test

El desarrollo del codigo fuente se hara en la carpeta src
Siguiendo la arquitectura de desarrollo en 3 capas las carpetas en src se organizaran de la siguente manera
Capa de presentacion
    -presentacion
Capa de logica de negocion:
    -negocio
Capa de acceso a datos
    -base datos
    -datos

Adicionalmente hay una carpeta llamada "entidades" que representa las entidades dentro de la base de datos

Y finalmente una carpeta "clases" que alberga clases complementarias para funcionalidades de la aplicacion

Los pasos a seguir para el desarrollo de la aplicacion son los siguentes:

1.- Analisis del negocio
2.- Desarrollo de la base de datos
3.- Albergar base de datos a un servidor
4.- Codificar Clase de conexion
5.- Codificar interface para CRUD
6.- Codificar Clases de Data access objects
7.- Codificar Clases de control
8.- Maquetar GUI para Escritorio virtual y modulos
9.- Codificar modulos correspondientes

1.- Analisis del negocio: 

Para el analisis del negocio se puede usar entrevistas o encuestas u otros metodos para obtener la informacion del funcionamiento del negocio y del sistema que se usa para el trabajo

2.- Desarrollo de la base de datos

Para desarrollar una base de datos relacional se tiene que 

-identificar las entidades y sus atributos

-Posteriormente las tablas intermedias

-Diseñar un modelo entidad relacion

-Diseñar un modelo logico relacional

-Elegir un sistema de gestion de base de datos: MySQL, SQLServer, Postgresql, etc

-Dentro del Sistema de gestion de base de datos, desarrollar el modelo fisico

3.- Albergar la base de datos en un servidor

El servidor puede ser de paga para puesta en produccion, por ejemplo SQLServer, MYSQL, o gratis como MariaDB que es un fork de MySQL y es gratis en puesta en produccion.

4.- Codificar clase Conexion

Para establecer conexion con una aplicacion Java se debe descargar el driver correspondiente para el lenguaje de programacion que se utilizara.

En el caso de un proyecto Java se descarga el driver JDBC de MYSQL para MYSQL y mariaDB y el JDBC de SQL server para sql server de microsoft

En la Clase conexion se debe hacer una cadena de conexion haciendo referencia al driver que se utilizara.

5.- Codificar interface para CRUD

En Java existen colecciones de metodos abstractos sin implementacion que pueden ser implementados en otras clases y se llaman interfaces.

En estas interfaces se puede definir las operaciones CRUD (Create, Read, Update, Delete) de una base de datos SQL

6.- Codificar clases de Data Access Object (DAO)
  Las clases DAO implementan la interfaz CrudSimpleInterface
 * y se debe sobreescribir los metodos abstractos de la interfaz
 * 
 * La clase RolDAO es la clase que interactua con la base de datos
 * 
 * Los Data Access Object (DAO) son clases que interactuan con la base de datos
 * y se encargan de realizar las operaciones basicas de la base de datos

