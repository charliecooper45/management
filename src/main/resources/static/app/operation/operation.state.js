(function() {
    'use strict';

    angular
        .module('managementApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('operations', {
                parent: 'app',
                url: '/operations',
                views: {
                    'content@': {
                        templateUrl: 'app/operation/operations.html',
                        controller: 'OperationsController',
                        controllerAs: 'vm'
                    }
                }
            })
            .state('operation', {
                parent: 'app',
                url: '/operations/{id}',
                views: {
                    'content@': {
                        templateUrl: 'app/operation/operation.html',
                        controller: 'OperationController',
                        controllerAs: 'vm',
                    }
                },
                resolve: {
                    entity: ['$stateParams', 'Operation', function($stateParams, Operation) {
                        return Operation.get({id:$stateParams.id});
                    }]
                }
            });
    }
})();