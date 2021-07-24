<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>



<main class="app-content">

        <form:form method="POST" modelAttribute="stateForm" action="${pageContext.request.contextPath}/saveState" class="form-signin">
            <h2 class="form-signin-heading">Create State</h2>
             <c:if test="${not empty successMessage}">
                    <div id="serverError" class="successMessage">${successMessage}</div>
                </c:if>
                 <br>
                <c:if test="${not empty serverError}">
                    <div id="serverError" class="plErroMessage">${serverError}</div>
                </c:if>
            <spring:bind path="stateName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="stateName" class="form-control" placeholder="State Name"
                                autofocus="true"></form:input>
                    <form:errors  path="stateName"></form:errors>
                </div>
            </spring:bind>


            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
             <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            <c:remove var="successMessage" scope="session" />
        </form:form>


    </div>
    </main>


