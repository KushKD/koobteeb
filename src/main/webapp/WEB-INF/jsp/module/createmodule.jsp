<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="moduleForm" action="${pageContext.request.contextPath}/saveModule" enctype="multipart/form-data" class="form-signin">
      <h2 class="form-signin-heading">Create Module</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="moduleId">
         <div class="form-group ${status.error ? 'has-error' : ''}" style="display:none;">
            <form:input type="text" path="moduleId" readonly="true" autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" autofocus="true"></form:input>
            <form:errors path="moduleId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="moduleName">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="moduleName" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Module Name"></form:input>
            <form:errors path="moduleName"></form:errors>
         </div>
      </spring:bind>

      <div  id="moduleIcon" class="form-group col-lg-4">
         <label for="moduleIcon" class="form-label">
            <spring:message code="form.documentry.aadhaar" text="Module Icon" />
            *
         </label>
         <form:input class="form-control" oncopy="return false" onpaste="return false" type="file" id="moduleIcon" path="moduleIcon" name="moduleIcon" />
         <form:errors style="color:red;" path="moduleIcon"></form:errors>
      </div>


      <spring:bind path="isActive">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:label path="isActive"> Is Active </form:label>
            <form:select path="isActive" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="earlierService">
               <form:option value=""> --Select-- </form:option>
               <form:option value="T"> Yes </form:option>
               <form:option value="F"> No </form:option>
            </form:select>
            <form:errors style="color:red;" path="isActive"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="isDeleted">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:label path="isDeleted"> Is Deleted </form:label>
            <form:select path="isDeleted" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="earlierService">
               <form:option value=""> --Select-- </form:option>
               <form:option value="T"> Yes </form:option>
               <form:option value="F"> No </form:option>
            </form:select>
            <form:errors style="color:red;" path="isDeleted"></form:errors>
         </div>
      </spring:bind>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
</main>
<script type="text/javascript">


</script>