'use strict';

describe('Operation Controller', function() {
    var OperationController;

    beforeEach(module('managementtestApp'));

    beforeEach(inject(function($injector) {
        var locals = {
            'entity': jasmine.createSpy('MockEntity')
        };
        OperationController = $injector.get('$controller')('operationController', locals);
    }));

    it('should be defined', function() {
        expect(OperationController).toBeDefined();
    });
});