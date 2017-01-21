(function() {
    'use strict';

    angular
        .module('managementApp', [
            'ngResource',
            'ui.router',
            'ngStorage',
            'ui.bootstrap'
        ])
        .run(run);

    run.$inject = ['$rootScope', 'Auth'];

    function run($rootScope, Auth) {
        var stateChangeStart = $rootScope.$on('$stateChangeStart', function (event, toState) {
            return Auth.authorize(event, toState);
        });

        $rootScope.$on('$destroy', function () {
            if(angular.isDefined(stateChangeStart) && stateChangeStart !== null) {
                stateChangeStart();
            }
        });
    }
})();