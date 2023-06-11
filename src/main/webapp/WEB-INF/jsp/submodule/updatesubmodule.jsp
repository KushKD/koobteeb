<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="subModuleForm" action="${pageContext.request.contextPath}/updateSubModuleEntity" enctype="multipart/form-data" class="form-signin">
      <h2 class="form-signin-heading">Update Sub Module</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="submoduleId">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="submoduleId" readonly="true" value="${submodule_to_update.submoduleId}" autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" autofocus="true"></form:input>
            <form:errors path="submoduleId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="submoduleName">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="submoduleName" class="form-control" value="${submodule_to_update.submoduleName}"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Sub Module Name"></form:input>
            <form:errors path="submoduleName"></form:errors>
         </div>
      </spring:bind>

 <div  id="submoduleIcon" class="form-group col-lg-4">
         <label for="submoduleIcon" class="form-label"> <spring:message code="form.documentry.aadhaar" text="Sub Module Icon" /> *  </label>
         <form:input class="form-control" oncopy="return false" onpaste="return false" type="file" id="submoduleIcon" path="submoduleIcon" name="submoduleIcon" />
         <form:errors style="color:red;" path="submoduleIcon"></form:errors>
      </div>

         <div  class="form-group col-lg-4">
        <a style="text-decoration:none;" href="${pageContext.request.contextPath}/downloadFile/${submodule_to_update.subiconName}" target="_blank">View Uploaded Image &nbsp; ${module_to_update.iconName}</a>
        </div>

        <spring:bind path="moduleId">
                     <div class="form-group  ${status.error ? 'has-error' : ''}">
                        <form:label path="moduleId" for="roles">Select Modules</form:label>
                        <form:select path="moduleId" name="moduleId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="modules">
                        </form:select>
                        <form:errors  path="moduleId"></form:errors>
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
       <div class="col-lg-12" style="margin-top:10px;">
                          <button class="btn btn-lg btn-primary col-lg-5" type="submit">Submit</button>

                           <a href="${pageContext.request.contextPath}/viewsubmodule" class="btn btn-lg btn-danger col-lg-5">Go Back</a>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                        </div>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
    <input class="form-control col-md-6"  id="mid" type="hidden" value="${submodule_to_update.moduleId.moduleId}"  />
</main>
<script type="text/javascript">

   function getModuledOnLoad(){
                       if(document.getElementById('mid') != null && document.getElementById('mid').value  != null ){
                               getModulesUpdated(document.getElementById('mid').value);
                               }
                           }


   $(document).ready(function() {
    getModuledOnLoad();
   });
</script>