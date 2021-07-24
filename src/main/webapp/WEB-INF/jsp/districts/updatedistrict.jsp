<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>

			<main class="app-content">
				<form:form method="POST" modelAttribute="districtForm" action="${pageContext.request.contextPath}/updateDistrictEntity" class="form-signin">
					<h2 class="form-signin-heading">Update District</h2>
					<c:if test="${not empty successMessage}">
						<div id="serverError" class="successMessage">${successMessage}</div>
					</c:if>
					<br>
					<c:if test="${not empty serverError}">
						<div id="serverError" class="plErroMessage">${serverError}</div>
					</c:if>
					<spring:bind path="districtId">
						<div class="form-group ${status.error ? 'has-error' : ''}" >
							<form:input type="text" path="districtId" readonly="true" autocomplete="off"  oncopy="return false" onpaste="return false" value="${district_to_update.districtId}" class="form-control" autofocus="true"></form:input>
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
							<form:input type="text" path="districtName" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" value="${district_to_update.districtName}" autofocus="true" placeholder="District Name"></form:input>
							<form:errors path="districtName"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="districtIsActive">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="districtIsActive"> Is Active </form:label>
							<form:select path="districtIsActive" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="earlierService">
								<form:option value=""> --Select-- </form:option>
								<form:option value="T"> Yes </form:option>
								<form:option value="F"> No </form:option>
							</form:select>
							<form:errors style="color:red;" path="districtIsActive"></form:errors>
						</div>
					</spring:bind>

					<spring:bind path="districtIsDeleted">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<form:label path="districtIsDeleted"> Is Deleted </form:label>
							<form:select path="districtIsDeleted" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="earlierService">
								<form:option value=""> --Select-- </form:option>
								<form:option value="T"> Yes </form:option>
								<form:option value="F"> No </form:option>
							</form:select>
							<form:errors style="color:red;" path="districtIsDeleted"></form:errors>
						</div>
					</spring:bind>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
										 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

					<c:remove var="successMessage" scope="session" /> </form:form>
				</div>
				<c:if test="${not empty district_to_update.stateID}">
                		<input class="form-control col-md-6"  id="did" type="hidden" value="${district_to_update.stateID}"  />
                	</c:if>
			</main>


			<script type="text/javascript">

			 function getStatesOnLoad(){
              if(document.getElementById('did') != null && document.getElementById('did').value  != null ){
                      getStatesUpdate(document.getElementById('did').value);
                      }
                  }
                    function getBarriers(data){
                            loadBarriers(data);
                        }

			$(document).ready(function() {
				 getStatesOnLoad();
			});
			</script>