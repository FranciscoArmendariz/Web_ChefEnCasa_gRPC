Autores ✒️
Nicolas Fiasche - Desarrollo cliente.
Franco Ponce - Desarrollo servidor.
Francisco Armendariz - Desarrollo Front End.
Valentin Topssian Oliva - Desarrollo servidor.
Giuliano Bacon – Desarrollo servidor.
Prerrequisitos Frontend
Asegúrate de cumplir con los siguientes requisitos antes de ejecutar el servidor de la vista:

Visual Studio Code instalado.
Node.js instalado.
Para ejecutar el servidor de vista, sigue estos pasos:

Ubícate dentro de la carpeta FrontEnd.
Ejecuta el comando yarn install en tu terminal para instalar las dependencias de Node.
Ejecuta el comando yarn dev.
La vista se ejecutará en el puerto 3000, http://localhost:3000.

Prerrequisitos Backend - Servidor
Para configurar el backend del servidor, sigue estos pasos:

Instala Gradle.
Asegúrate de tener instalado MySQL Workbench y crea una base de datos llamada init_db.
En una terminal que apunte a la carpeta chefEnCasa.server, ejecuta el comando gradle build para generar los archivos correspondientes de gRPC.
Compila el servidor.
Prerrequisitos Backend - Cliente
Para configurar el backend del cliente, sigue estos pasos:

Instala Visual Studio con la configuración para desarrollo en .NET.
Abre la carpeta BackEnd-Client dentro de Visual Studio y compila el archivo GrpcClientApi.
