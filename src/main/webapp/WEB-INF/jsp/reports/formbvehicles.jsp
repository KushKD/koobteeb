<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<main class="app-content">

	<form:form method="POST" id="form" modelAttribute="reportsForm" action="${pageContext.request.contextPath}/getVehiclesReport" class="form-signin">
		<h2 class="form-signin-heading">Vehicle Report (Form B)</h2>
		<c:if test="${not empty successMessage}">
			<div id="serverError" class="successMessage">${successMessage}</div>
		</c:if>
		<br>
			<c:if test="${not empty serverError}">
				<div id="serverError" class="plErroMessage">${serverError}</div>
			</c:if>
			<div class="row">


                       <spring:bind path="barrierId">
                         <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                            <form:label path="barrierId" for="roles">Select Barrier</form:label>
                            <form:select path="barrierId" autocomplete="off"  oncopy="return false" onpaste="return false" name="stateId" class="form-control" id="barriers" ></form:select>
                            <form:errors path="barrierId"></form:errors>
                         </div>
                      </spring:bind>

                      <spring:bind path="toDate">
                        <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                            <form:label path="toDate" >Till Date</form:label>
                            <form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="toDate" id="toDate" type="text" placeholder="To Date" />
                            <form:errors  path="toDate"></form:errors>
                        </div>
                    </spring:bind>





				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
				       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

				<c:remove var="successMessage" scope="session" />
			</div>
		</form:form>
	</div>


		</main>



<script type="text/javascript">







  $( document ).ready(function() {

getBarriers('204');

   var date = new Date();
       var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());

 $('#fromDate').datepicker({
        	format: "dd-mm-yyyy",
        	autoclose: true,
        	todayHighlight: true,
        	endDate:today
        });

        $('#toDate').datepicker({
                	format: "dd-mm-yyyy",
                	autoclose: true,
                	todayHighlight: true,
                	endDate:today
                });

    getStatesOnLoad();
    getdistrictsOnLoad();
    getBarrierOnLoad();
       getVehicleType();
        getOwnerType();

  });
   </script>
