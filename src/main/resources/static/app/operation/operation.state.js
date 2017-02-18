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
            })
            .state('operation.run', {
                parent: 'operation',
                url: '/run',
                onEnter: ['$state', '$uibModal', function($state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/operation/operation-run-dialog.html',
                        controller: 'OperationRunDialogController',
                        controllerAs: 'vm',
                        size: 'md'
                    }).result.then(function() {
                        $state.go('^');
                    }, function() {
                        $state.go('^');
                    });
                }]
            });
    }
})();