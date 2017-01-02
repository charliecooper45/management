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
                controller: 'OperationsController',
                controllerAs: 'vm'
            })
            .state('operation', {
                url: '/operations/{id}',
                templateUrl: 'app/operation/operation.html',
                controller: 'OperationController',
                controllerAs: 'vm',
                resolve: {
                    entity: ['$stateParams', 'Operation', function($stateParams, Operation) {
                        return Operation.get({id:$stateParams.id});
                    }]
                }
            });
    }
})();