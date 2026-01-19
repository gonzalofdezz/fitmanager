# fitmanager

Proyecto Spring Boot con arquitectura hexagonal (ports & adapters).

## Estructura

- `domain`: entidades y reglas del dominio.
- `application`: casos de uso y puertos.
- `infrastructure`: adaptadores (REST, JPA) y mappers.
- `config`: configuración de Spring (beans, OpenAPI...).

## Ejecutar

```bash
mvn spring-boot:run
```

### Endpoints

- Swagger UI: `http://localhost:8080/swagger`
- API docs: `http://localhost:8080/api-docs`
- H2 console: `http://localhost:8080/h2-console`

Ejemplo:

```bash
curl -X POST http://localhost:8080/api/v1/athletes \
  -H "Content-Type: application/json" \
  -d '{"name":"Gonzalo","age":25}'
```
