'use strict';

describe('Auth Factory', function() {
    var Auth, $sessionStorage, $state;

    beforeEach(function() {
        module('managementApp');
        module(function($urlRouterProvider) {
            $urlRouterProvider.deferIntercept();
        });
    });

    beforeEach(inject(function(_Auth_, _$sessionStorage_, _$state_) {
        Auth = _Auth_;
        $sessionStorage = _$sessionStorage_;
        $state = _$state_;
        spyOn($state, 'go').and.callThrough();
    }));

    it('should exist', function() {
        expect(Auth).toBeDefined();
    });

    describe('Token', function() {
        beforeEach(function() {
           delete $sessionStorage.token;
        });

        it('should return a user\'s token', function() {
            $sessionStorage.token = 'test-token';
            var token = Auth.token();
            expect(token).toEqual('test-token');
        });

        it('should return undefined if a user has no token', function() {
            var token = Auth.token();
            expect(token).toEqual(undefined);
        });

        it('should correctly show a user as authenticated', function() {
            $sessionStorage.token = 'test-token';
            var authenticated = Auth.isAuthenticated();
            expect(authenticated).toBe(true);
        });

        it('should correctly show a user is not authenticate', function() {
            var authenticated = Auth.isAuthenticated();
            expect(authenticated).toBe(false);
        });
    });

    describe('Authorize', function() {
        var event, loginState, homeState;

        beforeEach(function() {
            delete $sessionStorage.token;
            event = {
                preventDefault: jasmine.createSpy('preventdefault')
            };
            loginState = {
                name: 'login'
            };
            homeState = {
                name: 'home'
            };
        });

        it('should allow unauthenticated users to access the login page', function() {
            Auth.authorize(event, loginState);
            expect(event.preventDefault).not.toHaveBeenCalled();
            expect($state.go).not.toHaveBeenCalled();
        });

        it('should not allow authenticated users to access the login page', function() {
            $sessionStorage.token = 'test-token';
            Auth.authorize(event, loginState);
            expect(event.preventDefault).toHaveBeenCalled();
            expect($state.go).toHaveBeenCalledWith('home');
        });

        it('should not allow unauthenticated users to access the home page', function() {
            Auth.authorize(event, homeState);
            expect(event.preventDefault).toHaveBeenCalled();
            expect($state.go).toHaveBeenCalledWith('login');
        });

        it('should allow authenticated users to access the home page', function() {
            $sessionStorage.token = 'test-token';
            Auth.authorize(event, homeState);
            expect(event.preventDefault).not.toHaveBeenCalled();
            expect($state.go).not.toHaveBeenCalled();
        });
    });

    describe('Login', function() {
        var $httpBackend, $rootScope;

        beforeEach(inject(function( _$httpBackend_, _$rootScope_) {
            $httpBackend = _$httpBackend_;
            $rootScope = _$rootScope_;
        }));

        it('should handle successful login attempts', function() {
            var token = 'test-token';
            $httpBackend.whenPOST('api/login', { username: 'Fred', password: 'MyPassword' }).respond(200, {}, { Token: token});
            Auth.login('Fred', 'MyPassword');
            $httpBackend.flush();
            expect(Auth.isAuthenticated()).toEqual(true);
            expect(Auth.token()).toEqual(token);
        });
    });

    describe('Logout', function() {
        it('should log out a user successfully', function() {
            $sessionStorage.token = 'test-token';
            Auth.logout();
            expect($sessionStorage.token).toEqual(undefined);
            expect($state.go).toHaveBeenCalledWith('login', {}, {reload: true});
        });
    });
});