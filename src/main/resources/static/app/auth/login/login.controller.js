(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$state', 'Auth'];

    function LoginController($state, Auth) {
        var vm = this;

        vm.error = false;
        vm.login = login;

        function login() {
            Auth.login(vm.username, vm.password)
                .then(function() {
                    vm.error = false;
                    $state.go('home', {}, {reload: true});
                }).catch(function() {
                    vm.error = true;
                });
        }
    }
})();
