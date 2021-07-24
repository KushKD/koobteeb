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
	<div class="modal fade" id="empModal" role="dialog">
		<div class="modal-dialog  modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Vehicle Owner Details</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Pop Up Closed -->
	<form:form method="POST" id="form" modelAttribute="reportsForm" action="${pageContext.request.contextPath}/getReports" class="form-signin">
		<h2 class="form-signin-heading">Reports Section</h2>
		<c:if test="${not empty successMessage}">
			<div id="serverError" class="successMessage">${successMessage}</div>
		</c:if>
		<br>
			<c:if test="${not empty serverError}">
				<div id="serverError" class="plErroMessage">${serverError}</div>
			</c:if>
			<div class="row">
				 <spring:bind path="stateId">
                         <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                            <form:label path="stateId" for="roles">Select States</form:label>
                            <form:select path="stateId" autocomplete="off"  oncopy="return false" onpaste="return false" name="stateId" class="form-control" id="states" onchange="getDistrictsViaStates(this.value)"></form:select>
                            <form:errors path="stateId"></form:errors>
                         </div>
                      </spring:bind>
                      <spring:bind path="districtId">
                         <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                            <form:label path="districtId" for="roles">Select District</form:label>
                            <form:select path="districtId" autocomplete="off"  oncopy="return false" onpaste="return false" name="districtId" class="form-control" id="districts" onchange="getBarriers(this.value)"></form:select>
                            <form:errors path="districtId"></form:errors>
                         </div>
                      </spring:bind>

                       <spring:bind path="barrierId">
                                                                 <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                                                                    <form:label path="barrierId" for="roles">Select Barrier</form:label>
                                                                    <form:select path="barrierId" autocomplete="off"  oncopy="return false" onpaste="return false" name="stateId" class="form-control" id="barriers" ></form:select>
                                                                    <form:errors path="barrierId"></form:errors>
                                                                 </div>
                                                              </spring:bind>



				<spring:bind path="vehicleType">
                					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                						<form:label path="vehicleType" for="roles"> Vehicle Type</form:label>
                						<form:select path="vehicleType" autocomplete="off"  oncopy="return false" onpaste="return false" name="vehicleType" class="form-control" id="vehicletype" ></form:select>
                						<form:errors  path="vehicleType"></form:errors>
                					</div>
                				</spring:bind>

                <spring:bind path="ownerType">
                                					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                                						<form:label path="ownerType" for="roles"> Owner Type</form:label>
                                						<form:select path="ownerType" autocomplete="off"  oncopy="return false" onpaste="return false" name="ownerType" class="form-control" id="ownertype" ></form:select>
                                						<form:errors  path="ownerType"></form:errors>
                                					</div>
                                				</spring:bind>

				<spring:bind path="fromDate">
					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
						<form:label path="fromDate" >From Date</form:label>
						<form:input class="form-control" path="fromDate" autocomplete="off"  oncopy="return false" onpaste="return false" id="fromDate" type="text" placeholder="From Date" />
						<form:errors  path="fromDate"></form:errors>
					</div>
				</spring:bind>

				<spring:bind path="toDate">
                					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                						<form:label path="toDate" >To Date</form:label>
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
	 <input class="form-control col-md-6"  id="did" type="hidden" value="${barrier_to_update.stateId}"  />
       <input class="form-control col-md-6"  id="sid" type="hidden" value="${barrier_to_update.districtId}"  />
       <input class="form-control col-md-6"  id="bid" type="hidden" value="${barrier_to_update.barrierType}"  />
	<c:if test="${not empty vid}">
    		<input class="form-control col-md-3"  id="vid" type="hidden" value="${vid}"  />
    	</c:if>
    	<c:if test="${not empty oid}">
        		<input class="form-control col-md-3"  id="oid" type="hidden" value="${oid}"  />
        	</c:if>

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

  function getBarrierOnLoad(){
                 if(document.getElementById('bid') != null && document.getElementById('bid').value  != null ){
                         getBarrierTypeTwo();
                         }
                     }



  $( document ).ready(function() {

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
