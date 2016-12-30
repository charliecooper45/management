'use strict';

describe('Operations Controller', function() {
    var $controller, OperationsController;

    beforeEach(module('managementtestApp'));

    beforeEach(inject(function(_$controller_) {
        $controller = _$controller_;
        OperationsController = $controller('operationsController', {});
    }));

    it('should be defined', function() {
        expect(OperationsController).toBeDefined();
    });
});