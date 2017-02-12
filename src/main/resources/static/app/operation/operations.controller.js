(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('OperationsController', OperationsController);

    OperationsController.$inject = ['Operation'];

    function OperationsController(Operation) {
        var vm = this;

        loadAll();

        function loadAll() {
            vm.operations = Operation.query();
        }
    }
})();
