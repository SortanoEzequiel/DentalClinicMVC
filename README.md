🦷 DentalClinicMVC

API REST para la gestión de una clínica odontológica desarrollada con Spring Boot siguiendo el patrón MVC, con autenticación basada en JWT.

Permite:

Registro y login de usuarios

Gestión de odontólogos

Gestión de pacientes

Gestión de turnos

Protección de endpoints mediante token

🚀 Tecnologías utilizadas

Java 17
Spring Boot
Spring MVC
Spring Data JPA
Spring Security
JWT (JSON Web Token)
H2 Database (entorno de desarrollo)
Maven
Postman (para pruebas)

🏗️ Arquitectura

El proyecto sigue arquitectura en capas:

Controller → Service → Repository → Entity

Controller → Define los endpoints REST

Service → Lógica de negocio

Repository → Acceso a base de datos

Entity → Modelado de datos

Security → Configuración JWT y autenticación

⚙️ Instalación y ejecución
1️⃣ Clonar el repositorio
git clone https://github.com/SortanoEzequiel/DentalClinicMVC.git
cd DentalClinicMVC
2️⃣ Ejecutar la aplicación
mvn spring-boot:run

La aplicación corre en:

http://localhost:8080
🔐 Autenticación

La API utiliza autenticación JWT.

📌 1️⃣ Registro de usuario
POST /auth/register
{
  "firstname": "Juan",
  "lastname": "Perez",
  "email": "perez1234@gmail.com",
  "password": "Arguero"
}
📥 Respuesta:

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
📌 2️⃣ Login
POST /auth/login
{
  "email": "perez1234@gmail.com",
  "password": "Arguero"
}

📌 3️⃣ Usar el token

En Postman:

Authorization → Bearer Token
Pegar el token obtenido

Header automático:

Authorization: Bearer TU_TOKEN
👨‍⚕️ Endpoints Odontólogos
➕ Crear odontólogo
POST /odontologos
{
  "registration": 345678,
  "name": "HOLA",
  "lastName": "Gonzalez"
}
📅 Endpoints Turnos
➕ Crear turno
POST /turnos
{
  "dentist_id": 1,
  "patient_id": 2,
  "date": "2026-05-27"
}
🧪 Base de datos

El proyecto utiliza H2 en memoria para desarrollo.

Consola H2
http://localhost:8080/h2-console
📌 Flujo completo de uso

1)Registrar usuario

2)Obtener token

3)Loguearse

4)Crear odontólogos y pacientes

5)Crear odontologos/dentistas/turnos

🧠 Conceptos aplicados

Autenticación con JWT

Filtros personalizados en Spring Security

Relaciones entre entidades (OneToMany / ManyToOne)

Arquitectura MVC

Manejo de excepciones

API RESTful
