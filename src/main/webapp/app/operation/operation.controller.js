(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .controller('operationController', OperationController);

    OperationController.$inject = ['entity'];

    function OperationController(entity) {
        var vm = this;

        vm.operation = entity;
    }
})();
