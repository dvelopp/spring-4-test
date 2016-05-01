<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="userEditForm" name="userEditForm" ng-submit="save()">
    <input type="hidden" name="id" ng-model="editedUser.id">
    <div class="modal-header">
        <h3 class="modal-title">
            <div class="animate-switch" ng-if="!editedUser.id">New user</div>
            <div class="animate-switch" ng-if="editedUser.id">Edit user</div>
        </h3>
    </div>
    <div class="modal-body" ng-show="editedUser">
        <div class="form-group" ng-hide="editedUser.id">
            <label for="userNameInput">User Name</label>
            <input type="text" name="userName" class="form-control" id="userNameInput" ng-model="editedUser.userName">
        </div>
        <div class="form-group">
            <label for="userNameInput">First Name</label>
            <input type="text" name="userName" class="form-control" id="firstNameInput" ng-model="editedUser.firstName">

            <div field-validation-error-place-holder error="validationErrors.firstName"></div>
        </div>
        <div class="form-group">
            <label for="lastNameInput">Last Name</label>
            <input type="text" name="userName" class="form-control" id="lastNameInput" ng-model="editedUser.lastName">

            <div field-validation-error-place-holder error="validationErrors.lastName"></div>
        </div>
        <div class="form-group">
            <label for="passwordInput">Password</label>
            <input type="text" name="userName" class="form-control" id="passwordInput" ng-model="editedUser.password">

            <div field-validation-error-place-holder error="validationErrors.password"></div>
        </div>
        <div class="form-group">
            <label for="userGroupInput">User Group</label>
            <select ng-model="editedUser.userGroupId" id="userGroupInput" class="form-control">
                <option ng-repeat="userGroup in model.userGroups" value="{{userGroup.id}}">{{userGroup.name}}</option>
            </select>
            <div field-validation-error-place-holder error="validationErrors.userGroup"></div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" ng-click="saveUser()">OK</button>
        <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
</form>
