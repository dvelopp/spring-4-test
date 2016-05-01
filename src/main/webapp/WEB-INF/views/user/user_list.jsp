<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="../myAppJavaScripts.jsp"/>
    <script src="/resources/scripts/userController.js"></script>
    <script src="/resources/scripts/userEditController.js"></script>
    <script src="/resources/scripts/userDeleteController.js"></script>
</head>
<body ng-app="myApp">

<div ng-controller="UserOverviewController as userCtrl" class="ui-widget">
    <table ng-cloak class="table table-bordered table-hover" cellspacing="0" after-render="initializeJQueryUI">
        <thead class="ui-widget-header">
        <th>Actions</th>
        <th>User name</th>
        <th>First name</th>
        <th>Last name</th>
        <th>User group</th>
        <th>System user</th>
        </thead>
        <tbody>
        <tr ng-repeat="user in model.users">
            <td>
                <button class="btn btn-default" ng-show="model.hasUserEditAccess" ng-click="edit(user)">
                    <span class="glyphicon glyphicon-edit"></span>
                </button>
                <button class="btn btn-default" ng-show="model.hasUserDeleteAccess && !user.systemUser" ng-click="deleteUser(user)">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
            </td>
            <td>{{user.userName}}</td>
            <td>{{user.firstName}}</td>
            <td>{{user.lastName}}</td>
            <td>{{user.userGroupName}}</td>
            <td>{{user.systemUser}}</td>
        </tr>
        </tbody>
    </table>
    <button ng-click="create()" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> Create a new user</button>
    <div><a href="/home">Go to home page</a></div>
</div>
</body>
</html>
