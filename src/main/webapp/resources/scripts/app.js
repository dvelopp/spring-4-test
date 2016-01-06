var app = angular.module("myApp", []);

app.directive('onDynamicContentUpdated', ['$timeout', function ($timeout) {
    return {
        link: function ($scope, element, attrs) {
            $scope.$on('dynamicContentUpdated', function () {
                $timeout(function () {
                    $scope[attrs.onDynamicContentUpdated].apply();
                    initializeJQueryUI();
                }, 0, false);
            })
        }
    };
}]);

app.directive('fieldValidationErrorPlaceHolder', function(){
    return {
        restrict: 'AE',
        templateUrl: '/resources/directives/fieldValidationErrorPlaceHolder.html',
        replace:true,
        scope: {
            'error': '=error'
        }
    };
})
function initializeJQueryUI() {
    $(".simple-button.edit-button").button({
        icons: {
            primary: "ui-icon-pencil"
        },
        text: false
    });
    $(".simple-button.delete-button").button({
        icons: {
            primary: "ui-icon-trash"
        },
        text: false
    });
    $(".button").button();
}

$(document).ready(function(){
    initializeJQueryUI();
});

