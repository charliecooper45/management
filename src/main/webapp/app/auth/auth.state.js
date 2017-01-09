(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('login', {
                parent: 'app',
                url: '/login',
                views: {
                    'content@': {
                        templateUrl: 'app/auth/login.html',
                        controller: 'LoginController',
                        controllerAs: 'vm'
                    }
                }
            });
    }
})();