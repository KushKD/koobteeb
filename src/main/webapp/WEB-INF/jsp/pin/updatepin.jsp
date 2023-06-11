<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<main class="app-content">
				<form:form method="POST" modelAttribute="pinForm" action="${pageContext.request.contextPath}/updatePinEntity" class="form-signin">
					<h2 class="form-signin-heading">Update Pin</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="pinId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="pinId" readonly="true" value="${pin_to_update.pinId}" class="form-control" autofocus="true"></form:input>
							<form:errors path="pinId"></form:errors>
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
					<spring:bind path="pin">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" maxlength="6" onpaste="return false" path="pin" class="form-control" value="${pin_to_update.pin}" autofocus="true"></form:input>
							<form:errors path="pin"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="stateIsActive">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                            <form:label  path="stateIsActive" > Is Active </form:label>
                                                            <form:select  path="stateIsActive" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                               <form:option value=""> --Select-- </form:option>
                                                               <form:option value="T"> Yes </form:option>
                                                               <form:option value="F"> No </form:option>
                                                            </form:select>
                                                            <form:errors  style="color:red;" path="stateIsActive"></form:errors>
                                                         </div>
					</spring:bind>
					<spring:bind path="stateIsDeleted">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                                    <form:label  path="stateIsDeleted" > Is Deleted </form:label>
                                                                                    <form:select  path="stateIsDeleted" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                                                       <form:option value=""> --Select-- </form:option>
                                                                                       <form:option value="T"> Yes </form:option>
                                                                                       <form:option value="F"> No </form:option>
                                                                                    </form:select>
                                                                                    <form:errors  style="color:red;" path="stateIsDeleted"></form:errors>
                                                                                 </div>
					</spring:bind>
					  <div class="col-lg-12" style="margin-top:10px;">
                                         <button class="btn btn-lg btn-primary col-lg-5" type="submit">Submit</button>

                                          <a href="${pageContext.request.contextPath}/viewpin" class="btn btn-lg btn-danger col-lg-5">Go Back</a>
                                       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                                       </div>
					<c:remove var="successMessage" scope="session" />
					</form:form>
				</div>
				<input class="form-control col-md-6"  id="did" type="hidden" value="${pin_to_update.stateID.stateID}"  />
                      <input class="form-control col-md-6"  id="sid" type="hidden" value="${pin_to_update.districtId.districtId}"  />
			</main>


<script type="text/javascript">

 function getStatesOnLoad(){
             if(document.getElementById('did') != null && document.getElementById('did').value  != null ){
                     getStatesUpdate(document.getElementById('did').value);
                     }
                 }

  function getdistrictsOnLoad(){
               if(document.getElementById('sid') != null && document.getElementById('sid').value  != null ){
                       getdistrictsUpdate(document.getElementById('did').value);
                       }
                   }



   $(document).ready(function() {
    getStatesOnLoad();
    getdistrictsOnLoad();
   });


</script>