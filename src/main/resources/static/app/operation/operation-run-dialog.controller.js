(function() {
    'use strict';

    angular
        .module('managementApp')
        .controller('OperationRunDialogController', OperationRunDialogController);

    OperationRunDialogController.$inject = ['$uibModalInstance', '$stateParams'];

    function OperationRunDialogController($uibModalInstance, $stateParams) {
        var vm = this;

        vm.closeModal = closeModal;
        vm.runOperation = runOperation;

        function closeModal() {
            $uibModalInstance.dismiss('cancel');
        }

        function runOperation() {
            // TODO
            console.log('Running operation: ' + $stateParams.id);
            $uibModalInstance.close(true);
        }
    }
})();
