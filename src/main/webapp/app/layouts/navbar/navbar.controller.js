(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$localStorage', '$state'];

    function NavbarController($localStorage, $state) {
        var vm = this;

        vm.logout = logout;

        checkAuthentication();

        function checkAuthentication() {
            if($localStorage.token) {
                vm.authenticated = true;
            } else {
                vm.authenticated = false;
            }
        }

        function logout() {
            delete $localStorage.token;
            $state.go("login", {}, {reload: true});
        }
    }
})();
