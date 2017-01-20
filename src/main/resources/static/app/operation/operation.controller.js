(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('OperationController', OperationController);

    OperationController.$inject = ['entity'];

    function OperationController(entity) {
        var vm = this;

        vm.operation = entity;
    }
})();
