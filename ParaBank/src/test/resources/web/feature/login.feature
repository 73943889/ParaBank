@HU-1221
Feature: Gestion de registro de usuario
  Como un nuevo cliente de ParaBank
  Quiero registrarme e iniciar sesión
  Para gestionar mis cuentas bancarias de forma segura


  Scenario: Registro y login consecutivo con datos dínamicos
    Given que el usuario genera y registra una cuenta nueva
    When inicia sesion con los datos generados
    Then valido el ingreso exitoso visualizando "Accounts Overview"