(function() {
    'use strict';

    angular
        .module('managementtestApp', [])
        .controller('helloWorldController', HelloWorldController);

    function HelloWorldController() {
        var vm = this;

        vm.message = 'Hello World!';
    }
})();