(function() {
    'use strict';

    angular
        .module('managementApp')
        .factory('Auth', Auth);

    Auth.$inject = ['$http', '$sessionStorage', '$state'];

    function Auth ($http, $sessionStorage, $state) {
        var service = {
            authorize: authorize,
            login: login,
            logout: logout,
            isAuthenticated: isAuthenticated
        };

        return service;

        function authorize(event, toState) {
            if (!isAuthenticated() && toState.name !== 'login') {
                event.preventDefault();
                $state.go('login');
            }
        }

        function login(username, password) {
            var data = {
                username: username,
                password: password
            };
            return $http.post('api/login', data).then(loginSuccess);

            function loginSuccess(data) {
                var token = data.headers('Token');
                storeToken(token);
            }
        }

        function logout() {
            delete $sessionStorage.token;
            $state.go("login", {}, {reload: true});
        }

        function isAuthenticated() {
            var token = retrieveToken();
            return angular.isDefined(token);
        }

        function storeToken(token) {
            $sessionStorage.token = token;
            $http.defaults.headers.common.Authorization = 'Bearer ' + token;
        }

        function retrieveToken() {
            return $sessionStorage.token;
        }
    }
})();