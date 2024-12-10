# **1.- API REST La Diva Tattoo**

He elegido este tema enfocado hacia mi trabajo de fin de grado y es una api rest la cual permitirá gestionar un estudio de tatuajes, proporcionando funcionalidades para manejar usuarios, artistas, citas.
La API facilitará la conexión entre clientes y artistas, permitiendo la administración eficiente de citas y recursos del estudio.

He pensado en este proyecto ya que me viene bien tener algo planeado para mi tfg y aun asi he visto que es un buen programa aparte de para practicar para aprender mejor SpringBoot y reforzar conocimientos.

---
# **2.- Tablas que a usar**

## Tabla 1: Usuarios
## Tabla 2: Artista
## Tabla 3: Citas

---
### Tabla: `Usuario`
| Campo    | Tipo          | Descripción                                   |
|----------|---------------|-----------------------------------------------|
| `id`     | INT (PK)      | Identificador único del usuario.              |
| `nombre` | VARCHAR(100)  | Nombre completo del usuario.                  |
| `email`  | VARCHAR(150)  | Correo electrónico único del usuario.         |
| `contra` | VARCHAR(255)  | Contraseña encriptada.                        |
| `numtel` | VARCHAR(15)   | Número de teléfono del usuario.               |
| `rol`    | ENUM          | Rol del usuario: `cliente` o `administrador`. |


### Tabla: `Artistas`
| Campo          | Tipo          | Descripción                                    |
|----------------|---------------|------------------------------------------------|
| `id`           | INT (PK)      | Identificador único del artista.               |
| `nombre`       | VARCHAR(100)  | Nombre del artista.                            |
| `especialidad` | VARCHAR(100)  | Especialización del artista (e.g., realismo).  |


### Tabla: `Citas`
| Campo         | Tipo          | Descripción                                           |
|---------------|---------------|-------------------------------------------------------|
| `id`          | INT (PK)      | Identificador único de la cita.                       |
| `id_usuario`  | INT (FK)      | Relación con `usuario`. Usuario que solicitó la cita. |
| `id_artista`  | INT (FK)      | Relación con `artista`. Artista asignado.             |
| `fecha`       | DATE          | Fecha de la cita.                                     |
| `descripcion` | TEXT          | Descripción del diseño solicitado.                    |

--- 
![Diagrama Entidad relacion de las tablas Usuario, Citas, Artistas](Diagrama%20Entidad-Relacion.jpeg)
