<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<main class="app-content">
				<form:form method="POST" modelAttribute="updateUnit" action="${pageContext.request.contextPath}/updateUnitEntity" class="form-signin">
					<h2 class="form-signin-heading">Update Unit</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="unitId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="unitId" readonly="true" value="${unit_to_update.unitId}" class="form-control" autofocus="true"></form:input>
							<form:errors path="unitId"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="unitName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="unitName"  value="${unit_to_update.unitName}" class="form-control" autofocus="true"></form:input>
                            <form:errors path="unitName"></form:errors>
                        </div>
                    					</spring:bind>
					<spring:bind path="unitDescription">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="unitDescription" class="form-control" value="${unit_to_update.unitDesc}" autofocus="true"></form:input>
							<form:errors path="unitDescription"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="unitIsActive">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label  path="unitIsActive" > Is Active </form:label>
                            <form:select  path="unitIsActive" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                               <form:option value=""> --Select-- </form:option>
                               <form:option value="T"> Yes </form:option>
                               <form:option value="F"> No </form:option>
                            </form:select>
                            <form:errors  style="color:red;" path="unitIsActive"></form:errors>
                         </div>
					</spring:bind>


					<spring:bind path="unitIsDeleted">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:label  path="unitIsDeleted" > Is Deleted </form:label>
                            <form:select  path="unitIsDeleted" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                               <form:option value=""> --Select-- </form:option>
                               <form:option value="T"> Yes </form:option>
                               <form:option value="F"> No </form:option>
                            </form:select>
                            <form:errors  style="color:red;" path="unitIsDeleted"></form:errors>
                         </div>
					</spring:bind>


					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
					<c:remove var="successMessage" scope="session" />
					</form:form>
				</div>
			</main>