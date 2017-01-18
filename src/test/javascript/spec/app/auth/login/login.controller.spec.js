'use strict';

describe('Login Controller', function() {
    var $controller, $state, $q, $rootScope, $httpBackend, Auth;

    beforeEach(function() {
        module('managementApp');
    });

    beforeEach(inject(function(_$controller_, _$state_, _$q_, _$rootScope_, _$httpBackend_, _Auth_) {
        $controller = _$controller_;
        $state = _$state_;
        $q = _$q_;
        $rootScope = _$rootScope_;
        $httpBackend = _$httpBackend_;
        Auth = _Auth_;
    }));

    it('should be defined', function() {
        var LoginController = $controller('LoginController');
        expect(LoginController).toBeDefined();
    });

    it('should handle successful login attempts', function() {
        $httpBackend.whenGET("app/layouts/navbar/navbar.html").respond(200, '');

        var $scope = {};
        spyOn($state, 'go').and.callThrough();
        var LoginController = $controller('LoginController', { $scope: $scope, $state: $state });
        spyOn(Auth, 'login').and.callFake(function() {
            var deferred = $q.defer();
            deferred.resolve();
            return deferred.promise;
        });

        LoginController.login();
        $rootScope.$apply();
        expect($state.go).toHaveBeenCalledWith('home', {}, {reload: true});
    });

    it('should handle unsuccessful login attempts', function() {
        $httpBackend.whenGET("app/layouts/navbar/navbar.html").respond(200, '');

        var $scope = {};
        spyOn($state, 'go').and.callThrough();
        var LoginController = $controller('LoginController', { $scope: $scope, $state: $state });
        spyOn(Auth, 'login').and.callFake(function() {
            var deferred = $q.defer();
            deferred.reject();
            return deferred.promise;
        });

        LoginController.login();
        $rootScope.$apply();
        expect($state.go).not.toHaveBeenCalledWith('home', {}, {reload: true});
    });
});