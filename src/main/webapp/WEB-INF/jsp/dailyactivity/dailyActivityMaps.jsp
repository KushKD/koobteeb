   <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>


<style>
        #map {
          height: 400px;
          /* The height is 400 pixels */
          width: 100%;
          /* The width is the width of the web page */
        }
        </style>




<main class="app-content">

<form:form  method="POST" id="form" modelAttribute="dailyactivitymapsForm" action="${pageContext.request.contextPath}/getDailyActivityMaps"  class="form-signin">
		<h2 class="form-signin-heading">Officer Daily Activity </h2>
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

			<div class="row">

			 <spring:bind path="stateId">
                           <div class="col-md-3  form-group  ${status.error ? 'has-error' : ''}">
                              <form:label path="stateId" for="roles">Select State</form:label>
                              <form:select path="stateId" name="stateId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="states" onchange="getDistrictsViaStates(this.value)"></form:select>
                              <form:errors path="stateId"></form:errors>
                           </div>
                        </spring:bind>
                        <spring:bind path="districtId">
                           <div class="col-md-3  form-group  ${status.error ? 'has-error' : ''}">
                              <form:label path="districtId" for="roles">Select District</form:label>
                              <form:select path="districtId" name="stateId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="districts" onchange="getBarriers(this.value)" ></form:select>
                              <form:errors path="districtId"></form:errors>
                           </div>
                        </spring:bind>

                    <spring:bind path="sosdpoId">
                                       <div class="col-md-3  form-group  ${status.error ? 'has-error' : ''}">
                                          <form:label path="sosdpoId" for="roles">Select SO /SDPO </form:label>
                                          <form:select path="sosdpoId" name="sosdpoId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="sosdpoId" onchange="getPoliceStationsViasosdpoid(this.value)"></form:select>
                                          <form:errors path="sosdpoId"></form:errors>
                                       </div>
                                    </spring:bind>
                                    <spring:bind path="psId">
                                             <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                                                <form:label path="psId" for="poicestation">Select Police Station </form:label>
                                                <form:select path="psId" name="psId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="psId" onchange="getBeat(this.value);"></form:select>
                                                <form:errors path="psId"></form:errors>
                                             </div>
                                          </spring:bind>

                                 <spring:bind path="beatId">
                                             <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                                                <form:label path="beatId" for="roles">Select Beat</form:label>
                                                <form:select path="beatId" name="beatId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="beats" onchange="getUsers(this.value);" ></form:select>
                                                <form:errors path="beatId"></form:errors>
                                             </div>
                                          </spring:bind>

            <spring:bind path="userId">
                <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
                    <form:label path="userId" for="userId"> User </form:label>
                    <form:select path="userId" name="userId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="users" ></form:select>
                    <form:errors  path="userId"></form:errors>
                </div>
            </spring:bind>
				<spring:bind path="date">
					<div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
						<form:label path="date" >Select Date</form:label>
						<form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="date" id="demoDate" type="text" placeholder="Select Date" />
						<form:errors  path="date"></form:errors>
					</div>
				</spring:bind>
				<button class="btn btn-lg btn-primary btn-block" onclick="alert('We are Here');">Submit</button>
				 <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<c:remove var="successMessage" scope="session" />
			</div>



		</form:form>
	</div>

	  <input class="form-control col-md-6"  id="did" type="hidden" value="${usersDetails.stateId}"  />
           <input class="form-control col-md-6"  id="sid" type="hidden" value="${usersDetails.districtId}"  />
            <input class="form-control col-md-6"  id="sosid" type="hidden" value="${sosdpoId}"  />
                 <input class="form-control col-md-6"  id="psid" type="hidden" value="${psId}"  />
                 <input class="form-control col-md-6"  id="beatid" type="hidden" value="${beatId}"  />
                 <input class="form-control col-md-6"  id="uid" type="hidden" value="${userId}"  />
                 <div id="data" style="display:none;" > ${data} </div>

<div class="row">
        <div id="map"></div>
</div>


		</main>

           <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_KdD9Dys8-WFmjSG3gyTjaHTKLnBsoqA&callback=initMap&libraries=&v=weekly" async  > </script>



<script type="text/javascript">

    $( document ).ready(function() {
     var date = new Date();
        var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
    $('#demoDate').datepicker({
           	format: "dd/mm/yyyy",
           	autoclose: true,
           	todayHighlight: true,
           	endDate:today
           });


     });


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

     function getSOSDPOLoad(){
                          if(document.getElementById('sosid') != null && document.getElementById('sosid').value  != null ){
                                  getSOSDPOUpdate();
                                  }
                              }



           function getPoliceStationOnLoad(){
                          if(document.getElementById('psid') != null && document.getElementById('psid').value  != null ){
                                  getPoliceStationsViasosdpoid(document.getElementById('sosid').value);
                                  }
                              }

           function getBeatOnLoad(){
                                if(document.getElementById('beatid') != null && document.getElementById('beatid').value  != null ){
                                        getBeat(document.getElementById('psid').value);
                                        }
                                    }

function getUserIDLoad(){
 if(document.getElementById('uid') != null && document.getElementById('uid').value  != null ){
                                        getUsers(document.getElementById('beatid').value);
                                        }
                                    }




      $(document).ready(function() {
       getStatesOnLoad();
       getdistrictsOnLoad();
       getSOSDPO();
       getSOSDPOLoad();
       getPoliceStationOnLoad();
       getBeatOnLoad();
       getUserIDLoad();



      });
   </script>

   <script>

   var dataServer = document.getElementById('data');

   if(dataServer.innerHTML!= null){
   var json_ = JSON.parse(JSON.stringify(dataServer.innerHTML));
   console.log("Data is:- "+json_);
// Initialize and add the map
           function initMap() {
             // The location of Uluru
             const uluru = { lat: 31.0703868, lng: 77.1869349 };
             // The map, centered at Uluru
             const map = new google.maps.Map(document.getElementById("map"), {
               zoom: 12,
               center: uluru,
             });
             // The marker, positioned at Uluru
             const marker = new google.maps.Marker({
               position: uluru,
               map: map,
             });
           }

   }else{
        //alert("Empty")
           // Initialize and add the map
           function initMap() {
             // The location of Uluru
             const uluru = { lat: 31.0703868, lng: 77.1869349 };
             // The map, centered at Uluru
             const map = new google.maps.Map(document.getElementById("map"), {
               zoom: 12,
               center: uluru,
             });
             // The marker, positioned at Uluru
             const marker = new google.maps.Marker({
               position: uluru,
               map: map,
             });
           }
   }


         </script>