(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('OperationsController', OperationsController);

    OperationsController.$inject = ['Operation'];

    function OperationsController(Operation) {
        var vm = this;

        vm.message = 'Hello World from my controller in a separate file!';

        loadAll();

        function loadAll() {
            vm.operations = Operation.query();
        }
    }
})();
