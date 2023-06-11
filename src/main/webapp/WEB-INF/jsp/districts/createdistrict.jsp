<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
			<main class="app-content">
				<form:form method="POST" modelAttribute="districtForm" action="${pageContext.request.contextPath}/saveDistrict" class="form-signin">
					<h2 class="form-signin-heading">Create District</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="districtId">
						<div class="form-group ${status.error ? 'has-error' : ''}" style="display:none;">
							<form:input type="text" path="districtId" autocomplete="off"  oncopy="return false" onpaste="return false" readonly="true" value="${state_to_update.stateID}" class="form-control" autofocus="true"></form:input>
							<form:errors path="districtId"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="stateId">
                    						<div class="form-group  ${status.error ? 'has-error' : ''}">
                    							<form:label path="stateId" for="roles">Select State</form:label>
                    							<form:select path="stateId" autocomplete="off"  oncopy="return false" onpaste="return false" name="stateId" class="form-control" id="states"></form:select>
                    							<form:errors path="stateId"></form:errors>
                    						</div>
                    					</spring:bind>

					<spring:bind path="districtName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:input type="text" path="districtName" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" value="${state_to_update.stateName}" autofocus="true" placeholder="District Name"></form:input>
							<form:errors path="districtName"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="districtIsActive">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="districtIsActive"> Is Active </form:label>
							<form:select path="districtIsActive" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="earlierService">
								<form:option value=""> --Select-- </form:option>
								<form:option value="T"> Active </form:option>
								<form:option value="F"> Inactive </form:option>
							</form:select>
							<form:errors style="color:red;" path="districtIsActive"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="districtIsDeleted">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="districtIsDeleted"> Is Deleted </form:label>
							<form:select path="districtIsDeleted" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="earlierService">
								<form:option value=""> --Select-- </form:option>
								<form:option value="T"> Active </form:option>
								<form:option value="F"> Inactive </form:option>
							</form:select>
							<form:errors style="color:red;" path="districtIsDeleted"></form:errors>
						</div>
					</spring:bind>
					  <div class="col-lg-12" style="margin-top:10px;">
                                         <button class="btn btn-lg btn-primary col-lg-5" type="submit">Save</button>

                                          <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-lg btn-danger col-lg-5">Go Back</a>
                                       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                                       </div>
					<c:remove var="successMessage" scope="session" /> </form:form>
				</div>
			</main>
			<script type="text/javascript">

			$(document).ready(function() {
				getStates();
			});
			</script>