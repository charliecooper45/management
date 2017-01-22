'use strict';

describe('Login Controller', function() {
    var $controller, $state, $q, $rootScope, Auth, $httpBackend;

    beforeEach(function() {
        module('managementApp');
    });

    beforeEach(inject(function(_$controller_, _$state_, _$q_, _$rootScope_, _Auth_, _$httpBackend_) {
        $controller = _$controller_;
        $state = _$state_;
        $q = _$q_;
        $rootScope = _$rootScope_;
        Auth = _Auth_;
        $httpBackend = _$httpBackend_;
        $httpBackend.whenGET("app/layouts/navbar/navbar.html").respond(200, '');
        $httpBackend.whenGET("app/layouts/header/header.html").respond(200, '');
        $httpBackend.whenGET("app/auth/login/login.html").respond(200, '');
    }));

    it('should be defined', function() {
        var LoginController = $controller('LoginController');
        expect(LoginController).toBeDefined();
    });

    it('should handle successful login attempts', function() {
        spyOn($state, 'go').and.callThrough();
        var LoginController = $controller('LoginController', {$state: $state });
        spyOn(Auth, 'login').and.callFake(function() {
            var deferred = $q.defer();
            deferred.resolve();
            return deferred.promise;
        });

        LoginController.login();
        $rootScope.$apply();
        expect($state.go).toHaveBeenCalledWith('home', {}, {reload: true});
        expect(LoginController.error).toEqual(false);
    });

    it('should handle unsuccessful login attempts', function() {
        spyOn($state, 'go').and.callThrough();
        var LoginController = $controller('LoginController', { $state: $state });
        spyOn(Auth, 'login').and.callFake(function() {
            var deferred = $q.defer();
            deferred.reject();
            return deferred.promise;
        });

        LoginController.login();
        $rootScope.$apply();
        expect($state.go).not.toHaveBeenCalledWith('home', {}, {reload: true});
        expect(LoginController.error).toEqual(true);
    });
});