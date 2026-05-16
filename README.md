# PCer2 - Sistema de Servicio Técnico de Computadores

## Índice

1. [Descripción del proyecto](#descripción-del-proyecto)
2. [Integrantes](#integrantes)
3. [Arquitectura del proyecto](#arquitectura-del-proyecto)
4. [Funcionalidad de cada microservicio](#funcionalidad-de-cada-microservicio)
   - [service-clientes](#1-service-clientes)
   - [service-equipo](#2-service-equipo)
   - [service-servicio](#3-service-servicio)
   - [service-orden](#4-service-orden)
   - [service-voucher](#5-service-voucher)
   - [api-gateway](#6-api-gateway)
5. [Tecnologías utilizadas](#tecnologías-utilizadas)
6. [Bases de datos utilizadas](#bases-de-datos-utilizadas)
7. [Puertos utilizados](#puertos-utilizados)
8. [Ejecución del proyecto](#ejecución-del-proyecto)
   - [Requisitos previos](#1-requisitos-previos)
   - [Levantar los microservicios](#2-levantar-los-microservicios)
   - [Orden recomendado de ejecución](#orden-recomendado-de-ejecución)
9. [Comunicación entre microservicios](#comunicación-entre-microservicios)
10. [Ejemplos de endpoints](#ejemplos-de-endpoints)
    - [Clientes](#clientes)
    - [Equipos](#equipos)
    - [Tipo de equipo](#tipo-de-equipo)
    - [Tipo de almacenamiento](#tipo-de-almacenamiento)
    - [Servicios](#servicios)
    - [Órdenes](#órdenes)
    - [Voucher](#voucher)
11. [Datos de prueba sugeridos](#datos-de-prueba-sugeridos)
    - [Ejemplo de servicio](#ejemplo-de-servicio)
    - [Ejemplo de orden](#ejemplo-de-orden)
    - [Ejemplo de voucher](#ejemplo-de-voucher)
12. [Pruebas realizadas](#pruebas-realizadas)
13. [Estado del proyecto](#estado-del-proyecto)

## Descripción del proyecto
PCer2 es un sistema orientado a la gestión de un servicio técnico de computadores.  
El proyecto fue desarrollado bajo una arquitectura de microservicios, permitiendo administrar información de clientes, equipos, servicios técnicos, órdenes de trabajo y vouchers.

## Integrantes
- Sarai Jara
- Felipe Moya
- Jael Yapur

## Arquitectura del proyecto
El sistema está compuesto por los siguientes microservicios:

- **service-clientes**
- **service-equipo**
- **service-servicio**
- **service-orden**
- **service-voucher**
- **api-gateway**

## Funcionalidad de cada microservicio

### 1. service-clientes
Permite registrar y gestionar clientes.

### 2. service-equipo
Permite registrar y gestionar equipos asociados a clientes.  
También administra:
- Tipo de equipo
- Tipo de almacenamiento

### 3. service-servicio
Permite registrar los servicios técnicos disponibles, por ejemplo:
- Formateo
- Cambio de pieza
- Mantención completa
- Limpieza
- Diagnóstico

### 4. service-orden
Permite registrar órdenes de trabajo, relacionando:
- Cliente
- Equipo
- Servicio

Además, este microservicio consulta información de otros microservicios mediante **WebClient**.

### 5. service-voucher
Permite generar vouchers asociados a una orden de trabajo.

### 6. api-gateway
Actúa como punto de entrada principal del sistema, centralizando el acceso a los microservicios.

---

## Tecnologías utilizadas
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring WebFlux
- MySQL
- Maven
- Lombok
- Postman
- Git y GitHub
- XAMPP / phpMyAdmin

---

## Bases de datos utilizadas
Cada microservicio trabaja con su propia base de datos:

- **pc_clientes**
- **pc_equipos**
- **pc_servicio**
- **pc_orden**
- **pc_voucher**

---

## Puertos utilizados

- **service-clientes** → `8081`
- **service-equipo** → `8082`
- **service-servicio** → `8083`
- **service-orden** → `8084`
- **service-voucher** → `8085`
- **api-gateway** → `8080`

---

## Ejecución del proyecto

### 1. Requisitos previos
Antes de ejecutar el proyecto, se debe contar con:

- Java 21 instalado
- Maven instalado o uso de `mvnw`
- MySQL activo desde XAMPP
- Base de datos disponible en phpMyAdmin

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
4. `service-orden`
5. `service-voucher`
6. `api-gateway`

---

## Comunicación entre microservicios

El proyecto utiliza **WebClient** para la comunicación entre microservicios.

### Relaciones principales implementadas

#### `service-orden` consulta:

- `service-clientes`
- `service-equipo`
- `service-servicio`

#### `service-voucher` consulta:

- `service-orden`

---

## Ejemplos de endpoints

### Clientes

```http
GET /api/v1/clientes
POST /api/v1/clientes
```

### Equipos

```http
GET /api/v1/equipos
POST /api/v1/equipos
```

### Tipo de equipo

```http
GET /api/v2/tipoequipo
POST /api/v2/tipoequipo
```

### Tipo de almacenamiento

```http
GET /api/v2/tipoalmacen
POST /api/v2/tipoalmacen
```

### Servicios

```http
GET /api/v1/servicios
POST /api/v1/servicios
```

### Órdenes

```http
GET /api/v1/ordenes
GET /api/v1/ordenes/{id}
POST /api/v1/ordenes
```

### Voucher

```http
GET /api/v1/vouchers
GET /api/v1/vouchers/{id}
POST /api/v1/vouchers
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

### Ejemplo de orden

```json
{
  "clienteId": 1,
  "equipoId": 1,
  "servicioId": 3,
  "descripcionProblema": "Equipo muy sucio. Presenta estrangulamiento térmico en GPU y CPU.",
  "fechaIngreso": "2026-05-15",
  "fechaEntregaEstimada": "2026-05-18",
  "estado": "Ingresada",
  "precioTotal": 35000
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

- Creación de clientes.
- Creación de equipos.
- Creación de servicios.
- Creación de órdenes.
- Creación de vouchers.
- Comunicación entre microservicios mediante WebClient.
- Acceso centralizado mediante API Gateway.

---

## Estado del proyecto

Proyecto funcional a nivel de microservicios CRUD, con comunicación entre servicios y pruebas realizadas en Postman.
