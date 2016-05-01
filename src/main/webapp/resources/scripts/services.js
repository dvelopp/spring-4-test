'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('myLang', ['$location', function ($location) {
    return {
        put: function (name, value) {
        },
        get: function (name) {
            return $location.search()['lang']
        }
    }
}]);