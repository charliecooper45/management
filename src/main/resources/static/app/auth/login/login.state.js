(function() {
    'use strict';

    angular
        .module('managementApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('login', {
                parent: 'app',
                url: '/login',
                views: {
                    'content@': {
                        templateUrl: 'app/auth/login/login.html',
                        controller: 'LoginController',
                        controllerAs: 'vm'
                    }
                }
            });
    }
})();