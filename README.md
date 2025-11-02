# 🎬 Sistema de Gestión de Contenido Audiovisual

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://java.com)
[![POO](https://img.shields.io/badge/Programación-Orientada%20a%20Objetos-green.svg)](https://es.wikipedia.org/wiki/Programaci%C3%B3n_orientada_a_objetos)
[![GitHub](https://img.shields.io/badge/GitHub-Repository-lightgrey.svg)](https://github.com)

## 📋 Descripción

Sistema desarrollado en Java para la gestión integral de contenido audiovisual, implementando conceptos avanzados de Programación Orientada a Objetos (POO). El proyecto permite administrar diferentes tipos de contenido multimedia con relaciones complejas entre entidades.

## 🎯 Objetivos

### Objetivo General
Ampliar y mejorar un proyecto Java utilizando conceptos avanzados de POO como **herencia, asociación, agregación y composición**, integrando nuevas clases en un sistema existente.

### Objetivos Específicos
- ✅ Diseñar e implementar sistema de gestión de contenido audiovisual
- ✅ Aplicar relaciones de POO avanzadas
- ✅ Gestionar proyecto mediante control de versiones en GitHub
- ✅ Documentar sistema completo con pruebas funcionales

## 🏗️ Arquitectura del Sistema

### Diagrama de Clases
Clases Principales
Clase Abstracta Base
ContenidoAudiovisual: Clase abstracta base con propiedades comunes a todos los contenidos

Subclases Especializadas
Pelicula: Gestiona películas con actores y estudios

SerieDeTV: Administra series con temporadas y episodios

Documental: Maneja documentales con investigadores especializados

Podcast: Controla podcasts con sponsors y estadísticas de descargas

Livestream: Gestiona transmisiones en vivo con espectadores y donaciones

Clases de Relación
Actor: Representa actores en películas

Temporada: Modela temporadas en series de TV

Investigador: Representa investigadores en documentales

🔧 Funcionalidades Implementadas
Relaciones POO Implementadas
Herencia
Todas las subclases heredan de ContenidoAudiovisual

Método abstracto mostrarDetalles() implementado polimórficamente

Agregación
Pelicula → Actor (una película tiene varios actores)

SerieDeTV → Temporada (una serie tiene múltiples temporadas)

Documental → Investigador (un documental tiene varios investigadores)

Composición
Livestream contiene moderadores y gestiona transmisiones

Podcast contiene sponsors y estadísticas

Funcionalidades Específicas por Clase
Película
Gestión de actores

Información de estudio cinematográfico

Serie de TV
Administración de temporadas

Control de episodios por temporada

Documental
Gestión de investigadores

Especialización por temas

Podcast
Registro de descargas

Gestión de sponsors

Episodios numerados

Livestream
Transmisiones en vivo

Control de espectadores

Sistema de donaciones

Gestión de moderadores

🚀 Instrucciones de Instalación y Ejecución
Prerrequisitos
Java JDK 8 o superior

Git para control de versiones

Clonar el Repositorio
bash
git clone https://github.com/AnthonyPilatasig/poo_deber_unidad1.git
cd poo_deber_unidad1
Compilar el Proyecto
bash
javac -d bin poo/*.java uni1a/*.java
Ejecutar las Pruebas
bash
# Prueba principal del sistema
java -cp bin poo.PruebaAudioVisual

📁 Estructura de Archivos
text
poo_deber_unidad1/
│
├── poo/
│   ├── PruebaAudioVisual.java
│
├── uni1a/
│   ├── ContenidoAudiovisual.java
│   ├── Pelicula.java
│   ├── SerieDeTV.java
│   ├── Documental.java
│   ├── Podcast.java
│   ├── Livestream.java
│   ├── Actor.java
│   ├── Temporada.java
│   └── Investigador.java
│
├── README.md
└── diagrama-clases.png
🧪 Pruebas y Verificaciones
Pruebas Implementadas
Creación de objetos: Verificación de instanciación correcta

Relaciones entre clases: Validación de agregaciones y composiciones

Funcionalidades específicas: Prueba de métodos particulares de cada clase

Casos límite: Manejo de situaciones extremas

Polimorfismo: Verificación del comportamiento polimórfico

Métodos de Prueba
testCreacionObjetos(): Verifica creación de todas las clases

testRelaciones(): Valida relaciones entre objetos

testFuncionalidades(): Prueba métodos específicos

testCasosLimite(): Manejo de casos extremos

🔄 Gestión de Versiones en GitHub
Commits Realizados
Commit inicial: Estructura base del proyecto

Implementación clases base: Clases abstractas y principales

Relaciones y funcionalidades: Implementación de agregaciones y métodos específicos

Sistema de pruebas: Clases de prueba exhaustivas

Documentación: README y documentación final

Buenas Prácticas Implementadas
Commits descriptivos y atómicos

Estructura de proyecto clara

Documentación completa

Código comentado

🛠️ Mejoras Adicionales Implementadas
Optimizaciones de Código
Uso de List<> en lugar de arrays para mejor manejo de colecciones

Implementación de métodos toString() para mejor visualización

Validación de datos en constructores y setters

Manejo de casos límite en métodos críticos

Características Avanzadas
Sistema de IDs automático en clase base

Fechas y horas con LocalDateTime

Formateo de salidas monetarias y de fechas

Estadísticas en tiempo real para livestreams y podcasts

📊 Diagrama de Clases
El sistema incluye un diagrama UML que muestra:

Relaciones de herencia entre ContenidoAudiovisual y sus subclases

Relaciones de agregación con Actor, Temporada e Investigador

Atributos y métodos principales de cada clase

Cardinalidades de las relaciones

👥 Autor
Desarrollado como parte de un proyecto académico para implementar conceptos avanzados de Programación Orientada a Objetos.

📄 Licencia
Este proyecto es con fines educativos y académicos.
