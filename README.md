# Priorization Service API

¡Bienvenido al `Prioritization_Service`! Este proyecto ofrece una API REST para priorizar redes basada en tiempos de respuesta o costos, usando una arquitectura hexagonal.

## Descripción General

El servicio toma datos de redes y los ordena según un criterio seleccionado, devolviendo un arreglo de índices prioritarios.

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
