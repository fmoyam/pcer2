# **PCer2** - Sistema de Servicio Tecnico de Computadores

## Indice

1. [Descripcion del proyecto](#descripcion-del-proyecto)
2. [Integrantes](#integrantes)
3. [Arquitectura general](#arquitectura-general)
4. [Microservicios implementados](#microservicios-implementados)
5. [Tecnologias y bibliotecas utilizadas](#tecnologias-y-bibliotecas-utilizadas)
6. [Herramientas necesarias](#herramientas-necesarias)
7. [Bases de datos utilizadas](#bases-de-datos-utilizadas)
8. [Puertos utilizados](#puertos-utilizados)
9. [Servicio de autenticacion](#servicio-de-autenticacion)
10. [Rutas principales del API Gateway](#rutas-principales-del-api-gateway)
11. [Documentacion Swagger OpenAPI](#documentacion-swagger-openapi)
12. [Comunicacion entre microservicios](#comunicacion-entre-microservicios)
13. [Ejecucion local del proyecto](#ejecucion-local-del-proyecto)
14. [Ejecucion remota](#ejecucion-remota)
15. [Ejemplos de endpoints REST](#ejemplos-de-endpoints-rest)
16. [Datos de prueba sugeridos](#datos-de-prueba-sugeridos)
17. [Testing](#testing)
18. [Archivos SQL](#archivos-sql)
19. [Estado final del proyecto](#estado-final-del-proyecto)

---

## Descripcion del proyecto

**PCer2** es un sistema orientado a la gestion de un servicio tecnico de computadores.

El proyecto fue desarrollado bajo una arquitectura de microservicios, permitiendo administrar informacion de clientes, equipos, servicios tecnicos, ordenes de trabajo, vouchers, licencias de software, productos de mantenimiento, hardware, estadisticas, descuentos y autenticacion de usuarios.

Cada microservicio cuenta con su propia base de datos y expone endpoints REST para realizar operaciones CRUD o consultas especificas. Ademas, se utiliza un API Gateway como punto de entrada centralizado y WebClient para la comunicacion REST entre microservicios.

El sistema incorpora autenticacion mediante token JWT. El microservicio `service-auth` genera el token al iniciar sesion y el `api-gateway` valida dicho token mediante `AuthenticationFilter` antes de permitir el acceso a las rutas protegidas.

---

## Integrantes

- Sarai Jara
- Felipe Moya
- Jael Yapur

---

## Arquitectura general

La arquitectura del proyecto se organiza de la siguiente forma:

```text
Usuario / Postman / Swagger
        |
        v
API Gateway - puerto 8080
        |
        +--> service-auth - puerto 8091
        +--> service-clientes - puerto 8081
        +--> service-equipo - puerto 8082
        +--> service-servicio - puerto 8083
        +--> service-orden - puerto 8084
        +--> service-voucher - puerto 8085
        +--> service-estadisticas - puerto 8086
        +--> service-hardware - puerto 8087
        +--> service-software - puerto 8088
        +--> service-mantenimiento - puerto 8089
        +--> service-descuento - puerto 8090
```

Cada microservicio tiene su propia base de datos en MySQL. La comunicacion entre servicios se realiza mediante REST usando WebClient.

---

## Microservicios implementados

### 1. api-gateway

Actua como punto de entrada principal del sistema. Centraliza las rutas hacia los microservicios y valida el token JWT en las rutas protegidas mediante `AuthenticationFilter`.

### 2. service-auth

Permite registrar usuarios e iniciar sesion. Al realizar login correctamente, genera un token JWT que luego se utiliza para consumir las rutas protegidas del Gateway.

### 3. service-clientes

Permite registrar y gestionar clientes del servicio tecnico.

### 4. service-equipo

Permite registrar y gestionar equipos asociados a clientes. Tambien permite consultar equipos por cliente, numero de serie y marca.

### 5. service-servicio

Permite registrar los servicios tecnicos disponibles, por ejemplo:

- Formateo
- Cambio de pieza
- Mantencion completa
- Limpieza
- Diagnostico

### 6. service-orden

Permite registrar ordenes de trabajo, relacionando:

- Cliente
- Equipo
- Servicio
- Software
- Producto de mantenimiento

Este microservicio guarda identificadores externos como `clienteId`, `equipoId`, `servicioId`, `softwareId` y `productoMantenimientoId`. Ademas, consulta informacion de otros microservicios mediante WebClient y muestra datos complementarios usando campos `@Transient`.

### 7. service-voucher

Permite generar vouchers asociados a una orden de trabajo. El voucher guarda el `ordenId` y puede consultar la informacion de la orden mediante WebClient. Tambien permite asociar un codigo de descuento y consultar informacion desde `service-descuento`.

### 8. service-estadisticas

Permite generar registros estadisticos a partir de informacion obtenida desde otros microservicios, como clientes, equipos y vouchers.

### 9. service-hardware

Permite registrar y gestionar componentes de hardware utilizados en el servicio tecnico.

### 10. service-software

Permite registrar y gestionar licencias de software utilizadas en los servicios tecnicos.

Sus principales atributos son:

- Nombre
- Marca
- Version
- Serial

### 11. service-mantenimiento

Permite registrar y gestionar productos o insumos utilizados en mantenimiento.

Sus principales atributos son:

- Nombre
- Categoria
- Descripcion
- Stock actual
- Precio unitario

### 12. service-descuento

Permite registrar, listar, eliminar, validar y consultar codigos de descuento. Este servicio puede ser consumido por `service-voucher` para asociar descuentos a un voucher.

---

## Tecnologias y bibliotecas utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring WebFlux
- WebClient
- Spring Cloud Gateway
- Spring Security
- JWT
- JJWT
- MySQL
- Maven / Maven Wrapper
- Lombok
- Swagger / OpenAPI / Springdoc
- JUnit 5
- Mockito
- Postman
- Git y GitHub
- XAMPP / phpMyAdmin

---

## Herramientas necesarias

Para ejecutar el proyecto se requiere:

- JDK 21 instalado.
- Maven instalado o uso de Maven Wrapper (`mvnw` / `mvnw.cmd`).
- MySQL activo, por ejemplo mediante XAMPP.
- phpMyAdmin o DBeaver para revisar las bases de datos.
- Postman para probar endpoints REST.
- Navegador web para revisar Swagger.
- Git / GitHub para control de versiones.
- VS Code, IntelliJ IDEA u otro IDE compatible con Java y Spring Boot.

---

## Bases de datos utilizadas

Cada microservicio trabaja con su propia base de datos:

| Microservicio | Base de datos |
|---|---|
| service-auth | pc_seguridad |
| service-clientes | pc_clientes |
| service-equipo | pc_equipos |
| service-servicio | pc_servicio |
| service-orden | pc_orden |
| service-voucher | pc_voucher |
| service-estadisticas | pc_estadisticas |
| service-hardware | pc_hardware |
| service-software | pc_software |
| service-mantenimiento | pc_mantenimiento |
| service-descuento | pc_descuento |

Las bases de datos se crean automaticamente al ejecutar los microservicios, ya que los archivos `application.properties` usan:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Ademas, el proyecto incluye archivos `.sql` de poblamiento para apoyar la carga inicial de datos.

---

## Puertos utilizados

| Servicio | Puerto |
|---|---:|
| api-gateway | 8080 |
| service-clientes | 8081 |
| service-equipo | 8082 |
| service-servicio | 8083 |
| service-orden | 8084 |
| service-voucher | 8085 |
| service-estadisticas | 8086 |
| service-hardware | 8087 |
| service-software | 8088 |
| service-mantenimiento | 8089 |
| service-descuento | 8090 |
| service-auth | 8091 |

---

## Servicio de autenticacion

El proyecto implementa autenticacion mediante token JWT.

El flujo general es:

1. El usuario se registra o inicia sesion en `service-auth`.
2. Si las credenciales son correctas, `service-auth` genera un token JWT.
3. El cliente copia el token generado.
4. En las siguientes peticiones al Gateway se envia el token en el header `Authorization`.
5. El `api-gateway` valida el token con `AuthenticationFilter`.
6. Si el token es valido, el Gateway permite acceder al microservicio solicitado.
7. Si el token no existe o es invalido, el Gateway responde `401 Unauthorized`.

### Registro de usuario

```http
POST http://localhost:8080/api/v1/auth/registrar
```

Ejemplo de body:

```json
{
  "nombreUsuario": "admin",
  "contrasena": "1234",
  "roles": [
    {
      "nombreRol": "ADMIN"
    }
  ]
}
```

### Login

```http
POST http://localhost:8080/api/v1/auth/login
```

Ejemplo de body:

```json
{
  "nombreUsuario": "admin",
  "password": "1234"
}
```

La respuesta del login es un token JWT en texto plano, por ejemplo:

```text
eyJhbGciOiJIUzI1NiJ9...
```

### Uso del token

En Postman o Swagger se debe usar:

```text
Authorization: Bearer TOKEN_GENERADO
```

Ejemplo:

```text
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## Rutas principales del API Gateway

Todas estas rutas se consumen desde el puerto `8080`:

| Servicio | Ruta Gateway |
|---|---|
| Auth | http://localhost:8080/api/v1/auth |
| Clientes | http://localhost:8080/api/v1/clientes |
| Equipos | http://localhost:8080/api/v1/equipos |
| Servicios | http://localhost:8080/api/v1/servicios |
| Ordenes | http://localhost:8080/api/v1/ordenes |
| Vouchers | http://localhost:8080/api/v1/vouchers |
| Estadisticas | http://localhost:8080/api/v1/estadisticas |
| Hardware | http://localhost:8080/api/v1/hardware |
| Software | http://localhost:8080/api/v1/software |
| Mantenimiento | http://localhost:8080/api/v1/mantenimiento |
| Descuento | http://localhost:8080/api/v1/descuento |

Las rutas protegidas requieren token JWT.

---

## Documentacion Swagger OpenAPI

El proyecto utiliza Swagger / OpenAPI mediante la dependencia `springdoc-openapi`.

Swagger permite visualizar y probar los endpoints REST desde el navegador.

### Swagger centralizado desde API Gateway

```text
http://localhost:8080/swagger-ui.html
```

Desde esta URL se puede seleccionar la documentacion de los distintos microservicios.

### Swagger individual por microservicio

| Servicio | URL Swagger |
|---|---|
| service-auth | http://localhost:8091/swagger-ui.html |
| service-clientes | http://localhost:8081/swagger-ui.html |
| service-equipo | http://localhost:8082/swagger-ui.html |
| service-servicio | http://localhost:8083/swagger-ui.html |
| service-orden | http://localhost:8084/swagger-ui.html |
| service-voucher | http://localhost:8085/swagger-ui.html |
| service-estadisticas | http://localhost:8086/swagger-ui.html |
| service-hardware | http://localhost:8087/swagger-ui.html |
| service-software | http://localhost:8088/swagger-ui.html |
| service-mantenimiento | http://localhost:8089/swagger-ui.html |
| service-descuento | http://localhost:8090/swagger-ui.html |

### API Docs usados por el Gateway

| Servicio | Ruta api-docs |
|---|---|
| Clientes | /api/v1/clientes/v3/api-docs |
| Equipos | /api/v1/equipos/v3/api-docs |
| Servicio Tecnico | /api/v1/servicios/v3/api-docs |
| Ordenes de Trabajo | /api/v1/ordenes/v3/api-docs |
| Voucher | /api/v1/vouchers/v3/api-docs |
| Estadisticas | /api/v1/estadisticas/v3/api-docs |
| Hardware | /api/v1/hardware/v3/api-docs |
| Software | /api/v1/software/v3/api-docs |
| Mantenimiento | /api/v1/mantenimiento/v3/api-docs |
| Descuento | /api/v1/descuento/v3/api-docs |
| Auth | /api/v1/auth/v3/api-docs |

---

## Comunicacion entre microservicios

El proyecto utiliza `WebClient` para la comunicacion REST entre microservicios.

### service-clientes consulta

- `service-equipo`, para obtener equipos asociados a un cliente.

### service-orden consulta

- `service-clientes`
- `service-equipo`
- `service-servicio`
- `service-software`
- `service-mantenimiento`

### service-voucher consulta

- `service-orden`
- `service-descuento`

### service-estadisticas consulta

- `service-clientes`
- `service-equipo`
- `service-voucher`

### Uso de `@Transient`

Algunos microservicios utilizan campos `@Transient` para mostrar informacion obtenida desde otros servicios sin guardarla directamente en su base de datos.

Ejemplos:

- `service-orden` muestra datos externos de cliente, equipo, servicio, software y producto de mantenimiento.
- `service-voucher` muestra datos externos de orden y descuento.

---

## Ejecucion local del proyecto

### 1. Requisitos previos

Antes de ejecutar el proyecto, se debe contar con:

- Java 21 instalado.
- MySQL activo desde XAMPP u otra herramienta.
- Bases de datos disponibles o listas para ser creadas automaticamente.
- Postman para realizar pruebas.
- Navegador para Swagger.

### 2. Configurar MySQL

Iniciar MySQL desde XAMPP o desde el servicio local correspondiente.

Cada microservicio utiliza credenciales locales similares a:

```properties
spring.datasource.username=root
spring.datasource.password=
```

Si la configuracion local de MySQL usa otra clave, se debe modificar el archivo `application.properties` del microservicio correspondiente.

### 3. Ejecutar cada microservicio

Cada microservicio debe ejecutarse desde su carpeta correspondiente.

En Windows CMD o PowerShell:

```bash
mvnw.cmd spring-boot:run
```

En Git Bash, Linux o Mac:

```bash
./mvnw spring-boot:run
```

### 4. Orden recomendado de ejecucion

Se recomienda levantar los servicios en este orden:

1. `service-auth`
2. `service-clientes`
3. `service-equipo`
4. `service-servicio`
5. `service-software`
6. `service-mantenimiento`
7. `service-descuento`
8. `service-orden`
9. `service-voucher`
10. `service-estadisticas`
11. `service-hardware`
12. `api-gateway`

El `api-gateway` se recomienda levantar al final, cuando los microservicios ya esten activos.

---

## Ejecucion remota

El proyecto esta preparado para ejecucion local mediante Spring Boot, Maven y MySQL.

En caso de ejecutar en un entorno remoto, se deben configurar previamente:

- URLs de bases de datos remotas.
- Usuario y contrasena de MySQL remoto.
- Variables de entorno sensibles.
- Puertos o rutas publicas de cada servicio.
- URL publica del API Gateway.

Actualmente, las configuraciones del proyecto apuntan a entorno local usando `localhost`.

---

## Ejemplos de endpoints REST

Los siguientes ejemplos se ejecutan mediante el API Gateway en el puerto `8080`.

> Importante: excepto las rutas de autenticacion, las demas peticiones requieren token JWT.

### Auth

```http
POST http://localhost:8080/api/v1/auth/registrar
POST http://localhost:8080/api/v1/auth/login
```

### Clientes

```http
GET    http://localhost:8080/api/v1/clientes
POST   http://localhost:8080/api/v1/clientes
GET    http://localhost:8080/api/v1/clientes/1
PUT    http://localhost:8080/api/v1/clientes/1
DELETE http://localhost:8080/api/v1/clientes/1
GET    http://localhost:8080/api/v1/clientes/rut/87654321-5
```

### Equipos

```http
GET    http://localhost:8080/api/v1/equipos
POST   http://localhost:8080/api/v1/equipos/cliente/1
GET    http://localhost:8080/api/v1/equipos/1
PUT    http://localhost:8080/api/v1/equipos/1
DELETE http://localhost:8080/api/v1/equipos/1
GET    http://localhost:8080/api/v1/equipos/cliente/1
GET    http://localhost:8080/api/v1/equipos/serie/MSI-DESKTOP-001
GET    http://localhost:8080/api/v1/equipos/marca/MSI
```

### Servicios

```http
GET    http://localhost:8080/api/v1/servicios
POST   http://localhost:8080/api/v1/servicios
GET    http://localhost:8080/api/v1/servicios/1
PUT    http://localhost:8080/api/v1/servicios/1
DELETE http://localhost:8080/api/v1/servicios/1
```

### Ordenes

```http
GET    http://localhost:8080/api/v1/ordenes
POST   http://localhost:8080/api/v1/ordenes
GET    http://localhost:8080/api/v1/ordenes/1
PUT    http://localhost:8080/api/v1/ordenes/1
DELETE http://localhost:8080/api/v1/ordenes/1
```

### Vouchers

```http
GET    http://localhost:8080/api/v1/vouchers
POST   http://localhost:8080/api/v1/vouchers
GET    http://localhost:8080/api/v1/vouchers/1
PUT    http://localhost:8080/api/v1/vouchers/1
DELETE http://localhost:8080/api/v1/vouchers/1
```

### Estadisticas

```http
GET    http://localhost:8080/api/v1/estadisticas
POST   http://localhost:8080/api/v1/estadisticas/clientes
POST   http://localhost:8080/api/v1/estadisticas/equipos
POST   http://localhost:8080/api/v1/estadisticas/vouchers
GET    http://localhost:8080/api/v1/estadisticas/1
DELETE http://localhost:8080/api/v1/estadisticas/1
```

### Hardware

```http
GET    http://localhost:8080/api/v1/hardware
POST   http://localhost:8080/api/v1/hardware
GET    http://localhost:8080/api/v1/hardware/1
PUT    http://localhost:8080/api/v1/hardware/1
DELETE http://localhost:8080/api/v1/hardware/1
```

### Software

```http
GET    http://localhost:8080/api/v1/software
POST   http://localhost:8080/api/v1/software
GET    http://localhost:8080/api/v1/software/1
PUT    http://localhost:8080/api/v1/software/1
DELETE http://localhost:8080/api/v1/software/1
```

### Mantenimiento

```http
GET    http://localhost:8080/api/v1/mantenimiento
POST   http://localhost:8080/api/v1/mantenimiento
GET    http://localhost:8080/api/v1/mantenimiento/1
PUT    http://localhost:8080/api/v1/mantenimiento/1
DELETE http://localhost:8080/api/v1/mantenimiento/1
```

### Descuento

```http
GET    http://localhost:8080/api/v1/descuento
POST   http://localhost:8080/api/v1/descuento
GET    http://localhost:8080/api/v1/descuento/1
PUT    http://localhost:8080/api/v1/descuento/1
DELETE http://localhost:8080/api/v1/descuento/1
GET    http://localhost:8080/api/v1/descuento/validar/DIADELPADRE2026
GET    http://localhost:8080/api/v1/descuento/codigo/DIADELPADRE2026
```

---

## Datos de prueba sugeridos

### Ejemplo de login

```json
{
  "nombreUsuario": "admin",
  "password": "1234"
}
```

### Ejemplo de cliente

```json
{
  "nombre": "Maria",
  "apellido": "Gonzalez",
  "rut": "87654321-5",
  "email": "maria.gonzalez@gmail.com",
  "telefono": 987654321,
  "fechaRegistro": "2026-06-20",
  "ordenesTotales": 0
}
```

### Ejemplo de servicio

```json
{
  "nombre": "Mantencion completa",
  "descripcion": "Limpieza completa del hardware y cambio de pasta termica.",
  "precioBase": 35000,
  "activo": true
}
```

### Ejemplo de software

```json
{
  "nombre": "Windows 11 Pro",
  "marca": "Microsoft",
  "version": "23H2",
  "serial": "WIN11-PRO-2026"
}
```

### Ejemplo de producto de mantenimiento

```json
{
  "nombre": "Pasta termica",
  "categoria": "Insumo",
  "descripcion": "Pasta termica para CPU y GPU",
  "stockActual": 20,
  "precioUnitario": 4990
}
```

### Ejemplo de descuento

```json
{
  "codigo": "DIADELPADRE2026",
  "descripcion": "Descuento especial por dia del padre",
  "porcentajeDescuento": 15,
  "fechaInicio": "2026-06-01",
  "fechaFin": "2026-06-30",
  "activo": true
}
```

### Ejemplo de orden

```json
{
  "clienteId": 1,
  "equipoId": 1,
  "servicioId": 1,
  "softwareId": 1,
  "productoMantenimientoId": 1,
  "descripcionProblema": "Equipo requiere mantenimiento completo e instalacion de software",
  "fechaIngreso": "2026-06-20",
  "fechaEntregaEstimada": "2026-06-23",
  "estado": "Ingresada",
  "precioTotal": 39500
}
```

### Ejemplo de voucher

```json
{
  "ordenId": 1,
  "fechaEmision": "2026-06-20",
  "metodoPago": "Transferencia",
  "total": 39500,
  "cantidadServicios": 2,
  "estado": "Pagado",
  "observacion": "Voucher generado por mantencion completa",
  "codigoDescuento": "DIADELPADRE2026"
}
```

---

## Testing

El proyecto implementa pruebas unitarias con:

- JUnit 5
- Mockito

Las pruebas se ubican en:

```text
src/test/java
```

Se probaron principalmente metodos de la capa `service`, utilizando mocks para simular repositorios y dependencias externas como WebClient.

### Ejecutar tests en Windows

Desde la carpeta de cada microservicio:

```bash
mvnw.cmd test
```

### Ejecutar tests en Git Bash, Linux o Mac

```bash
./mvnw test
```

### Servicios con pruebas unitarias

- service-auth
- service-clientes
- service-equipo
- service-servicio
- service-orden
- service-voucher
- service-estadisticas
- service-hardware
- service-software
- service-mantenimiento
- service-descuento

### Ejemplo de lo que se valida

- Guardar registros.
- Listar registros.
- Buscar por ID.
- Actualizar registros.
- Eliminar registros.
- Validar credenciales de usuario.
- Generar token JWT.
- Validar codigos de descuento.
- Simular comunicacion entre microservicios usando mocks.

---

## Archivos SQL

El proyecto incluye archivos `.sql` para apoyar el poblamiento de datos y la preparacion de bases de datos.

Archivos incluidos:

- `pc_clientes.sql`
- `pc_equipos.sql`
- `pc_servicio.sql`
- `pc_orden.sql`
- `pc_voucher.sql`

Adicionalmente, los microservicios pueden crear o actualizar sus tablas automaticamente mediante JPA al ejecutarse con:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

## Estado final del proyecto

El proyecto **PCer2** se encuentra preparado para:

- Ejecucion local de microservicios Spring Boot.
- Uso de API Gateway como entrada centralizada.
- Autenticacion mediante token JWT.
- Validacion de rutas protegidas desde el Gateway.
- Comunicacion REST entre microservicios mediante WebClient.
- Documentacion de endpoints con Swagger/OpenAPI.
- Pruebas unitarias con JUnit 5 y Mockito.
- Pruebas de API REST mediante Postman.
- Defensa tecnica individual del funcionamiento del sistema.

---

## Observaciones finales

- Las rutas protegidas deben probarse usando token JWT.
- Las rutas de autenticación (`/api/v1/auth/registrar` y `/api/v1/auth/login`) no requieren token JWT.
- Se recomienda ejecutar primero los microservicios y luego el API Gateway.
- Se recomienda verificar Swagger centralizado en `http://localhost:8080/swagger-ui.html`.
- Los test del proyecto pueden verificarse ejecutando `mvnw.cmd test` o `./mvnw test` desde la carpeta de cada microservicio.
