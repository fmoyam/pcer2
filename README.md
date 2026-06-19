# PCer2 - Sistema de Servicio Técnico de Computadores

## Índice

1. [Descripción del proyecto](#descripción-del-proyecto)
2. [Integrantes](#integrantes)
3. [Arquitectura del proyecto](#arquitectura-del-proyecto)
4. [Funcionalidad de cada microservicio](#funcionalidad-de-cada-microservicio)

   * [service-clientes](#1-service-clientes)
   * [service-equipo](#2-service-equipo)
   * [service-servicio](#3-service-servicio)
   * [service-orden](#4-service-orden)
   * [service-voucher](#5-service-voucher)
   * [service-software](#6-service-software)
   * [service-mantenimiento](#7-service-mantenimiento)
   * [api-gateway](#8-api-gateway)
5. [Tecnologías utilizadas](#tecnologías-utilizadas)
6. [Bases de datos utilizadas](#bases-de-datos-utilizadas)
7. [Puertos utilizados](#puertos-utilizados)
8. [Ejecución del proyecto](#ejecución-del-proyecto)

   * [Requisitos previos](#1-requisitos-previos)
   * [Levantar los microservicios](#2-levantar-los-microservicios)
   * [Orden recomendado de ejecución](#orden-recomendado-de-ejecución)
9. [Comunicación entre microservicios](#comunicación-entre-microservicios)
10. [Ejemplos de endpoints](#ejemplos-de-endpoints)

    * [Clientes](#clientes)
    * [Equipos](#equipos)
    * [Servicios](#servicios)
    * [Órdenes](#órdenes)
    * [Voucher](#voucher)
    * [Software](#software)
    * [Mantenimiento](#mantenimiento)
11. [Datos de prueba sugeridos](#datos-de-prueba-sugeridos)

    * [Ejemplo de servicio](#ejemplo-de-servicio)
    * [Ejemplo de software](#ejemplo-de-software)
    * [Ejemplo de producto de mantenimiento](#ejemplo-de-producto-de-mantenimiento)
    * [Ejemplo de orden](#ejemplo-de-orden)
    * [Ejemplo de voucher](#ejemplo-de-voucher)
12. [Pruebas realizadas](#pruebas-realizadas)
13. [Estado del proyecto](#estado-del-proyecto)

---

## Descripción del proyecto

PCer2 es un sistema orientado a la gestión de un servicio técnico de computadores.

El proyecto fue desarrollado bajo una arquitectura de microservicios, permitiendo administrar información de clientes, equipos, servicios técnicos, órdenes de trabajo, vouchers, licencias de software y productos de mantenimiento.

Cada microservicio cuenta con su propia base de datos y expone endpoints REST para realizar operaciones CRUD. Además, se utiliza un API Gateway como punto de entrada centralizado y WebClient para la comunicación entre microservicios.

---

## Integrantes

* Sarai Jara
* Felipe Moya
* Jael Yapur

---

## Arquitectura del proyecto

El sistema está compuesto por los siguientes microservicios:

* **service-clientes**
* **service-equipo**
* **service-servicio**
* **service-orden**
* **service-voucher**
* **service-software**
* **service-mantenimiento**
* **api-gateway**

---

## Funcionalidad de cada microservicio

### 1. service-clientes

Permite registrar y gestionar clientes del servicio técnico.

### 2. service-equipo

Permite registrar y gestionar equipos asociados a clientes.

Los atributos relacionados con el tipo de equipo y tipo de almacenamiento forman parte del registro del equipo, por lo tanto no se manejan como microservicios o endpoints separados.

### 3. service-servicio

Permite registrar los servicios técnicos disponibles, por ejemplo:

* Formateo
* Cambio de pieza
* Mantención completa
* Limpieza
* Diagnóstico

### 4. service-orden

Permite registrar órdenes de trabajo, relacionando:

* Cliente
* Equipo
* Servicio
* Software
* Producto de mantenimiento

Este microservicio guarda los identificadores de otros servicios, como `clienteId`, `equipoId`, `servicioId`, `softwareId` y `productoMantenimientoId`.

Además, consulta información de otros microservicios mediante **WebClient** y muestra datos complementarios usando campos `@Transient`.

### 5. service-voucher

Permite generar vouchers asociados a una orden de trabajo.

El voucher guarda el `ordenId` y puede consultar la información de la orden mediante WebClient.

### 6. service-software

Permite registrar y gestionar licencias de software utilizadas en los servicios técnicos.

Sus principales atributos son:

* Nombre
* Marca
* Versión
* Serial

### 7. service-mantenimiento

Permite registrar y gestionar productos o insumos utilizados en mantenimiento.

Sus principales atributos son:

* Nombre
* Categoría
* Descripción
* Stock actual
* Precio unitario

### 8. api-gateway

Actúa como punto de entrada principal del sistema, centralizando el acceso a los microservicios desde un solo puerto.

---

## Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring WebFlux
* MySQL
* Maven
* Lombok
* Postman
* Git y GitHub
* XAMPP / phpMyAdmin

---

## Bases de datos utilizadas

Cada microservicio trabaja con su propia base de datos:

* **pc_clientes**
* **pc_equipos**
* **pc_servicio**
* **pc_orden**
* **pc_voucher**
* **pc_software**
* **pc_mantenimiento**

---

## Puertos utilizados

* **service-clientes** → `8081`
* **service-equipo** → `8082`
* **service-servicio** → `8083`
* **service-orden** → `8084`
* **service-voucher** → `8085`
* **service-software** → `8088`
* **service-mantenimiento** → `8089`
* **api-gateway** → `8080`

---

## Ejecución del proyecto

### 1. Requisitos previos

Antes de ejecutar el proyecto, se debe contar con:

* Java 21 instalado
* Maven instalado o uso de `mvnw`
* MySQL activo desde XAMPP
* Bases de datos disponibles en phpMyAdmin
* Postman para realizar pruebas de endpoints

### 2. Levantar los microservicios

Cada microservicio debe ejecutarse desde su carpeta correspondiente.

En Git Bash, Linux o Mac:

```bash
./mvnw spring-boot:run
```

En Windows CMD o PowerShell:

```bash
mvnw spring-boot:run
```

---

## Orden recomendado de ejecución

Se recomienda levantar los servicios en el siguiente orden:

1. `service-clientes`
2. `service-equipo`
3. `service-servicio`
4. `service-software`
5. `service-mantenimiento`
6. `service-orden`
7. `service-voucher`
8. `api-gateway`

---

## Comunicación entre microservicios

El proyecto utiliza **WebClient** para la comunicación entre microservicios.

### Relaciones principales implementadas

#### `service-orden` consulta:

* `service-clientes`
* `service-equipo`
* `service-servicio`
* `service-software`
* `service-mantenimiento`

#### `service-voucher` consulta:

* `service-orden`

### Uso de `@Transient`

En `service-orden` se utilizan campos `@Transient` para mostrar información obtenida desde otros microservicios sin guardarla directamente en la base de datos de órdenes.

Ejemplos:

* `datosCliente`
* `datosEquipo`
* `datosServicio`
* `datosSoftware`
* `datosProductoMantenimiento`

---

## Ejemplos de endpoints

### Clientes

```http
GET /api/v1/clientes
GET /api/v1/clientes/{id}
POST /api/v1/clientes
PUT /api/v1/clientes/{id}
DELETE /api/v1/clientes/{id}
```

### Equipos

```http
GET /api/v1/equipos
GET /api/v1/equipos/{id}
POST /api/v1/equipos
PUT /api/v1/equipos/{id}
DELETE /api/v1/equipos/{id}
```

### Servicios

```http
GET /api/v1/servicios
GET /api/v1/servicios/{id}
POST /api/v1/servicios
PUT /api/v1/servicios/{id}
DELETE /api/v1/servicios/{id}
```

### Órdenes

```http
GET /api/v1/ordenes
GET /api/v1/ordenes/{id}
POST /api/v1/ordenes
PUT /api/v1/ordenes/{id}
DELETE /api/v1/ordenes/{id}
```

### Voucher

```http
GET /api/v1/vouchers
GET /api/v1/vouchers/{id}
POST /api/v1/vouchers
PUT /api/v1/vouchers/{id}
DELETE /api/v1/vouchers/{id}
```

### Software

```http
GET /api/v1/software
GET /api/v1/software/{id}
POST /api/v1/software
PUT /api/v1/software/{id}
DELETE /api/v1/software/{id}
```

### Mantenimiento

```http
GET /api/v1/mantenimiento
GET /api/v1/mantenimiento/{id}
POST /api/v1/mantenimiento
PUT /api/v1/mantenimiento/{id}
DELETE /api/v1/mantenimiento/{id}
```

---

## Datos de prueba sugeridos

### Ejemplo de servicio

```json
{
  "nombre": "Mantención completa de equipo",
  "descripcion": "Limpieza completa del hardware del equipo. Incluye cambio de pasta térmica a CPU y GPU.",
  "precioBase": 35000,
  "activo": true
}
```

### Ejemplo de software

```json
{
  "nombre": "Windows 11 Pro",
  "marca": "Microsoft",
  "version": "11 Pro",
  "serial": "WIN11-PRO-ABC123"
}
```

### Ejemplo de producto de mantenimiento

```json
{
  "nombre": "Pasta térmica",
  "categoria": "Insumo técnico",
  "descripcion": "Pasta térmica para mantenimiento de CPU y GPU",
  "stockActual": 25,
  "precioUnitario": 4500
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
  "descripcionProblema": "Equipo requiere mantenimiento completo e instalación de software",
  "fechaIngreso": "2026-06-14",
  "fechaEntregaEstimada": "2026-06-17",
  "estado": "Ingresada",
  "precioTotal": 39500
}
```

### Ejemplo de voucher

```json
{
  "ordenId": 1,
  "fechaEmision": "2026-05-15",
  "metodoPago": "Efectivo",
  "total": 35000,
  "cantidadServicios": 1,
  "estado": "Emitido",
  "observacion": "Voucher generado desde orden de trabajo"
}
```

---

## Pruebas realizadas

Las pruebas del sistema se realizaron mediante **Postman**, verificando:

* Creación de clientes.
* Creación de equipos.
* Creación de servicios.
* Creación de órdenes de trabajo.
* Creación de vouchers.
* Creación de licencias de software.
* Creación de productos de mantenimiento.
* Consulta de registros por ID.
* Actualización de registros.
* Eliminación de registros.
* Comunicación entre microservicios mediante WebClient.
* Acceso centralizado mediante API Gateway.

---

## Estado del proyecto

Proyecto funcional a nivel de microservicios CRUD, con comunicación entre servicios mediante WebClient y pruebas realizadas en Postman.

Actualmente se cuenta con microservicios para clientes, equipos, servicios, órdenes de trabajo, vouchers, software y productos de mantenimiento, además de un API Gateway para centralizar las peticiones.
