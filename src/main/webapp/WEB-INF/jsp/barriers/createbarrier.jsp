<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="barrierForm" action="${pageContext.request.contextPath}/saveBarrier" class="form-signin">
      <h2 class="form-signin-heading">Create Barrier</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="barrierId">
         <div class="form-group ${status.error ? 'has-error' : ''}" style="display:none;">
            <form:input type="text" path="barrierId" readonly="true" autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" autofocus="true"></form:input>
            <form:errors path="barrierId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="barrierName">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="barrierName" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Barrier Name"></form:input>
            <form:errors path="barrierName"></form:errors>
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
      <spring:bind path="barrierType">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:label path="barrierType" for="roles">Select Barrier Type</form:label>
            <form:select path="barrierType" name="barrierType" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="barrierType"></form:select>
            <form:errors path="barrierType"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="barrierIsActive">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:label path="barrierIsActive"> Is Active </form:label>
            <form:select path="barrierIsActive" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="earlierService">
               <form:option value=""> --Select-- </form:option>
               <form:option value="T"> Yes </form:option>
               <form:option value="F"> No </form:option>
            </form:select>
            <form:errors style="color:red;" path="barrierIsActive"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="barrierIsDeleted">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:label path="barrierIsDeleted"> Is Deleted </form:label>
            <form:select path="barrierIsDeleted" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="earlierService">
               <form:option value=""> --Select-- </form:option>
               <form:option value="T"> Yes </form:option>
               <form:option value="F"> No </form:option>
            </form:select>
            <form:errors style="color:red;" path="barrierIsDeleted"></form:errors>
         </div>
      </spring:bind>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
</main>
<script type="text/javascript">
  // function getStatesOnLoad(){
    //         if(document.getElementById('did') != null && document.getElementById('did').value  != null ){
      //               getStatesUpdate(document.getElementById('did').value);
        //             }
          //       }

 // function getdistrictsOnLoad(){
   //            if(document.getElementById('sid') != null && document.getElementById('sid').value  != null ){
     //                  getdistrictsUpdate(document.getElementById('did').value);
       //                }
         //          }

  //function getBarrierOnLoad(){
    //             if(document.getElementById('bid') != null && document.getElementById('bid').value  != null ){
      //                   getBarrierTypeTwo();
        //                 }
          //           }

   $(document).ready(function() {
   				getStates();
   				getBarrierType();

   // getStatesOnLoad();
   // getdistrictsOnLoad();
   // getBarrierOnLoad();
   });
</script>