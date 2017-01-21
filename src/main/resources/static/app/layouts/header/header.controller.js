(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('HeaderController', HeaderController);

    HeaderController.$inject = ['Auth'];

    function HeaderController(Auth) {
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
