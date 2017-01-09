(function() {
    'use strict';

    angular
        .module('managementtestApp', [
            'ngResource',
            'ui.router',
            'ngStorage'
        ])
        .run(run);

        run.$inject = ['$rootScope', '$location', '$state', '$localStorage', '$http'];

        function run($rootScope, $location, $state, $localStorage, $http) {
            $rootScope.$on('$locationChangeStart', function () {
                if ($location.path() !== "/login" && !$localStorage.token) {
                    $state.go("login");
                } else if ($location.path() !== "/login") {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.token;
                }
            });
        }
})();