(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .controller('OperationController', OperationController);

    OperationController.$inject = ['entity'];

    function OperationController(entity) {
        var vm = this;

        vm.operation = entity;
    }
})();
