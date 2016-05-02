app.controller("LoginPageController", ['$rootScope', '$scope', '$http', '$location', function ($rootScope, $scope, $http, $location) {

    var authenticate = function (credentials, callback) {
        var headers = credentials ? {
            authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('/user', {headers: headers}).then(function (response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }, function () {
            $rootScope.authenticated = false;
            callback && callback();
        });

    };

    authenticate();
    $scope.credentials = {};

    $scope.login = function () {
        authenticate($scope.credentials, function () {
            if ($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
                $rootScope.$emit("reloadNavigation", {});
            } else {
                $location.path("/login");
                $scope.error = true;
            }
        });
    };

}]);