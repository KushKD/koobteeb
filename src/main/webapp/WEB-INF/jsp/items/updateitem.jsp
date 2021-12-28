<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<main class="app-content">
				<form:form method="POST" modelAttribute="updateItemCat" action="${pageContext.request.contextPath}/saveItemCategoryEntity" class="form-signin">
					<h2 class="form-signin-heading">Update Store</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="categoryId">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="categoryId" readonly="true" value="${category_to_update.categoryId}" class="form-control" autofocus="true"></form:input>
							<form:errors path="categoryId"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="categoryName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="categoryName" class="form-control" value="${category_to_update.categoryName}" autofocus="true"></form:input>
							<form:errors path="categoryName"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="categoryDesc">
                    						<div class="form-group ${status.error ? 'has-error' : ''}">
                    							<form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="categoryDesc" class="form-control" value="${category_to_update.categoryDescription}" autofocus="true"></form:input>
                    							<form:errors path="categoryDesc"></form:errors>
                    						</div>
                    					</spring:bind>
					<spring:bind path="categoryIsActive">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                            <form:label  path="categoryIsActive" > Is Active </form:label>
                                                            <form:select  path="categoryIsActive" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                               <form:option value=""> --Select-- </form:option>
                                                               <form:option value="T"> Yes </form:option>
                                                               <form:option value="F"> No </form:option>
                                                            </form:select>
                                                            <form:errors  style="color:red;" path="categoryIsActive"></form:errors>
                                                         </div>
					</spring:bind>
					<spring:bind path="categoryIsDeleted">
						 <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                                    <form:label  path="categoryIsDeleted" > Is Deleted </form:label>
                                                                                    <form:select  path="categoryIsDeleted" class="form-control" id="earlierService" autocomplete="off"  oncopy="return false" onpaste="return false">
                                                                                       <form:option value=""> --Select-- </form:option>
                                                                                       <form:option value="T"> Yes </form:option>
                                                                                       <form:option value="F"> No </form:option>
                                                                                    </form:select>
                                                                                    <form:errors  style="color:red;" path="categoryIsDeleted"></form:errors>
                                                                                 </div>
					</spring:bind>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
					<c:remove var="successMessage" scope="session" />
					</form:form>
				</div>
			</main>