(function() {
    'use strict';

    angular
        .module('managementApp')
        .config(httpConfig);

    httpConfig.$inject = ['$urlRouterProvider', '$httpProvider'];

    function httpConfig($urlRouterProvider, $httpProvider) {
        $urlRouterProvider.otherwise('/');

        $httpProvider.interceptors.push('authInterceptor');
    }
})();
