<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<main class="app-content">

	<form:form method="POST" id="form" modelAttribute="cashCollectionForm" action="${pageContext.request.contextPath}/cashCollection" class="form-signin">
		<h2 class="form-signin-heading">Cash Collection</h2>
		<c:if test="${not empty successMessage}">

		<div class="col-lg-12">
                    <div class="bs-component">
                      <div class="alert alert-dismissible alert-success">
                        <button class="close" type="button" data-dismiss="alert">×</button><strong>Success</strong> ${successMessage}
                      </div>
                    </div>
                  </div>

		</c:if>
		<br>
			<c:if test="${not empty serverError}">
				<div class="col-lg-12">
                                    <div class="bs-component">
                                      <div class="alert alert-dismissible alert-danger">
                                        <button class="close" type="button" data-dismiss="alert">×</button><strong>Failed</strong> ${serverError}
                                      </div>
                                    </div>
                                  </div>
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
						<form:select path="barrier_id" name="barrier_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="barriers" onchange="getUsersAll();" ></form:select>
						<form:errors  path="barrier_id"></form:errors>
					</div>
				</spring:bind>

            <spring:bind path="user_id">
                <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                    <form:label path="user_id" for="user_id"> User </form:label>
                    <form:select path="user_id" name="user_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="users" ></form:select>
                    <form:errors  path="user_id"></form:errors>
                </div>
            </spring:bind>


				<spring:bind path="date">
					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}" style="display:none;">
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

			 <sec:authorize access="hasAnyAuthority('CASHIER')">
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
            						<form:select path="barrier_id" name="barrier_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="barriersdisabled" onchange="getUsersAll();" ></form:select>
            						<form:errors  path="barrier_id"></form:errors>
            					</div>
            				</spring:bind>

                        <spring:bind path="user_id">
                            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                                <form:label path="user_id" for="user_id"> User </form:label>
                                <form:select path="user_id" name="user_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="users" ></form:select>
                                <form:errors  path="user_id"></form:errors>
                            </div>
                        </spring:bind>


            				<spring:bind path="date">
            					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}" style="display:none;">
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
         <input class="form-control col-md-6"  id="uid" type="hidden" value="${userId}"  />



	<c:if test="${not empty amountToCollect}">



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


 <sec:authorize access="hasAnyAuthority('CASHIER')">
<c:if test = "${canCollectCash}">
		<div class="row user">
              <div class="col-md-12">
                 <div class="tab-content">
                    <div id="user-timeline">
                       <div class="timeline-post">
                          <h2>Amount to Collect</h2>
                          <hr>
                          <div class="row">
                             <!-- Feilds -->
                             <div class="col-lg-12">
                                <div class="row">
                                   <div class=" col-lg-4 form-group">
                                      <fieldset disabled="">
                                         <label class="control-label" for="disabledInput">Closing Balance:</label>
                                         <input class="form-control" id="disabledInput" type="text" value="${totalBalance}" disabled="">
                                      </fieldset>
                                   </div>
                                    <div class=" col-lg-4 form-group">
                                     <fieldset>
                                        <label class="control-label" for="disabledInput">Enter Amount:</label>
                                        <input class="form-control" id="enterAmount" onKeyPress="return isNumber(event)" oncopy="return false" onpaste="return false" type="text" >
                                     </fieldset>
                                    </div>


                                    <div class=" col-lg-4 form-group">
                                    <label class="control-label" for="disabledInput">&nbsp;</label>
                                    <button class="btn btn-success" id="sendotp" style="margin-top:30px;" onclick="getOtp();">Proceed</button>
                                 </div>

                                  <div class=" col-lg-12 form-group">
                                   <label class="control-label text-center" style="color:red;"  id="otpmessage"></label>
                                    </div>
                                </div>

                                <div class="row user" id="verifyOTPRow" style="display:none;" >
                                     <div class=" col-lg-12 ">
                                     <form:form method="POST" onsubmit="return submit_form()"  modelAttribute="cashCollectionFormSave"  action="${pageContext.request.contextPath}/updateCashCollection" autocomplete="off">

                                                     <spring:bind path="enterotp">
                                                        <div class="form-group col-lg-4 ${status.error ? 'has-error' : ''}">
                                                           <form:label path="enterotp" for="enterotp"> Enter OTP </form:label>
                                                           <form:input type="text" path="enterotp" id="enterotp" class="form-control" onKeyPress="return isNumber(event)"  oncopy="return false" onpaste="return false" />
                                                        </div>
                                                        <form:errors  style="color:red;" path="enterotp"></form:errors>
                                                     </spring:bind>

                                                     <spring:bind path="rev_user">
                                                        <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                                           <form:input type="hidden" path="rev_user" id="rev_user" class="form-control" value="${userId}"  />
                                                        </div>
                                                        <form:errors  style="color:red;" path="rev_user"></form:errors>
                                                     </spring:bind>
                                                     <spring:bind path="amount">
                                                        <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                                           <form:input type="hidden" path="amount" id="amount" class="form-control" />
                                                        </div>
                                                        <form:errors  style="color:red;" path="amount"></form:errors>
                                                     </spring:bind>
                                                      <spring:bind path="barrierid">
                                                     <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                                        <form:input type="hidden" path="barrierid" id="barrierid" class="form-control" value="${barrierId}" />
                                                     </div>
                                                     <form:errors  style="color:red;" path="barrierid"></form:errors>
                                                  </spring:bind>



                                                     <input type="submit"  value="Submit" id="submit_cash" class="btn btn-primary col-lg-4">
                                                     <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                                                  </form:form>
                                                  </div>



                            </div>


                             </div>
                          </div>
                       </div>
                    </div>
                 </div>
              </div>
           </div>
           </div>
           </c:if>
            </sec:authorize>
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


 function getUserOnLoad(){
                 if(document.getElementById('uid') != null && document.getElementById('uid').value  != null ){
                         getUsersOnReLoad(document.getElementById('did').value,document.getElementById('sid').value,document.getElementById('bid').value);
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
      getUserOnLoad();

  });


  function getOtp(){
  $("#enterAmount").prop('disabled', true);
  var revenueUserId = document.getElementById('uid').value;
  var amount_entered = document.getElementById('enterAmount').value;
  var barrierId = document.getElementById('bid').value;

  var toataBalanceToDeposit = document.getElementById('disabledInput').value;

  if(parseInt(amount_entered, 10) > parseInt(toataBalanceToDeposit, 10)){
  alert("Amount Entered cannot exceed Closing Balance");
    $("#enterAmount").prop('disabled', false);
  }else{
    sendOtp(revenueUserId,amount_entered,barrierId);
  }



  }

   </script>

<script>
function submit_form() {

    document.getElementById('submit_cash').disabled = 'disabled';

   return true;

}
</script>
