<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="userEditForm" name="userEditForm" ng-submit="save()">
    <input type="hidden" name="id" ng-model="editedUser.id">
    <div ng-show="editedUser">
        <table>
            <tr ng-hide="editedUser.id">
                <td>User Name</td>
                <td><input type="text" name="userName" ng-model="editedUser.userName"></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" ng-model="editedUser.firstName"></td>
            </tr>
            <tr field-validation-error-place-holder error="validationErrors.firstName"></tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" ng-model="editedUser.lastName"></td>
            </tr>
            <tr field-validation-error-place-holder error="validationErrors.lastName"></tr>
            <tr>
                <td>Password</td>
                <td><input type="password" ng-model="editedUser.password"></td>
            </tr>
            <tr field-validation-error-place-holder error="validationErrors.password"></tr>
            <tr>
                <td>User Group</td>
                <td>
                    <select ng-model="editedUser.userGroupId">
                        <option ng-repeat="userGroup in model.userGroups" value="{{userGroup.id}}">{{userGroup.name}}</option>
                    </select>
                </td>
            </tr>
            <tr field-validation-error-place-holder error="validationErrors.userGroupId"></tr>
        </table>
    </div>

</form>