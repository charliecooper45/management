(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .controller('operationsController', OperationsController);

    OperationsController.$inject = ['Operation'];

    function OperationsController(Operation) {
        var vm = this;

        vm.message = 'Hello World from my controller in a separate file!';

        loadAll();

        function loadAll() {
            vm.operations = Operation.getAll();
        }
    }
})();
