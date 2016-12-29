(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .factory('Operation', Operation);

    function Operation() {
        var service = {
            getAll: getAll,
            getOne: getOne
        }

        return service;

        function getAll() {
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

            return operations;
        }

        function getOne(id) {
            return getAll().find(function(element) {
                return element.id === id;
            });
        }
    }
})();
