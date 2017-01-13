(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['Login', '$localStorage', '$state', '$http'];

    function LoginController(Login, $localStorage, $state, $http) {
        var vm = this;

        vm.login = login;

        function login() {
            Login.login({
                username: vm.username,
                password: vm.password
            }).$promise.then(function (response) {
                $localStorage.token = response.$httpHeaders('token');
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;
                $state.go("home", {}, {reload: true});
            }).catch(function (response) {
                console.log(response);
            });;
        }
    }
})();
