ParaBank E2E Automation Framework (Java, Selenium & Karate)
1. Introducción
Este proyecto implementa una solución de automatización "End-to-End" para el sistema ParaBank. La solución cubre desde el registro de un nuevo usuario a través de la interfaz web (UI) hasta flujos financieros complejos ejecutados mediante servicios web (API).

2. Stack Tecnológico
Lenguaje: Java 11.
Automatización de API: Karate Framework.
Automatización de UI: Selenium WebDriver.
Gestor de Dependencias: Maven.
Test Runner: TestNG.
Reportería: Cucumber Reporting / Karate Reports.

3. Escenario de Negocio Automatizado
El flujo completo sigue el ciclo de vida de un cliente bancario:

Registro (UI): Creación dinámica de un usuario con datos únicos para permitir ejecuciones múltiples.

Login (API): Autenticación del usuario recién creado para obtener una sesión funcional.

Apertura de Cuenta (API): Creación de una cuenta de ahorros (Savings) financiada desde la cuenta inicial generada en el registro.

Transferencia (API): Movimiento de fondos entre la cuenta corriente y la nueva cuenta de ahorros.

Validación (API): Verificación del historial de transacciones, confirmando montos, tipos de operación y timestamps.


4. Precondiciones y Suposiciones
Cuenta de Fondeo: Se asume que el proceso de registro de ParaBank crea automáticamente una cuenta inicial (checking), la cual se extrae dinámicamente para fondear la nueva cuenta.

Datos Dinámicos: El framework genera nombres de usuario únicos basados en timestamps para evitar colisiones en la base de datos de ParaBank.

Latencia: Se han incluido esperas controladas (WebDriverWait en UI y sleep en API) para manejar la asincronía de los servicios de la aplicación de prueba.

5. Cómo Ejecutar las Pruebas
Desde el IDE (IntelliJ/Eclipse)
Importar el proyecto como Maven Project.

Ejecutar el archivo testng.xml ubicado en la raíz del proyecto (clic derecho -> Run).

Desde la Terminal (Maven)
Para ejecutar todos los tests y generar los reportes:
Bash
mvn clean test

6. Reportes de Prueba
Tras la ejecución, los resultados detallados (incluyendo Requests y Responses de la API) se pueden encontrar en:

target/karate-reports/karate-summary.html

target/cucumber-html-reports/overview-features.html (Reporte gráfico avanzado)