app.controller("UserController", ['$rootScope', '$scope', '$http', '$location', function ($rootScope, $scope, $http) {

    $scope.userEditDialogElement = $("#userEditDialog");
    $scope.userDeleteConfirmDialog = $("#userDeleteConfirmDialog");

    $scope.model = {};
    $scope.validationErrors = {};
    $scope.editedUser;


    $scope.showDeletionDialog = false;

    $scope.deletionError;

    $scope.initializeJQueryUI = function () {
        initializeJQueryUI();
    };

    $scope.dialog = $scope.openEditUserDialog = function () {
        $scope.userEditDialogElement.dialog({
            height: 400,
            width: 500,
            modal: true,
            title: $scope.editedUser.id ? 'Edit user ' + $scope.editedUser.userName : 'Create new user',
            buttons: {
                "Save": $scope.save,
                Cancel: function () {
                    $(this).dialog('close');
                }
            }
        });
    };

    $scope.edit = function (user) {
        $scope.validationErrors = {};
        $scope.editedUser = angular.copy(user);
        if ($scope.editedUser.password) {
            $scope.editedUser.password = "******";
        }
        $scope.openEditUserDialog();
    };

    $scope.create = function () {
        $scope.validationErrors = {};
        $scope.editedUser = {};
        $scope.openEditUserDialog();
    };

    $scope.loadUsers = function () {
        $http.get("/ws/user/list").success(function (data) {
            $scope.model = data;
            $rootScope.$broadcast('dynamicContentUpdated');
        })
    };

    $scope.save = function () {
        $http({
            method: 'POST',
            url: '/ws/user/save',
            data: $.param($scope.editedUser),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).error(function (data) {
            $scope.validationErrors = data;
        }).success(function () {
            $scope.validationErrors = {};
            $scope.editedUser = undefined;
            $scope.loadUsers();
            $scope.userEditDialogElement.dialog('close');
        })
    };

    function deleteUser(user) {
        $http({
            method: 'DELETE',
            url: '/ws/user/' + user.id + '/delete'
        }).success(function () {
            $scope.loadUsers();
        }).error(function (data) {
            $scope.deletionError = data.message;
            alert($scope.deletionError);
        }).finally(function () {
            $scope.userDeleteConfirmDialog.dialog('close');
        });
    }

    $scope.deleteDialog = function (user) {
        $scope.userDeleteConfirmDialog.dialog({
            height: 250,
            modal: true,
            buttons: {
                "Delete": function () {
                    deleteUser(user);
                },
                Cancel: function () {
                    $scope.userDeleteConfirmDialog.dialog('close');
                }
            }
        });
    };

    $scope.loadUsers();



}]);