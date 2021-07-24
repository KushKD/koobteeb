<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
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
      <div class="modal-body">

      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
     </div>
    </div>
   </div>

   <!-- Modal Pop Up Closed -->

        <form:form method="POST" modelAttribute="searchId" action="${pageContext.request.contextPath}/getIdCardsSearch" class="form-signin">
            <h2 class="form-signin-heading">Search ID Card </h2>
              <c:if test="${not empty successMessage}">
                                <div id="serverError" class="successMessage">${successMessage}</div>
                            </c:if>
                             <br>
                            <c:if test="${not empty serverError}">
                                <div id="serverError" class="plErroMessage">${serverError}</div>
                            </c:if>


                        <div class="row">
                         <spring:bind path="vehicleNumber">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">

                            <form:input path="vehicleNumber" autocomplete="off"  oncopy="return false" onpaste="return false" name="vehicleNumber" class="form-control" type="text" placeholder="Enter Vehicle Number" required="required" />
                              <form:errors  path="vehicleNumber"></form:errors>
                            </div>
                             </spring:bind>


                          <spring:bind path="mobileNumber">
                          <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">

                            <form:input path="mobileNumber" autocomplete="off"  oncopy="return false" onpaste="return false" name="mobileNumber" class="form-control" type="text" placeholder="Enter Mobile Number"   />

                              <form:errors  path="mobileNumber"></form:errors>
                            </div>
                             </spring:bind>




            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                   <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

               <c:remove var="successMessage" scope="session" />

               </div>
        </form:form>

    </div>
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
                                                                      <a href="${pageContext.request.contextPath}/generateId/${vehicledata_.vehicleOwnerId}" style="color:white; text-decoration:none;"  >Download ID Card</a>
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
                               <div>
                               </div>
                               </div>
</c:if>

    </main>



<script type="text/javascript">

  $( document ).ready(function() {
 $('#demoDate').datepicker({
        	format: "dd/mm/yyyy",
        	autoclose: true,
        	todayHighlight: true
        });

  });
   </script>
   <script type="text/javascript">
   $('#sampleTable').DataTable();
   </script>