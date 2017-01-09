(function() {
    'use strict';

    angular
        .module('managementtestApp')
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
