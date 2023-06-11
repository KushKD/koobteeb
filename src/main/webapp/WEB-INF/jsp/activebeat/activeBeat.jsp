<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<main class="app-content">
	<!-- Modal -->

	<form:form method="POST" id="form" modelAttribute="activeBeat" action="${pageContext.request.contextPath}/getActiveBeat" class="form-signin">
		<h2 class="form-signin-heading">Active Beats</h2>
		<c:if test="${not empty successMessage}">
			<div id="serverError" class="successMessage">${successMessage}</div>
		</c:if>
		<br>
			<c:if test="${not empty serverError}">
				<div id="serverError" class="plErroMessage">${serverError}</div>
			</c:if>
			<div class="row">
				<spring:bind path="toDate">
                <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                    <form:label path="toDate" >Select Date</form:label>
                    <form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="toDate" id="toDate" type="text" placeholder="To Date" />
                    <form:errors  path="toDate"></form:errors>
                </div>
            </spring:bind>


             <div class="col-lg-12" style="margin-top:10px;">
                     <button class="btn btn-lg btn-primary col-lg-5" type="submit">Submit</button>

                      <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-lg btn-danger col-lg-5">Go Back</a>
                   <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                   </div>





				<c:remove var="successMessage" scope="session" />
			</div>
		</form:form>


		<c:if test="${not empty activeBeats}">
        		<div class="row">
        			<div class="col-md-12">
        				<div class="tile">
        					<div class="tile-body">
        						<div class="table-responsive">
        							<table class="table table-hover table-bordered" id="sampleTable">
        								<thead>
        									<tr>
        										<th>S.No</th>
        										<th>Police Station Name</th>
        										<th>Beat Name</th>
        										<th>Username</th>
        										<th>Mobile Number</th>
        										<th>Date </th>

        									</tr>
        								</thead>
        								<tbody>
        									<c:forEach items="${activeBeats}" var="ab" varStatus="loopCounter">
        										<tr>
        											<td>
        												<c:out value="${loopCounter.count}"/>
        											</td>
        											<td>${ab.policeStationName}</td>
        											<td>${ab.beatName}</td>
        											<td>${ab.username}</td>
        											<td>${ab.mobile}</td>
        											<td>${ab.date}</td>


        										</tr>
        									</c:forEach>
        								</tbody>
        							</table>
        						</div>
        					</div>
        					<div></div>
        				</div>
        			</c:if>


		</main>



<script type="text/javascript">




  $( document ).ready(function() {

   var date = new Date();
   var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());


        $('#toDate').datepicker({
                	format: "dd-mm-yyyy",
                	autoclose: true,
                	todayHighlight: true,
                	endDate:today
                });

$('#sampleTable').DataTable();


  });
   </script>
