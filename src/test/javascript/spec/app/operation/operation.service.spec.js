'use strict';

describe('Operation Factory', function() {
    var Operation;
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

    beforeEach(module('managementtestApp'));

    beforeEach(inject(function(_Operation_) {
        Operation = _Operation_;
    }));

    it('should exist', function() {
        expect(Operation).toBeDefined();
    });

    describe('.getAll()', function() {

        it('should exist', function() {
            expect(Operation.getAll).toBeDefined();
        });

        it('should return a hard coded list of users', function() {
            expect(Operation.getAll()).toEqual(operations);
        });
    });

    describe('.getOne()', function() {

        it('should exist', function() {
            expect(Operation.getOne).toBeDefined();
        });

        it('should return one operation object if it exists', function() {
            expect(Operation.getOne('1')).toEqual(operation);
        });

        it('should return undefined if operation object does not exist', function() {
            expect(Operation.getOne('3')).toBeUndefined();
        });
    });
});