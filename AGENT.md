# Guía del proyecto: Platzi Pizzería

## Propósito

Aplicación de aprendizaje para el curso **Java Spring Data JPA: Bases de Datos**. Modela una pizzería y usa Spring Data JPA para persistir sus entidades en MySQL.

## Tecnología

- Java 21 y Gradle.
- Spring Boot 4.1.1.
- Spring Data JPA e Hibernate.
- MySQL Connector/J.
- Lombok para código repetitivo de las entidades.
- MySQL local ejecutado en Docker y expuesto en `localhost:3306`.

## Estructura actual

```text
src/main/java/com/platzi/pizza/
├── PlatziPizzeriaApplication.java       # Punto de entrada de Spring Boot
└── persistence/entity/                  # Mapeos JPA de tablas MySQL
    ├── PizzaEntity.java                 # tabla pizza
    ├── CustomerEntity.java              # tabla customer
    ├── OrderEntity.java                 # tabla pizza_order
    ├── OrderItemEntity.java             # tabla order_item
    └── OrderItemId.java                 # clave compuesta de order_item

src/main/resources/
└── application.properties               # Configuración local de Spring, JPA y MySQL
```

## Modelo de persistencia

- `PizzaEntity` representa los productos de la pizzería. Su llave primaria `idPizza` se genera con identidad.
- `CustomerEntity` representa clientes. Su llave primaria `idCustomer` es un texto.
- `OrderEntity` representa pedidos en `pizza_order`; guarda el identificador del cliente y expone su relación con `CustomerEntity` y los ítems del pedido.
- `OrderItemEntity` representa el detalle del pedido en `order_item`. Usa la clave compuesta `OrderItemId` (`idOrder`, `idItem`) y se relaciona con `OrderEntity` y `PizzaEntity`.
- Al usar `@IdClass`, los nombres y los tipos de los campos de `OrderItemId` deben coincidir exactamente con los campos `@Id` de `OrderItemEntity`.

## Base de datos local

- La conexión se configura en `application.properties`.
- `spring.jpa.hibernate.ddl-auto=update` permite a Hibernate crear o actualizar el esquema durante el aprendizaje local.
- `spring.jpa.show-sql=true` muestra las consultas SQL generadas en la consola.
- No guardar contraseñas reales en el repositorio. Las credenciales actuales son exclusivamente de desarrollo local.

## Forma de trabajo acordada

1. La persona usuaria realiza los cambios de código, configuración y comandos Git desde IntelliJ IDEA.
2. El asistente explica errores, propone cambios concretos y proporciona los comandos que se deben ejecutar.
3. El asistente solo modifica archivos si la persona usuaria lo solicita explícitamente.
4. Antes de un commit, revisar `git status` y `git diff`; agregar únicamente los archivos pertinentes con `git add <ruta>`.

## Restricción: no usar codebase-memory

- No usar `codebase-memory-mcp` en este proyecto.
- No indexar el repositorio ni crear la carpeta `.codebase-memory/`.
- No añadir archivos de `codebase-memory` a Git.
- Para revisar el código, usar únicamente los archivos o fragmentos que comparta la persona usuaria, o herramientas locales convencionales cuando se autoricen.
