<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<main class="app-content">
				<form:form method="POST" modelAttribute="updateState" action="${pageContext.request.contextPath}/updateStateEntity" class="form-signin">
					<h2 class="form-signin-heading">Update State</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="stateId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="stateId" readonly="true" value="${state_to_update.stateID}" class="form-control" autofocus="true"></form:input>
							<form:errors path="stateId"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="stateName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="stateName" class="form-control" value="${state_to_update.stateName}" autofocus="true"></form:input>
							<form:errors path="stateName"></form:errors>
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

                                          <a href="${pageContext.request.contextPath}/viewStates" class="btn btn-lg btn-danger col-lg-5">Go Back</a>
                                       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                                       </div>
					</form:form>
				</div>
			</main>