<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="pSForm" action="${pageContext.request.contextPath}/savePs" class="form-signin">
      <h2 class="form-signin-heading">Create Police Station</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="psId">
         <div class="form-group ${status.error ? 'has-error' : ''}" style="display:none;">
            <form:input type="text" path="psId" readonly="true" autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" autofocus="true"></form:input>
            <form:errors path="psId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="psName">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="psName" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Police Station Name"></form:input>
            <form:errors path="psName"></form:errors>
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
            <form:label path="sosdpoId" for="roles">Select Police Line </form:label>
            <form:select path="sosdpoId" name="sosdpoId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="sosdpoId"></form:select>
            <form:errors path="sosdpoId"></form:errors>
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