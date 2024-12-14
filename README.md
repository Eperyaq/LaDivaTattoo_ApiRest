# **1.- Nombre: La Diva Tattoo**

# Idea del proyecto 
He pensado en una api rest la cual permitirá gestionar un estudio de tatuajes, proporcionando funcionalidades para manejar usuarios, artistas, citas.
La API facilitará la conexión entre clientes y artistas, permitiendo la administración eficiente de citas y recursos del estudio.

# Justificación del proyecto
He pensado en este proyecto ya que me viene bien tener algo planeado para mi tfg y aun asi he visto que es un buen programa aparte de para practicar para aprender mejor SpringBoot y reforzar conocimientos.

---
# **2.- Tablas a usar**

## Tabla 1: Usuarios
## Tabla 2: Artista
## Tabla 3: Citas

---
# Descripción detallada

## Tabla: `Usuario`

### Descripción:

Esta tabla gestiona toda la información relacionada con los usuarios del sistema, incluyendo tanto clientes como administradores.
Cada usuario tiene un identificador único, información de contacto, rol en el sistema, y credenciales encriptadas para garantizar la seguridad.
Es esencial para la autenticación, autorización, y gestión de los perfiles de los usuarios.

| Campo    | Tipo          | Descripción                                   |
|----------|---------------|-----------------------------------------------|
| `id`     | INT (PK)      | Identificador único del usuario.              |
| `nombre` | VARCHAR(100)  | Nombre completo del usuario.                  |
| `email`  | VARCHAR(150)  | Correo electrónico único del usuario.         |
| `contra` | VARCHAR(255)  | Contraseña encriptada.                        |
| `numtel` | VARCHAR(15)   | Número de teléfono del usuario.               |
| `rol`    | ENUM          | Rol del usuario: `cliente` o `administrador`. |
| `citas`       | LIST(CITAS) | Lista de citas que tiene el usuario           |


## Tabla: `Artistas`

### Descripción: 

Esta tabla almacena la información de los artistas disponibles en el sistema, incluyendo su nombre y especialización.
Sirve para identificar a los artistas y su área en la que es experto, permitiendo a los usuarios elegir un artista 
específico según sus preferencias o necesidades para las citas.

| Campo          | Tipo          | Descripción                                    |
|----------------|---------------|------------------------------------------------|
| `id`           | INT (PK)      | Identificador único del artista.               |
| `nombre`       | VARCHAR(100)  | Nombre del artista.                            |
| `especialidad` | VARCHAR(100)  | Especialización del artista (e.g., realismo).  |
| `citas`       | LIST(CITAS) | Lista de citas que tiene el artista                   |


## Tabla: `Citas`

### Descripción:

Esta tabla gestiona el registro de citas entre los usuarios y los artistas. Incluye información clave como el usuario que solicitó la cita,
el artista asignado, la fecha de la cita y una descripción del diseño o solicitud del cliente.
Facilita la organización y seguimiento de las citas programadas en el sistema.

| Campo         | Tipo        | Descripción                                           |
|---------------|-------------|-------------------------------------------------------|
| `id`          | INT (PK)    | Identificador único de la cita.                       |
| `id_usuario`  | INT (FK)    | Relación con `usuario`. Usuario que solicitó la cita. |
| `id_artista`  | INT (FK)    | Relación con `artista`. Artista asignado.             |
| `fecha`       | DATE        | Fecha de la cita.                                     |
| `descripcion` | TEXT        | Descripción del diseño solicitado.                    |


--- 
![Diagrama Entidad relacion de las tablas Usuario, Citas, Artistas](Diagrama%20Entidad-Relacion.jpeg)


----------

# EndPoints

## **Usuarios**

1. **Crear usuario**
    - **POST** `/usuarios`
    - Crea un nuevo usuario en el sistema.
    - **Lógica de negocio**
      - Comprobar que la contraseña tenga minimo 5 dígitos y que contenga numero y mayus
      - Que el email sea único (comprobar válido)
      - Que el telefono no se repita
    - **Quien puede usar este endpoint** 
      - Cualquier persona
    - **Errores:**
       - `400 Bad Request`: Datos faltantes o formato inválido.
       - `409 Conflict`: El correo electrónico ya está registrado.


2. **Obtener todos los usuarios**
    - **GET** `/usuarios`
    - Devuelve una lista de todos los usuarios registrados.
    - **Quien puede usar este endpoint**
      - Admin
    - **Errores:**
      - `404 Not Found`: No se encuentran los usuarios.


3. **Obtener usuario por ID**
    - **GET** `/usuarios/{id}`
    - Devuelve la información de un usuario específico.
    - **Quien puede usar este endpoint**
      - Admin
      - La persona logueada debe ser la misma que realiza la busqueda
   - **Errores:**
     - `404 Not Found`: Usuario no encontrado.
     
     

4. **Actualizar usuario**
    - **PUT** `/usuarios/{id}`
    - Actualiza la información de un usuario.
    - **Lógica de negocio**
      - Comprobar que la contraseña tenga minimo 5 dígitos y que contenga numero y mayus
      - Que el email sea único
      - Que el telefono no se repita
    - **Quien puede usar este endpoint**
      - Admin
    - **Errores**
      - `404 Not Found`: El usuario que se va a actualizar no se encuentra
      

5. **Eliminar usuario**
    - **DELETE** `/usuarios/{id}`
    - Elimina un usuario del sistema.
    - **Quien puede usar este endpoint**
      - Admin
    - **Errores**
      - `404 Not Found`: El usuario que se va a borrar no se encuentra

6. **login**
    - **POST**
    - Inicia sesión un usuario
    - **Lógica de negocio**
        - Comprobar que el usuario y la contraseña coinciden
   - **Quien puede usar este endpoint**
     - Cualquiera
    - **Errores**
        - `404 Not Found`: El usuario que se va a actualizar no se encuentra
        - `401 Unauthorized`: El correo o la contraseña son incorrectos

---

## **Artistas**

1. **Crear artista**
    - **POST** `/artistas`
    - Crea un nuevo artista en el sistema.
    - **Lógica de negocio**
        - Comprobar que la contraseña tenga minimo 5 dígitos y que contenga numero y mayus
   - **Quien puede usar este endpoint**
     - Cualquier artista
    - **Errores**
        - `400 Bad Request`: Los datos introducidos no son correctos o no cumplen el formato


2. **Obtener todos los artistas**
    - **GET** `/artistas`
    - Devuelve una lista de todos los artistas registrados.
    - **Quien puede usar este endpoint**
       - Admin
    - **Errores**
      - `404 Not Found`: El Artista no se encuentra.
           


3. **Obtener artista por ID**
    - **GET** `/artistas/{id}`
    - Devuelve la información de un artista específico.
    - **Quien puede usar este endpoint**
       - Admin
    - **Errores**
        - `404 Not Found`: Artista no encontrado.


4. **Actualizar artista**
    - **PUT** `/artistas/{id}`
    - Actualiza la información de un artista.
    - **Lógica de negocio**
        - Comprobar que la contraseña tenga minimo 5 dígitos y que contenga numero y mayus
    - **Quien puede usar este endpoint**
         - Admin
    - **Errores**
        - `400 Bad Request`: los datos introducidos para actualizar no son correctos.
        - `404 Not Found`: El artista a actualizar no se encuentra.

5. **Eliminar artista**
    - **DELETE** `/artistas/{id}`
    - Elimina un artista del sistema.
    - **Quien puede usar este endpoint**
      - Admin
    - **Errores**
      - `404 Not Found`: El artista a eliminar no se encuentra.
      

---

## **Citas**

1. **Crear cita**
    - **POST** `/citas`
    - Programa una nueva cita entre un usuario y un artista.
    - **Lógica de negocio**
         - Comprobar que la fecha de la cita sea correcta (no sea para ayer o una hora que ya esté ocupada)
    - **Quien puede usar este endpoint**
         - Un Usuario
    - **Errores**
        - `400 Bad Request`: los datos introducidos no son correctos.
        - `500 Internal Server Error`: Error en el servidor al recuperar las citas.
    

2. **Obtener todas las citas**
    - **GET** `/citas`
    - Devuelve una lista de todas las citas programadas.
    - **Quien puede usar este endpoint**
       - Admin
    - **Errores**
        - `500 Internal Server Error`: Error en el servidor al recuperar las citas.

3. **Obtener cita por ID**
    - **GET** `/citas/{id}`
    - Devuelve la información de una cita específica.
    - **Quien puede usar este endpoint**
       - Admin
    - **Errores**
        - `404 Not Found`: La cita no se encuentra.
        - `500 Internal Server Error`: Error en el servidor al recuperar las citas.

4. **Obtener citas por usuario**
    - **GET** `/usuarios/{id}/citas`
    - Devuelve todas las citas asociadas a un usuario específico.
    - **Quien puede usar este endpoint**
       - Admin
    - **Errores**
        - `404 Not Found`: La cita del usuario no se encuentra.
        - `500 Internal Server Error`: Error en el servidor al recuperar las citas.

5. **Obtener citas por artista**
    - **GET** `/artistas/{id}/citas`
    - Devuelve todas las citas asociadas a un artista específico.
    - **Quien puede usar este endpoint**
         - Admin
         - El mismo artista que busque sus citas (Lo mismo que al buscar usuario pero esta vez con un artista)
    - **Errores**
        - `404 Not Found`: La cita del artista no se encuentra.
        - `500 Internal Server Error`: Error en el servidor al recuperar las citas.

6. **Eliminar Cita**
   - **DELETE** `/citas/{id}`
   - Elimina una cita del sistema
   - **Quien puede usar este endpoint**
      - Admin
   - **Errores**
      - `404 Not Found`: La cita a eliminar no se encuentra.
      - `500 Internal Server Error`: Error en el servidor al recuperar los artistas.