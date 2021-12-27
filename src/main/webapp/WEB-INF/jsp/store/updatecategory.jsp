<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<main class="app-content">
				<form:form method="POST" modelAttribute="updateStore" action="${pageContext.request.contextPath}/updateCategoryEntity" class="form-signin">
					<h2 class="form-signin-heading">Update Store</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="storeId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="storeId" readonly="true" value="${category_to_update.storeID}" class="form-control" autofocus="true"></form:input>
							<form:errors path="storeId"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="storeName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="storeName" class="form-control" value="${category_to_update.storeName}" autofocus="true"></form:input>
							<form:errors path="storeName"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="active">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                            <form:label  path="active" > Is Active </form:label>
                                                            <form:select  path="active" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                               <form:option value=""> --Select-- </form:option>
                                                               <form:option value="T"> Yes </form:option>
                                                               <form:option value="F"> No </form:option>
                                                            </form:select>
                                                            <form:errors  style="color:red;" path="active"></form:errors>
                                                         </div>
					</spring:bind>
					<spring:bind path="deleted">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                                    <form:label  path="deleted" > Is Deleted </form:label>
                                                                                    <form:select  path="deleted" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                                                       <form:option value=""> --Select-- </form:option>
                                                                                       <form:option value="T"> Yes </form:option>
                                                                                       <form:option value="F"> No </form:option>
                                                                                    </form:select>
                                                                                    <form:errors  style="color:red;" path="deleted"></form:errors>
                                                                                 </div>
					</spring:bind>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
					<c:remove var="successMessage" scope="session" />
					</form:form>
				</div>
			</main>