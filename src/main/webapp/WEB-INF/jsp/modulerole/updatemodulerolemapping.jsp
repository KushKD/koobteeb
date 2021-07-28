<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="moduleRoleForm" action="${pageContext.request.contextPath}/updateModuleRoleEntity" enctype="multipart/form-data" class="form-signin">
      <h2 class="form-signin-heading">Update Module</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="id">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="id" readonly="true" value="${module_to_update.moduleRoleId}" autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" autofocus="true"></form:input>
            <form:errors path="id"></form:errors>
         </div>
      </spring:bind>

      <spring:bind path="moduleId">
                    <div class="form-group  ${status.error ? 'has-error' : ''}">
                       <form:label path="moduleId" for="roles">Select Modules</form:label>
                       <form:select path="moduleId" name="moduleId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="modules">
                       </form:select>
                       <form:errors  path="moduleId"></form:errors>
                    </div>
                 </spring:bind>

          <spring:bind path="roleId">
                   <div class="form-group  ${status.error ? 'has-error' : ''}">
                      <form:label path="roleId" for="roles">Select Role</form:label>
                      <form:select path="roleId" name="roleId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="roles">
                      </form:select>
                      <form:errors  path="roleId"></form:errors>
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


      <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
         <input class="form-control col-md-6"  id="rid" type="hidden" value="${module_to_update.roleId}"  />
         <input class="form-control col-md-6"  id="mid" type="hidden" value="${module_to_update.moduleId}"  />
</main>
<script type="text/javascript">


   function getrolesOnLoad(){
                    if(document.getElementById('rid') != null && document.getElementById('rid').value  != null ){
                            getRolesUpdated(document.getElementById('rid').value);
                            }
                        }

   function getModuledOnLoad(){
                       if(document.getElementById('mid') != null && document.getElementById('mid').value  != null ){
                               getModulesUpdated(document.getElementById('mid').value);
                               }
                           }


   $(document).ready(function() {
    getrolesOnLoad();
    getModuledOnLoad();
   });
</script>