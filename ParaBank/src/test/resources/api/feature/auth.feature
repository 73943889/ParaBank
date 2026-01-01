@E2E
Feature: Flujo de Negocio ParaBank - Gestión de Cuentas y Transferencias

  Background:
    * url baseUrl
    * configure headers = { Accept: 'application/json' }


  Scenario: Flujo E2E: Login, Creación, Consulta, Transferencia y Transacción
    # --- TEST 1: Autenticar Usuario ---
    Given path 'login', user, pass
    When method get
    Then status 200
    * def customerId = response.id
    * print 'El ID del cliente es:', customerId
    # --- TEST 2: Crear Nueva Cuenta ---
    Given path 'customers',customerId,'accounts'
    When method get
    Then status 200
    * def fundingAccountId = response[0].id
    * print 'Cuenta de fondo detectada: ',fundingAccountId
    Given path 'createAccount'
    And param customerId = customerId
    And param newAccountType = 1
    And param fromAccountId = fundingAccountId
    When method post
    Then status 200
    * def newAccountId = response.id
    And match response.type == 'SAVINGS'
    * print 'Nueva cuenta creada:', newAccountId
    # --- TEST 3: Obtener Cuentas del Cliente ---
    Given path 'customers', customerId, 'accounts'
    When method get
    Then status 200
    And match response[*].id contains newAccountId
    And match response[0].balance == '#number'
    * print 'Validación exitosa: La cuenta existe en el listado del cliente'
    # --- TEST 4: Transferir Fondos ---
    Given path 'transfer'
    And param fromAccountId = fundingAccountId
    And param toAccountId = newAccountId
    And param  amount = 399
    When method post
    Then status 200
    # --- TEST 5: Validar Transacciones de la Cuenta ---
    * eval java.lang.Thread.sleep(1000)
    Given path 'accounts', newAccountId, 'transactions'
    When method get
    Then status 200
    And match each response[*].amount == '#number'
    And match response[*].amount contains 399.0
    And match response[*].type contains 'Credit'
    * print 'Transacción confirmada en el historial'