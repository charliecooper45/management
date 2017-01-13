'use strict';

describe('Operation Factory', function() {
    var Operation;

    beforeEach(module('managementApp'));

    beforeEach(inject(function(_Operation_) {
        Operation = _Operation_;
    }));

    it('should exist', function() {
        expect(Operation).toBeDefined();
    });

    describe('Restful calls', function() {
        var $httpBackend, $rootScope;
        var url = 'api/operations';
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
        var operation = {
            id: '1',
            name: 'Test 1'
        };

        beforeEach(inject(function(_$httpBackend_, _$rootScope_) {
            $httpBackend = _$httpBackend_;
            $rootScope = _$rootScope_;
            $httpBackend.whenGET(url).respond(operations);
            $httpBackend.whenGET(url + '/1').respond(operation);
            $httpBackend.whenGET('app/layouts/navbar/navbar.html').respond('');
            $httpBackend.whenGET('app/auth/login.html').respond('');
        }));

        it('should call backend to retrieve all operations', function() {
            $httpBackend.expectGET('api/operations');
            $httpBackend.expectGET('app/layouts/navbar/navbar.html');
            $httpBackend.expectGET('app/auth/login.html');

            var value = Operation.query();
            $httpBackend.flush();

            expect(value.length).toEqual(2);
        });

        it('should call backend to retrieve single operation', function() {
            $httpBackend.expectGET('api/operations/1');
            $httpBackend.expectGET('app/layouts/navbar/navbar.html');
            $httpBackend.expectGET('app/auth/login.html');

            var value = Operation.get({id:1});
            $httpBackend.flush();

            expect(value).toBeDefined();
            expect(value.id).toEqual('1');
            expect(value.name).toEqual('Test 1');
        });

        afterEach(function() {
            $httpBackend.verifyNoOutstandingExpectation();
            $httpBackend.verifyNoOutstandingRequest();
        });
    });
});