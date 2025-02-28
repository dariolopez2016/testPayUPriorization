# Priorization Service API
## Autor
- **Nombre**: Dario Lopez
- **Correo**: dario.lopez.sistemas@gmail.com
- **Fecha**: 28 de febrero de 2025

¡Bienvenido al `Prioritization_Service`! Este proyecto ofrece una API REST para priorizar redes basada en tiempos de respuesta o costos, usando una arquitectura hexagonal.

## Descripción General

El servicio toma datos de redes y los ordena según un criterio seleccionado, devolviendo un arreglo de índices prioritarios.
## Instalación
1. Clona el repositorio desde GitHub:
   git clone https://github.com/dariolopez2016/network-data-service.git

# Sistema de Gestión de Datos de Red

Este sistema está compuesto por **dos proyectos** que trabajan juntos: `data-service` y `network-service`. Ambos deben estar ejecutándose simultáneamente para el correcto funcionamiento de la aplicación.

## Proyectos

### 1. Data Service (`data-service`)
API RESTful para gestionar datos generales del sistema.

- **Endpoint principal**: `GET /api/data`
- **Swagger UI**: `http://localhost:8081/swagger-ui.html`
- **Repositorio**: `https://github.com/dariolopez2016/data-service.git`

### 2. Network Service (`network-service`)
API RESTful para obtener información específica de la red.

- **Endpoint principal**: `GET /api/networks/data`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Repositorio**: `https://github.com/dariolopez2016/network-service.git`

### Cómo Funciona

- **Entrada:** Un objeto `PrioritizationRequest` con:
    - `responseTimes`: Tiempos de respuesta (ej. `[20, 15, 100]`).
    - `costs`: Costos (ej. `[50, 60, 30]`).
    - `criteria`: `"RESPONSE_TIME"` o `"COST"`.
- **Proceso:** Ordena las redes según el criterio (menor a mayor).
- **Salida:** Índices ordenados (ej. `[1, 0, 2]`).

### Ejemplo

**Entrada:**
```json
{
  "responseTimes": [20, 15, 100],
  "costs": [50, 60, 30],
  "criteria": "RESPONSE_TIME"
}
