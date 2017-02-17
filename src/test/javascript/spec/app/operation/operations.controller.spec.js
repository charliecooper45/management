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

    beforeEach(module('managementApp'));

    beforeEach(inject(function($controller, _Operation_) {
        OperationFactory = _Operation_;

        spyOn(OperationFactory, 'query').and.callFake(function() {
            return operations;
        });
        OperationsController = $controller('OperationsController', { Operation: OperationFactory });
    }));

    it('should be defined', function() {
        expect(OperationsController).toBeDefined();
    });

    it('should initialize with a call to Operation.getAll()', function() {
        expect(OperationFactory.query).toHaveBeenCalled();
        expect(OperationsController.operations).toEqual(operations);
    });
});