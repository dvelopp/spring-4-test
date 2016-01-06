<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="../myAppJavaScripts.jsp"/>
    <script src="/resources/scripts/userController.js"></script>
</head>
<body ng-app="myApp">

<div ng-controller="UserController as userCtrl" class="ui-widget" on-dynamic-content-updated="initializeJQueryUI">
    <table ng-cloak class="data-table" cellspacing="0" after-render="initializeJQueryUI">
        <thead class="ui-widget-header">
        <th>Actions</th>
        <th>User name</th>
        <th>First name</th>
        <th>Last name</th>
        <th>User group</th>
        <th>System user</th>
        </thead>
        <tbody class="ui-widget-content">
        <tr ng-repeat="user in model.users">
            <td>
                <button class="simple-button edit-button" ng-show="model.hasUserEditAccess" ng-click="edit(user)"></button>
                <button class="simple-button delete-button" ng-show="model.hasUserDeleteAccess && !user.systemUser" ng-click="deleteDialog(user)"></button>
            </td>
            <td>{{user.userName}}</td>
            <td>{{user.firstName}}</td>
            <td>{{user.lastName}}</td>
            <td>{{user.userGroupName}}</td>
            <td>{{user.systemUser}}</td>
        </tr>
        </tbody>
    </table>
    <button ng-click="create()" class="button">Create a new user</button>
    <div><a href="/home">Go to home page</a></div>
    <div class="dialog" id="userEditDialog" class="hidden" title="{{dialogTitle}}">
        <c:import url="user_edit.jsp"/>
    </div>
    <div id="userDeleteConfirmDialog" class="hidden" title="User deletion">
        <p>Are you sure that you want to delete selected user?</p>
    </div>
</div>

</body>
</html>
