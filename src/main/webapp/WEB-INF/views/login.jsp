<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="myAppJavaScripts.jsp"/>
    <script src="/resources/scripts/loginPageController.js"></script>
</head>
<body ng-app="myApp">
<div class="login-form ui-widget" ng-controller="LoginPageController as loginCtrl">
    <form method="post" action="login" id="loginForm" name="loginForm">
        <div class="ui-widget-content" style="width: 350px; margin-left: auto; margin-right: auto;">
            <div class="ui-widget-header">Authentication</div>
            <table>
                <tr>
                    <td translate>system.pages.login-page.username</td>
                    <td><input type="text" id="username" name="username" ng-model="credentials.username"></td>
                </tr>
                <tr>
                    <td translate>system.pages.login-page.password</td>
                    <td><input type="password"id="password" name="password" ng-model="credentials.password"></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
                    <td><button type="submit" class="button">Submit</button></td>
                </tr>
            </table>
            <c:if test="${param.error != null}">
                <div class="ui-state-error"><p>Invalid username or password</p></div>
            </c:if>
        </div>

    </form>
</div>
</body>
</html>
