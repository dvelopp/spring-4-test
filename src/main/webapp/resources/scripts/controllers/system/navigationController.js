controllers.controller("NavigationController", ['$rootScope', '$scope', '$http', '$location', function ($rootScope, $scope, $http, $location) {

    $scope.model = {};

    var reloadNavigation = function(){
        $http.get("/ws/navigation/attributes").success(function(data) {
            $scope.model = data;
        })
    };

    $rootScope.$on("reloadNavigation", function(){
        reloadNavigation();
    });

    reloadNavigation();

    $scope.logout = function () {
        $http.post('logout', {}).success(function () {
            $rootScope.authenticated = false;
            $location.path("/");
            $rootScope.$emit("reloadNavigation", {});
        }).error(function (data) {
            $rootScope.authenticated = false;
        });
    }

}]);