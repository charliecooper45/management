(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['Auth'];

    function NavbarController(Auth) {
        var vm = this;

        vm.logout = logout;

        checkAuthentication();

        function checkAuthentication() {
            vm.authenticated = Auth.isAuthenticated();
        }

        function logout() {
            Auth.logout();
        }
    }
})();
