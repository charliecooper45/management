'use strict';

describe('OperationRunDialog Controller', function() {
    var OperationRunDialogController, $uibModalInstance;

    beforeEach(module('managementApp'));

    beforeEach(inject(function($controller, _$stateParams_) {
        $uibModalInstance = jasmine.createSpyObj('$uibModalInstance', ['close', 'dismiss']);

        _$stateParams_.id = 1;
        OperationRunDialogController = $controller('OperationRunDialogController',
            { $uibModalInstance: $uibModalInstance, $stateParams: _$stateParams_ });
    }));

    it('should be defined', function() {
        expect(OperationRunDialogController).toBeDefined();
    });

    it('should close the modal', function() {
        OperationRunDialogController.closeModal();
        expect($uibModalInstance.dismiss).toHaveBeenCalledWith('cancel');
    });

    it('should run the operation', function() {

        OperationRunDialogController.runOperation();
        expect($uibModalInstance.close).toHaveBeenCalledWith(true);
    });
});