
# ForoHub

ForoHub es una API REST para la gestión de un foro. Permite a los usuarios autenticados interactuar con los tópicos del foro, incluyendo acciones como creación, consulta, actualización y eliminación.

## Características

- Autenticación de usuarios con JWT (JSON Web Token).
- Gestión de tópicos del foro.
- Seguridad implementada con Spring Security.
- Almacenamiento de datos en PostgreSQL.
- Migraciones de base de datos con Flyway.
- Pruebas unitarias y de integración.

## Requisitos

- Java 17
- Maven 3.6+
- PostgreSQL
- Insomnia o Postman (para pruebas)

## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu_usuario/forohub.git
   cd forohub

   cd forohub

    Configura la base de datos PostgreSQL:

        Crea una base de datos llamada forohub.

        Configura las credenciales en el archivo src/main/resources/application.properties:

        properties

    spring.datasource.url=jdbc:postgresql://localhost:5432/forohub
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña

Ejecuta las migraciones de Flyway:

bash

mvn flyway:migrate

Compila y ejecuta la aplicación:

bash

    mvn clean install
    mvn spring-boot:run

Uso
Autenticación

    Registra un usuario (puedes usar Insomnia o Postman):

    json

POST /auth/register
{
"username": "nuevo_usuario",
"password": "nueva_contraseña"
}

Inicia sesión para obtener el token JWT:

json

POST /auth/login
{
"username": "nuevo_usuario",
"password": "nueva_contraseña"
}

La respuesta incluirá el token JWT:

json

    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }

Gestión de Tópicos

    Crea un tópico (incluye el token JWT en el encabezado de la solicitud):

    json

POST /topicos
{
"titulo": "Nuevo Tópico",
"mensaje": "Este es el contenido del nuevo tópico."
}

Encabezado:

makefile

Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

Consulta los tópicos:

json

GET /topicos

Actualiza un tópico (incluye el token JWT en el encabezado de la solicitud):

json

PUT /topicos/{id}
{
"titulo": "Título Actualizado",
"mensaje": "Contenido actualizado del tópico."
}

Encabezado:

makefile

Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

Elimina un tópico (incluye el token JWT en el encabezado de la solicitud):

json

DELETE /topicos/{id}

Encabezado:

makefile

    Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

Contribuciones

¡Las contribuciones son bienvenidas! Por favor, abre un issue o envía un pull request para discutir posibles mejoras o correcciones.
Licencia