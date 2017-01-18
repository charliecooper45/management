'use strict';

describe('Home Controller', function() {
    var HomeController;

    beforeEach(module('managementApp'));

    beforeEach(inject(function($injector) {
        HomeController = $injector.get('$controller')('HomeController');
    }));

    it('should be defined', function() {
        expect(HomeController).toBeDefined();
    });
});