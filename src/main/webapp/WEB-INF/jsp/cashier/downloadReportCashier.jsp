<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<main class="app-content">

	<form:form method="POST" id="form" modelAttribute="cashCollectionForm" action="${pageContext.request.contextPath}/downloadCashierReport" class="form-signin">
		<h2 class="form-signin-heading">Cash Collection Report</h2>
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
						<form:select path="barrier_id" name="barrier_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="barriers" onchange="getUsers();" ></form:select>
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


            <spring:bind path="from_Date">
            					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}" >
            						<form:label path="from_Date" >Select From Date</form:label>
            						<form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="from_Date" id="from_date" type="text" placeholder="Select Date" />
            						<form:errors  path="from_Date"></form:errors>
            					</div>
            				</spring:bind>



				<spring:bind path="date">
					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}" >
						<form:label path="date" >Select to Date</form:label>
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
             						<form:select path="barrier_id" name="barrier_id" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="barriersdisabled" onchange="getUsers();" ></form:select>
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


                         <spring:bind path="from_Date">
                         					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}" >
                         						<form:label path="from_Date" >Select From Date</form:label>
                         						<form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="from_Date" id="from_date" type="text" placeholder="Select Date" />
                         						<form:errors  path="from_Date"></form:errors>
                         					</div>
                         				</spring:bind>



             				<spring:bind path="date">
             					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}" >
             						<form:label path="date" >Select to Date</form:label>
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

          $('#from_date').datepicker({
                  	format: "dd/mm/yyyy",
                  	autoclose: true,
                  	todayHighlight: true,
                  	endDate:today,
                  	minDate:today

                  });

      getStatesOnLoad();
      getdistrictsOnLoad();
      getBarriersOnLoad();
      getUserOnLoad();
      getBarriersOnLoadDisable();

  });




   </script>

