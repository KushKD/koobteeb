<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="rolesForm" action="${pageContext.request.contextPath}/updateRoleEntity"  class="form-signin">
      <h2 class="form-signin-heading">Update Role</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="roleId">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="roleId" readonly="true" value="${role_to_update.roleId}" autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" autofocus="true"></form:input>
            <form:errors path="roleId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="roleName">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="roleName" class="form-control" value="${role_to_update.roleName}"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Role Name"></form:input>
            <form:errors path="roleName"></form:errors>
         </div>
      </spring:bind>

       <spring:bind path="roleDescription">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" path="roleDescription" class="form-control" value="${role_to_update.roleDescription}"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Role Description"></form:input>
                  <form:errors path="roleDescription"></form:errors>
               </div>
            </spring:bind>



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