Autores ✒️
●     Nicolas Fiasche  - Desarrollo cliente.
●     Franco Ponce - Desarrollo servidor.
●     Francisco Armendariz - Desarrollo Front End.
●     Valentin Topssian Oliva - Desarrollo servidor.
●    Giuliano Bacon  – Desarrollo servidor.

Prerequisitos Front ends
Instalado Visual Studio Code. https://code.visualstudio.com/

Instalado NodeJS. https://nodejs.org/es/

Para ejecutar el servidor de vista se debe ubicar dentro de la carpeta FrontEnd

Por medio de terminal ejecutar el comando yarn install para instalar las dependencias de node.

Ejecutar el comando yarn dev.

La vista se ejecutara en puerto 3000 , http://localhost:3000

Prerequisitos Back End Servidor

Instalar gradle https://gradle.org/install/
Tener instalado MySql Workvench y crear una base de datos ( create database init_db )
En una terminal que apunte a la carpeta chefEnCasa.server ejecutar el comando: gradle build  para generar los archivos correspondientes de grpc
Luego compilar el servidor.


Prerequisitos Back End Cliente

Instalar visual studio con configuracion para desarrollo en .NET
Abrir la carpeta BackEnd-Client dentro de visual y compilar el archivo GrpcClientApi


