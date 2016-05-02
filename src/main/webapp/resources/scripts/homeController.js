app.controller("HomeController", ['$rootScope', '$scope', '$http', '$location', function ($rootScope, $scope, $http) {

    $scope.model = {};

    $http.get("/ws/home/attributes").success(function(data) {
        $scope.model = data;
    })

}]);