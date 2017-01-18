(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$state', 'Auth'];

    function LoginController($state, Auth) {
        var vm = this;

        vm.login = login;

        function login() {
            Auth.login(vm.username, vm.password)
                .then(function() {
                    $state.go('home', {}, {reload: true});
                }).catch(function() {
                    console.log('failure logging in!');
                });
        }
    }
})();
