var app = angular.module('myApp', ['ui.bootstrap', 'services', 'pascalprecht.translate','ngCookies']);

app.config(function ($translateProvider) {
    $translateProvider.useUrlLoader('/messageBundle');
    $translateProvider.useCookieStorage();
    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');
});

app.directive('fieldValidationErrorPlaceHolder', function(){
    return {
        restrict: 'AE',
        templateUrl: '/resources/directives/fieldValidationErrorPlaceHolder.html',
        replace:true,
        scope: {
            'error': '=error'
        }
    };
});


