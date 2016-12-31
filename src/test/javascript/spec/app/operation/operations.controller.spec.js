'use strict';

describe('Operations Controller', function() {
    var $controller, OperationsController, OperationFactory;
    var operations = [
        {
            id: '1',
            name: 'Test 1'
        },
        {
            id: '2',
            name: 'Test 2'
        }
    ];

    beforeEach(module('managementtestApp'));

    beforeEach(inject(function(_$controller_, _Operation_) {
        $controller = _$controller_;
        OperationFactory = _Operation_;

        spyOn(OperationFactory, 'getAll').and.callFake(function() {
            return operations;
        });
        OperationsController = $controller('OperationsController', { Operation: OperationFactory });
    }));

    it('should be defined', function() {
        expect(OperationsController).toBeDefined();
    });

    it('should initialize with a call to Operation.getAll()', function() {
        expect(OperationFactory.getAll).toHaveBeenCalled();
        expect(OperationsController.operations).toEqual(operations);
    });
});