(function() {
    'use strict';

    angular
        .module('managementApp')
        .factory('Auth', Auth);

    Auth.$inject = ['$http', '$sessionStorage', '$state'];

    function Auth ($http, $sessionStorage, $state) {
        var service = {
            token: token,
            authorize: authorize,
            login: login,
            logout: logout,
            isAuthenticated: isAuthenticated,
        };

        return service;

        function token() {
            return $sessionStorage.token;
        }

        function authorize(event, toState) {
            if (toState.name === 'login') {
                if (isAuthenticated()) {
                    goToState(event, 'home');
                }
            } else if (!isAuthenticated()) {
                goToState(event, 'login');
            }
        }

        function goToState(event, stateName) {
            event.preventDefault();
            $state.go(stateName);
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
            return angular.isDefined(token());
        }

        function storeToken(token) {
            $sessionStorage.token = token;
        }
    }
})();
