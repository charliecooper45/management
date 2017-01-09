(function() {
    'use strict';

    angular
        .module('managementtestApp')
        .factory('Login', Login);

    Login.$inject = ['$resource'];

    function Login($resource) {
        var resourceUrl = 'api/login/';

        return $resource(resourceUrl, {}, {
            login: {
                method: 'POST',
                interceptor: {
                    response: function(response) {
                        response.resource.$httpHeaders = response.headers;
                        return response.resource;
                    }
                }
            }
        });
    }
})();
