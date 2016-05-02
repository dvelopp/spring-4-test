var app = angular.module('myApp',
    [
        'ui.bootstrap',
        'services',
        'pascalprecht.translate',
        'ngCookies',
        'ui.router',
        'ngRoute'
    ]
);

app.config(function ($translateProvider) {
    $translateProvider.useUrlLoader('/ws/messageBundle/properties');
    $translateProvider.useCookieStorage();
    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');
});

app.directive('fieldValidationErrorPlaceHolder', function () {
    return {
        restrict: 'AE',
        templateUrl: '/resources/directives/fieldValidationErrorPlaceHolder.html',
        replace: true,
        scope: {
            'error': '=error'
        }
    };
});

app.config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'HomeController',
        controllerAs: 'controller'
    }).when('/configuration', {
        templateUrl: 'configuration.html',
        controller: 'ConfigurationController',
        controllerAs: 'controller'
    }).when('/login', {
        templateUrl: 'login.html',
        controller: 'LoginPageController',
        controllerAs: 'controller'
    }).when('/users', {
        templateUrl: 'user/user_list.html',
        controller: 'UserOverviewController',
        controllerAs: 'controller'
    }).when('/users/edit', {
        templateUrl: 'user/user_edit.html',
        controller: 'UserEditController',
        controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

});