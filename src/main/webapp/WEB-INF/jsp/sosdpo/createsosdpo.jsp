<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>



<main class="app-content">

        <form:form method="POST" modelAttribute="soSdpoForm" action="${pageContext.request.contextPath}/savesosdpo" class="form-signin">
            <h2 class="form-signin-heading">Create So/SDPO </h2>
             <c:if test="${not empty successMessage}">
                    <div id="serverError" class="successMessage">${successMessage}</div>
                </c:if>
                 <br>
                <c:if test="${not empty serverError}">
                    <div id="serverError" class="plErroMessage">${serverError}</div>
                </c:if>
            <spring:bind path="soSdpo">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="soSdpo" class="form-control" placeholder="SO/SDPO Name"
                                autofocus="true"></form:input>
                    <form:errors  path="soSdpo"></form:errors>
                </div>
            </spring:bind>


              <div class="col-lg-12" style="margin-top:10px;">
                                 <button class="btn btn-lg btn-primary col-lg-5" type="submit">Submit</button>

                                  <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-lg btn-danger col-lg-5">Go Back</a>
                               <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                               </div>
            <c:remove var="successMessage" scope="session" />
        </form:form>


    </div>
    </main>


