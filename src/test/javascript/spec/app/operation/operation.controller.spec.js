'use strict';

describe('Operation Controller', function() {
    var OperationController;

    beforeEach(module('managementApp'));

    beforeEach(inject(function($injector) {
        OperationController = $injector.get('$controller')('OperationController',
            { 'entity': jasmine.createSpy('MockEntity') });
    }));

    it('should be defined', function() {
        expect(OperationController).toBeDefined();
    });
});