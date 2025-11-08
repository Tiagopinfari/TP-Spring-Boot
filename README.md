# 💻 TP - Fundamentos de Spring Boot: Sistema de Gestión de Tareas

## 📋 Descripción del Proyecto
Este proyecto es un **Sistema de Gestión de Tareas**.

El objetivo principal es aplicar los conceptos fundamentales del *framework* Spring Boot para construir una aplicación profesional que demuestre el uso de:
* **Inyección de Dependencias (DI)** por constructor.
* **Estereotipos** (`@Service`, `@Repository`) según la responsabilidad de cada clase.
* **Configuración externa** con archivos `application.properties`.
* **Inyección de valores** mediante `@Value`.
* **Gestión de diferentes entornos** (`dev` y `prod`) mediante **Profiles**.
* **Beans Condicionales** (`@Profile`).
* **Lógica de inicio** usando la interfaz `CommandLineRunner`.

La lógica de la aplicación se ejecuta automáticamente al iniciar y simula las operaciones básicas de un gestor de tareas utilizando un repositorio de datos en memoria.

***

## ⚙️ Tecnologías Utilizadas
Este proyecto fue construido utilizando la siguiente pila tecnológica:

| Tecnología | Versión | Descripción |
| :--- | :--- | :--- |
| **Java** | 17+ | Lenguaje de programación. |
| **Spring Boot** | 3.x | Framework de desarrollo de microservicios. |
| **Maven** | 3.x | Herramienta de gestión y construcción del proyecto. |
| **Lombok** | (latest) | Generación automática de *boilerplate code*. |

***

## 🚀 Instrucciones para Clonar y Ejecutar

Sigue estos pasos para obtener una copia funcional del proyecto en tu máquina local:

### 1. Clonar el Repositorio
```bash
git clone (https://github.com/Tiagopinfari/TP-Spring-Boot.git)
cd tareas
```
### 2. Ejecutar y Cambiar entre Profiles
La aplicación debe ser probada en los dos perfiles configurados: dev y prod.
Para ver el perfil hay que ir hasta src/main/resources/application.properties y en spring.profiles.active se podrá ver el perfil activo, y para cambiarlo basta con poner dev o prod.

#### Perfil de Desarrollo (dev)
Este es el perfil activo por defecto. Utiliza un límite bajo de tareas, activa las estadísticas y tiene un logging detallado (DEBUG).

#### Perfil de Producción (prod)
Para activar este perfil, debemos sobrescribir la propiedad spring.profiles.active al iniciar. Deshabilita las estadísticas, tiene un límite alto de tareas y un logging restringido (ERROR).

## 🖼️ Capturas de Pantalla
Capturas de pantalla de la consola para ambas ejecuciones, demostrando las diferencias en:

Mensajes de bienvenida/despedida (MensajeService condicional).

Configuración de propiedades (app.max-tareas y app.mostrar-estadisticas).

Nivel de logging (DEBUG vs. ERROR).

#### Ejecución en Profile DEV
<img width="1802" height="847" alt="spring profiles active=dev 1" src="https://github.com/user-attachments/assets/13c600b9-a9f0-4368-beeb-58db9ac84cce" />
<img width="1790" height="767" alt="spring profiles active=dev 2" src="https://github.com/user-attachments/assets/fe8fca4a-01c3-48a1-a9df-02140dba6d6f" />

#### Ejecución en Profile PROD
<img width="1790" height="805" alt="spring profiles active=prod 1" src="https://github.com/user-attachments/assets/aa3b7ae4-9fe5-4823-816c-0ecfbf544507" />
<img width="1788" height="648" alt="spring profiles active=prod 2" src="https://github.com/user-attachments/assets/e12c1e68-3e24-4eee-b73e-8fa1bbeaa60e" />

## 🧠 Conclusiones Personales
Este proyecto demostró la aplicación exitosa de los fundamentos de Spring Boot para construir una aplicación modular y adaptable.

Estructura y Acoplamiento: Se implementó una arquitectura limpia utilizando estereotipos (@Service, @Repository) y la inyección de dependencias por constructor, lo que resultó en un código desacoplado y fácil de mantener.

Adaptabilidad con Profiles: El uso de Profiles (dev y prod) y el manejo de propiedades con @Value permitió adaptar la aplicación de forma dinámica, cambiando configuraciones (límites de tareas) y el comportamiento del servicio (MensajeService condicional) según el entorno activo.

Integración: La interfaz CommandLineRunner se utilizó eficazmente para orquestar la lógica de inicio y demostrar la integración de todos los componentes inyectados.

En resumen, el trabajo práctico cumplió con el objetivo de simular una práctica de desarrollo profesional, enfatizando el diseño modular y la gestión flexible de entornos.

## 🧑‍💻 Autor
Tiago Pínfari

Legajo: 51043
