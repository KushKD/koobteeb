<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
	<form:form method="POST" id="form" modelAttribute="showIdCardList" action="${pageContext.request.contextPath}/getIdCardsApproved" class="form-signin">
		<h2 class="form-signin-heading">Generate ID Card List</h2>
		<c:if test="${not empty successMessage}">
			<div id="serverError" class="successMessage">${successMessage}</div>
		</c:if>
		<br>
			<c:if test="${not empty serverError}">
				<div id="serverError" class="plErroMessage">${serverError}</div>
			</c:if>
			 <sec:authorize access="hasAnyAuthority('ADMIN')">
			<div class="row">
			<spring:bind path="state_id">
            					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
            						<form:label path="state_id" for="roles">State</form:label>
            						<form:select path="state_id" name="state_id" class="form-control" id="states" autocomplete="off"  oncopy="return false" onpaste="return false" onchange="getdistrictsUpdate(this.value)"></form:select>
            						<form:errors  path="state_id"></form:errors>
            					</div>
            				</spring:bind>

				<spring:bind path="district_id">
					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
						<form:label path="district_id" for="roles">District</form:label>
						<form:select path="district_id" name="district_id" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="districts" onchange="getBarriers(this.value)"></form:select>
						<form:errors  path="district_id"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="barrier_id">
					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
						<form:label path="barrier_id" for="roles"> Barrier</form:label>
						<form:select path="barrier_id" name="barrier_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="barriers" ></form:select>
						<form:errors  path="barrier_id"></form:errors>
					</div>
				</spring:bind>
				<spring:bind path="date">
					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
						<form:label path="date" >Select Date</form:label>
						<form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="date" id="demoDate" type="text" placeholder="Select Date" />
						<form:errors  path="date"></form:errors>
					</div>
				</spring:bind>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
				 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<c:remove var="successMessage" scope="session" />
			</div>
			</sec:authorize>
			 <sec:authorize access="hasAnyAuthority('REVENUE','CASHIER','OPERATOR')">
            			<div class="row">
            			<spring:bind path="state_id">
                        					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                        						<form:label path="state_id" for="roles">State</form:label>
                        						<form:select path="state_id" name="state_id" class="form-control" id="states" autocomplete="off"  oncopy="return false" onpaste="return false" onchange="getdistrictsUpdate(this.value)"></form:select>
                        						<form:errors  path="state_id"></form:errors>
                        					</div>
                        				</spring:bind>

            				<spring:bind path="district_id">
            					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
            						<form:label path="district_id" for="roles">District</form:label>
            						<form:select path="district_id" name="district_id" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="districts" onchange="getBarriersDisable(this.value)"></form:select>
            						<form:errors  path="district_id"></form:errors>
            					</div>
            				</spring:bind>
            				<spring:bind path="barrier_id">
            					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
            						<form:label path="barrier_id" for="roles"> Barrier</form:label>
            						<form:select path="barrier_id" name="barrier_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="barriersdisabled" ></form:select>
            						<form:errors  path="barrier_id"></form:errors>
            					</div>
            				</spring:bind>
            				<spring:bind path="date">
            					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
            						<form:label path="date" >Select Date</form:label>
            						<form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="date" id="demoDate" type="text" placeholder="Select Date" />
            						<form:errors  path="date"></form:errors>
            					</div>
            				</spring:bind>
            				<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            				 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
            				<c:remove var="successMessage" scope="session" />
            			</div>
            			</sec:authorize>
		</form:form>
	</div>
	 <input class="form-control col-md-6"  id="did" type="hidden" value="${stateId}"  />
       <input class="form-control col-md-6"  id="sid" type="hidden" value="${districtId}"  />
       <input class="form-control col-md-6"  id="bid" type="hidden" value="${barrierId}"  />


	<c:if test="${not empty vehicledata}">
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<div class="tile-body">
						<div class="table-responsive">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Owner Name</th>
										<th>Owner Mobile</th>
										<th>Owner Vehicle Number</th>
										<th>Card Generated </th>
										<th>View Details</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${vehicledata}" var="vehicledata_" varStatus="loopCounter">
										<tr>
											<td>
												<c:out value="${loopCounter.count}"/>
											</td>
											<td>${vehicledata_.vehicleOwnerName}</td>
											<td>${vehicledata_.vehicleOwnerMobile}</td>
											<td>${vehicledata_.vehicleOwnerVehicleNumber}</td>

											 <c:if test="${vehicledata_.genrerated_}">
											 <td class="text-center btn-primary" style="color:white;">
                                              <a href="${pageContext.request.contextPath}/generateId/${vehicledata_.vehicleOwnerId}" target="_blank" style="color:white; text-decoration:none;"  >View ID Card</a>
                                              </c:if>
                                            </td>
                                              <c:if test="${not vehicledata_.genrerated_}">
                                              <td class="text-center btn-danger" style="color:white;">
                                               <a href="#" style="color:white; text-decoration:none;"  >ID NOT Generated</a>
                                                     </td>
                                               </c:if>

											<td class="text-center btn-success" style="color:white;">
												<a href="${pageContext.request.contextPath}/getVehicleOwnerDetails/${vehicledata_.vehicleOwnerId}" style="color:white; text-decoration:none;"  >View Details</a>
											</td>

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



 function getBarriersOnLoad(){
                 if(document.getElementById('bid') != null && document.getElementById('bid').value  != null ){
                         getBarriers(document.getElementById('sid').value);
                         }
                     }

 function getBarriersOnLoadDisable(){
                  if(document.getElementById('bid') != null && document.getElementById('bid').value  != null ){
                          getBarriersDisable(document.getElementById('sid').value);
                          }
                      }


  $( document ).ready(function() {

  var date = new Date();
     var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
 $('#demoDate').datepicker({
        	format: "dd/mm/yyyy",
        	autoclose: true,
        	todayHighlight: true,
        	minDate:today,
        	endDate:today

        });

      getStatesOnLoad();
      getdistrictsOnLoad();
      getBarriersOnLoad();
      getBarriersOnLoadDisable();

  });
   </script>

<script type="text/javascript">
   $('#sampleTable').DataTable();


   </script>