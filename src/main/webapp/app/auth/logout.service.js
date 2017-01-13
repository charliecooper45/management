(function() {
    'use strict';

    angular
        .module('managementApp')
        .factory('Logout', Logout);

    function Logout() {
        var service = {
            logout: logout
        };

        return service;

        function logout() {
            delete $localStorage.token;
            $http.defaults.headers.common = {};
        }
    }
})();
