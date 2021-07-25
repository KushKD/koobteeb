<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="beatForm" action="${pageContext.request.contextPath}/saveBeat" class="form-signin">
      <h2 class="form-signin-heading">Create Beat</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="beatId">
         <div class="form-group ${status.error ? 'has-error' : ''}" style="display:none;">
            <form:input type="text" path="beatId" readonly="true" autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" autofocus="true"></form:input>
            <form:errors path="beatId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="beatName">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="beatName" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Beat Name"></form:input>
            <form:errors path="beatName"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="stateId">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:label path="stateId" for="roles">Select State</form:label>
            <form:select path="stateId" name="stateId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="states" onchange="getDistrictsViaStates(this.value)"></form:select>
            <form:errors path="stateId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="districtId">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:label path="districtId" for="roles">Select District</form:label>
            <form:select path="districtId" name="districtId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="districts"></form:select>
            <form:errors path="districtId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="sosdpoId">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:label path="sosdpoId" for="roles">Select SO /SDPO </form:label>
            <form:select path="sosdpoId" name="sosdpoId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="sosdpoId" onchange="getPoliceStationsViasosdpoid(this.value)"></form:select>
            <form:errors path="sosdpoId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="psId">
               <div class="form-group  ${status.error ? 'has-error' : ''}">
                  <form:label path="psId" for="roles">Select Police Station </form:label>
                  <form:select path="psId" name="psId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="psId"></form:select>
                  <form:errors path="psId"></form:errors>
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

   $(document).ready(function() {
   				getStates();
   				getSOSDPO();

   });
</script>