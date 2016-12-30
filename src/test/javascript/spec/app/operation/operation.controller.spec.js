'use strict';

describe('Operation Controller', function() {
    var OperationController;

    beforeEach(module('managementtestApp'));

    beforeEach(inject(function($injector) {
        OperationController = $injector.get('$controller')('operationController',
            { 'entity': jasmine.createSpy('MockEntity') });
    }));

    it('should be defined', function() {
        expect(OperationController).toBeDefined();
    });
});