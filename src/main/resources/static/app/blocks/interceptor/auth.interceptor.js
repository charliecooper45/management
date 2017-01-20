(function() {
    'use strict';

    angular
        .module('managementApp')
        .factory('authInterceptor', authInterceptor);

    authInterceptor.$inject = ['$sessionStorage'];

    function authInterceptor ($sessionStorage) {
        var service = {
            request: request
        };

        return service;

        function request(config) {
            var token = $sessionStorage.token;
            config.headers = config.headers || {};
            if (angular.isDefined(token)) {
                config.headers.Authorization = 'Bearer ' + token;
            }
            return config;
        }
    }
})();
