'use strict';

describe('Auth Interceptor', function() {
    var authInterceptor, $sessionStorage, config;

    beforeEach(function() {
        module('managementApp');
    });

    beforeEach(inject(function(_authInterceptor_, _$sessionStorage_) {
        authInterceptor = _authInterceptor_;
        $sessionStorage = _$sessionStorage_;
        delete $sessionStorage.token;
        config = {};
    }));

    it('should exist', function() {
        expect(authInterceptor).toBeDefined();
    });

    it('should set header when token is defined', function() {
        $sessionStorage.token = 'test-token';
        authInterceptor.request(config);
        expect(config.headers['Authorization']).toEqual('Bearer test-token');
    });

    it('should not set the header when token is undefined', function() {
        authInterceptor.request(config);
        expect(config.headers['Authorization']).toEqual(undefined);
    });
});