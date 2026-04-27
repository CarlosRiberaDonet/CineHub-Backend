# CineHub — Backend

Backend REST API para [CineHub](https://carlosriberadonet.github.io/CineHub/), una aplicación web para explorar películas y series en tiempo real.

Actúa como gateway hacia la API de TMDB: recibe las peticiones del frontend, construye las llamadas a TMDB, transforma las respuestas JSON en un modelo de dominio propio y las expone como endpoints REST limpios.

**Frontend:** [github.com/CarlosRiberaDonet/CineHub](https://github.com/CarlosRiberaDonet/CineHub)  
**Demo en producción:** [carlosriberadonet.github.io/CineHub](https://carlosriberadonet.github.io/CineHub/)  
**API desplegada en:** `https://peliculasonlinehd-production.up.railway.app`

---

## Stack

| | |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.4.8 |
| Serialización | Jackson · jackson-datatype-jsr310 |
| Despliegue | Railway |
| API externa | TMDB |

---

## Arquitectura

```
Frontend (GitHub Pages)
        │
        │  HTTP / fetch
        ▼
CineHub Backend (Railway)
        │
        │  HTTP
        ▼
API TMDB
```

El backend desacopla el frontend de la estructura de TMDB. Cualquier cambio en la API externa solo afecta a la capa DAO, sin impacto en el cliente.

### Estructura del proyecto

```
src/main/java/com/CineHub/
├── controllers/   # Endpoints REST expuestos al frontend
├── services/      # Lógica de negocio y construcción de URLs TMDB
├── dao/           # Llamadas HTTP a la API de TMDB
├── dto/           # Objetos de transferencia de datos
└── entity/        # Modelo de dominio propio
```

---

## Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/peliculas/upcoming` | Próximos estrenos |
| `GET` | `/peliculas/playing` | En cartelera |
| `GET` | `/peliculas/trendingDayMovies` | Tendencias del día |
| `GET` | `/peliculas/topMovies` | Top películas |
| `GET` | `/peliculas/details?id={id}` | Detalle de una película |
| `GET` | `/famous/credits?id={id}` | Reparto de una película |
| `GET` | `/trailer?id={id}` | Trailer de una película |

---

## Ejecutar en local

### Requisitos

- Java 17+
- Maven
- API Key de TMDB ([obtener aquí](https://developer.themoviedb.org/docs/getting-started))

### Pasos

```bash
git clone https://github.com/CarlosRiberaDonet/CineHub-Backend
cd CineHub-Backend
```

Configura tu API key en `src/main/resources/application.properties`:

```properties
server.port=${PORT:8080}
tmdb.api.key=TU_API_KEY
```

Arranca el servidor:

```bash
./mvnw spring-boot:run
```

La API estará disponible en `http://localhost:8080`.

Para conectar el frontend en local, asegúrate de que en `js/api.js` del repo CineHub esté:

```javascript
const BASE_URL = "http://localhost:8080";
```

---

## Despliegue

El backend está desplegado en **Railway**. El CORS está configurado para aceptar peticiones desde:

- `http://localhost:5500` (desarrollo local)
- `https://carlosriberadonet.github.io` (producción)

---

## Autor

**Carlos Ribera Donet**  
[github.com/CarlosRiberaDonet](https://github.com/CarlosRiberaDonet) · [linkedin.com/in/carlos-r-335390276](https://www.linkedin.com/in/carlos-r-335390276/)
