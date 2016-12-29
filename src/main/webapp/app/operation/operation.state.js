(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('operations', {
                url: '/operations',
                templateUrl: 'app/operation/operations.html',
                controller: 'operationsController',
                controllerAs: 'vm'
            })
            .state('operation', {
                url: '/operations/{operationId}',
                templateUrl: 'app/operation/operation.html',
                controller: 'operationController',
                controllerAs: 'vm',
                resolve: {
                    entity: ['$stateParams', 'Operation', function($stateParams, Operation) {
                        return Operation.getOne($stateParams.operationId);
                    }]
                }
            });
    }
})();