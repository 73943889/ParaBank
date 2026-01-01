function fn() {
    karate.configure('logPrettyRequest', true);
    karate.configure('logPrettyResponse', true);
    karate.configure('report', { showLog: true, showAllSteps: true });
    var config = {
        baseUrl: 'https://parabank.parasoft.com/parabank/services/bank',
        user: karate.properties['testUser'],
        pass: karate.properties['testPass']
    };
    return config;
}