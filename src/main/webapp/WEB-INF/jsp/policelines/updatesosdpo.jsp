<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<main class="app-content">
				<form:form method="POST" modelAttribute="soSdpoUpdate" action="${pageContext.request.contextPath}/updateSoSDPOEntity" class="form-signin">
					<h2 class="form-signin-heading">Update Police Line</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="policelineId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="policelineId" readonly="true" value="${sosdpo_to_update.policelineId}" class="form-control" autofocus="true"></form:input>
							<form:errors path="policelineId"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="policelineName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="policelineName" class="form-control" value="${sosdpo_to_update.policelineName}" autofocus="true"></form:input>
							<form:errors path="policelineName"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="soSdpoActive">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                            <form:label  path="soSdpoActive" > Is Active </form:label>
                                                            <form:select  path="soSdpoActive" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                               <form:option value=""> --Select-- </form:option>
                                                               <form:option value="T"> Yes </form:option>
                                                               <form:option value="F"> No </form:option>
                                                            </form:select>
                                                            <form:errors  style="color:red;" path="soSdpoActive"></form:errors>
                                                         </div>
					</spring:bind>
					<spring:bind path="soSdpoDeleted">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                                    <form:label  path="soSdpoDeleted" > Is Deleted </form:label>
                                                                                    <form:select  path="soSdpoDeleted" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                                                       <form:option value=""> --Select-- </form:option>
                                                                                       <form:option value="T"> Yes </form:option>
                                                                                       <form:option value="F"> No </form:option>
                                                                                    </form:select>
                                                                                    <form:errors  style="color:red;" path="soSdpoDeleted"></form:errors>
                                                                                 </div>
					</spring:bind>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
					<c:remove var="successMessage" scope="session" />
					</form:form>
				</div>
			</main>