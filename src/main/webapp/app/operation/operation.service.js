(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .factory('Operation', Operation);

    Operation.$inject = ['$resource'];

    function Operation($resource) {
        var resourceUrl = 'api/operations/:id';

        return $resource(resourceUrl);
    }
})();
