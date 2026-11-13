# Domi 🛵 — El reto de los domicilios

Aplicación de consola en **Java puro** (sin Maven ni frameworks) que gestiona clientes, repartidores y pedidos de Domi, una startup bogotana de domicilios. El sistema decide automáticamente qué repartidor le conviene a cada pedido según costo, tiempo y capacidad.

> Hackathon Java · Generation Colombia · Cohorte 15 · Duración: 8 horas

---

## 👥 Equipo e integrantes

| Integrante | Rol |
|---|---|
| Juan Perdomo | Arquitecto/a del modelo |
| Natalia Villalobos | Responsable de reglas |
| Mauro Infante | Responsable de la terminal |
| Angie Fuentes | QA (pruebas) |
| Valentina Bucuru | Integrador/a Git |

Todos los integrantes programan y deben poder explicar cualquier parte del código.

---

## 🛠️ Tecnologías y herramientas

- **Java puro** (sin Maven ni frameworks)
- **IntelliJ IDEA**
- Git / GitHub (ramas, Pull Requests, `main` estable)
- Datos en memoria (sin archivos ni base de datos)

---

## 📁 Estructura del proyecto

```
hackaton2-equipo2/
├── app/        # Main: menú, Scanner, mensajes y manejo de errores
├── modelo/     # Cliente, Repartidor (abstracta), Bici, Moto, Carro, Dron, Pedido, Calificable
├── servicio/   # DomiService: colecciones y reglas de negocio
└── README.md
```

Principio de diseño: **Main pregunta y muestra, el servicio decide, el modelo se valida a sí mismo.**

---

## ▶️ Cómo ejecutar el proyecto en IntelliJ

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Maurux01/hackaton2-equipo2.git
   ```
2. Abrir la carpeta en IntelliJ IDEA (File → Open).
3. Marcar `app`, `modelo` y `servicio` como *Sources Root* si no lo están.
4. Ejecutar la clase `app.Main`.
5. Los datos de arranque (9 barrios, 3 repartidores y 2 clientes) se cargan automáticamente al iniciar.

---

## ✅ Funcionalidades implementadas

- **Nivel Base 🟢**: gestión de clientes y repartidores (registrar, listar, editar, eliminar), validaciones, datos de arranque, app que no se cae.
- **Nivel Corazón 🟡**: cotización polimórfica, regla de asignación, estados del pedido (EN_CAMINO / ENTREGADO / CANCELADO), entregar y cancelar.
- **Operación real 🔴**: calificaciones con interfaz `Calificable`, modo lluvia, cierre del día.
- **Jefe final 🐉**: soporte de `Dron` sin tocar la lógica de cotización/asignación.

---

## 📋 Respuestas a las preguntas del PDF

### Sección 6.3 — ¿Dónde guardar los datos?

| Dato | Colección | ¿Por qué? |
|---|---|---|
| Clientes por cédula | `HashMap<String, Cliente>` | Búsqueda O(1) por cédula y evita duplicados con `containsKey`. Una lista obligaría a recorrer todo. |
| Barrios con cobertura | `HashSet<String>` | La membresía es rápida y garantiza que no se repitan. |
| Distancia por barrio | `HashMap<String, Double>` | El operador nunca escribe los km; se derivan del barrio del cliente. |
| Repartidores | `ArrayList<Repartidor>` | Hay que recorrerlos todos para cotizar; aquí sí sirve una lista. |
| Pedidos | `ArrayList<Pedido>` | Se buscan por número recorriendo la lista; el consecutivo es automático. |

### ¿Dónde quedó la regla de la lluvia y por qué?

La regla vive en **`DomiService`** (estado `modoLluvia` + ajuste en `crearPedido`):
- Las bicis no pueden llevar pedidos.
- Todos los costos suben un 20%.

Se puso en el servicio porque es quien cotiza y asigna. El modelo (`Repartidor`) no conoce el clima, lo que evita tocar firmas y facilita agregar el dron (que tampoco vuela con lluvia).

### Jefe final — ¿Cuántos archivos se tocaron para agregar el dron y por qué?

Solo se creó la clase `Dron` y se agregó la opción "Dron" al registrar un repartidor. **No se modificó** el método que cotiza y asigna, porque el diseño polimórfico permite que cada repartidor calcule su propio costo/tiempo y capacidad. Si hubiera que tocar la lógica de asignación, el diseño tendría una grieta.

### Reflexión de cierre (sección 15)

- Al cerrar y volver a abrir el programa, **los pedidos del día se pierden** (los datos viven en memoria).
- Para compartir clientes y pedidos entre dos sedes, habría que persistir los datos (base de datos) y sincronizarlos entre operadores.

---

## 🎬 Video demo

Link: https://drive.google.com/file/d/1PEQhSjpTbz_0RZZe5V_WtLlEDdUsm98b/view?usp=sharing

---

## 📝 Notas

- La app nunca se cae: cada opción del menú está envuelta en `try/catch`.
- Uso de IA como tutor/revisor, no como copiloto que escriba el proyecto.
- Regla de oro: cualquier integrante debe poder explicar cualquier línea del código.
