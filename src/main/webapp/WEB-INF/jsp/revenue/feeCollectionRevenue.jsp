<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<main class="app-content">
<div style="background-color:#FFFFFF; padding:30px;">
   <form:form method="POST" id="form" modelAttribute="feeCollectionFrom" action="${pageContext.request.contextPath}/getFeeReportsDateWise" class="form-signin">
      <h2 class="form-signin-heading">Fees Collection Reports</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <div class="row">
         <spring:bind path="fromDate" >
            <div class="col-md-3 form-group ${status.error ? 'has-error' : ''}" style="display:none;">
               <form:label path="fromDate" >From Date</form:label>
               <form:input class="form-control" path="fromDate" autocomplete="off"  oncopy="return false" onpaste="return false" id="fromDate" type="text" placeholder="From Date" />
               <form:errors  path="fromDate"></form:errors>
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
<div>
<hr>

   <div class="row" style="background-color:#FFFFFF; padding:20px;">
    <h4 class="form-signin-heading col-lg-12 col-md-12">Total Collection </h4> <br>
         <div class="col-lg-3 col-md-6">
            <div class="widget-small info coloured-icon">
               <div class="col-lg-12 btn-success" style="padding:30px;">
                  <h3 class="m-b-5 font-strong text-center" id="totalmoney" style="color:white;">${totalAmount}/-</h3>
                  <div class="m-b-5 text-center" style="color:white;">Total Collection  </div>
                  <div><i class="m-r-5"></i><small>&nbsp;</small></div>
               </div>
            </div>
         </div>
         <div class="col-lg-3 col-md-6">
            <div class="widget-small info coloured-icon">
               <div class="col-lg-12 btn-warning" style="padding:30px;">
                  <h3 class="m-b-5 font-strong text-center" id="totalmoney" style="color:white;">${currentDayCount}/-</h3>
                  <div class="m-b-5 text-center" style="color:white;">Todays Collection </div>
                  <div><i class="m-r-5"></i><small>&nbsp;</small></div>
               </div>
            </div>
         </div>
         <div class="col-lg-3 col-md-6">
                     <div class="widget-small info coloured-icon">
                        <div class="col-lg-12 btn-primary" style="padding:30px;">
                           <h3 class="m-b-5 font-strong text-center" id="totalmoney" style="color:white;">${totalDeposited}/-</h3>
                           <div class="m-b-5 text-center" style="color:white;">Total Deposited </div>
                           <div><i class="m-r-5"></i><small>&nbsp;</small></div>
                        </div>
                     </div>
                  </div>
                  <div class="col-lg-3 col-md-6">
                              <div class="widget-small info coloured-icon">
                                 <div class="col-lg-12 btn-danger" style="padding:30px;">
                                    <h3 class="m-b-5 font-strong text-center" id="totalmoney" style="color:white;">${totalBalance}/-</h3>
                                    <div class="m-b-5 text-center" style="color:white;">Total Balance </div>
                                    <div><i class="m-r-5"></i><small>&nbsp;</small></div>
                                 </div>
                              </div>
                           </div>
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


   });
</script>