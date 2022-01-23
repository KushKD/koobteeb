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


        #propertymap .gm-style-iw{
            box-shadow:none;
            color:#515151;
            font-family: "Georgia", "Open Sans", Sans-serif;
            text-align: center;
            width: 100% !important;
            border-radius: 0;
            left: 0 !important;
            top: 20px !important;
        }

         #propertymap .gm-style > div > div > div > div > div > div > div {
            background: none!important;
        }

        .gm-style > div > div > div > div > div > div > div:nth-child(2) {
             box-shadow: none!important;
        }
         #propertymap .gm-style-iw > div > div{
            background: #FFF!important;
        }

         #propertymap .gm-style-iw a{
            text-decoration: none;
        }

         #propertymap .gm-style-iw > div{
            width: 245px !important
        }

         #propertymap .gm-style-iw .img_wrapper {
            height: 150px;
            overflow: hidden;
            width: 100%;
            text-align: center;
            margin: 0px auto;
        }

         #propertymap .gm-style-iw .img_wrapper > img {
            width: 100%;
            height:auto;
        }

         #propertymap .gm-style-iw .property_content_wrap {
            padding: 0px 20px;
        }

         #propertymap .gm-style-iw .property_title{
            min-height: auto;
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
				<button class="btn btn-lg btn-primary btn-block">Submit</button>
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

           <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDsFa0sl8ykZ1dPr1atULmlixkOuzqOT8A&callback=initMap&libraries=&v=weekly" async  > </script>



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

   var markersOnMap = null;
   var dataServer = document.getElementById('data');

   if (dataServer.innerHTML != null) {
   	var json_ = JSON.parse(JSON.stringify(dataServer.innerHTML));
   	markersOnMap = json_;
   	console.log(markersOnMap);

   	function initMap() {
   		function getContextPath() {
   			return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 0)); //2  0
   		}

   		var formURL = getContextPath() + "/downloadFile/";

   		const uluru = {
   			lat: 31.0703868,
   			lng: 77.1869349
   		};

   		const map = new google.maps.Map(document.getElementById("map"), {
   			zoom: 12,
   			center: uluru,
   			mapTypeId: google.maps.MapTypeId.ROADMAP
   		});

   		var infowindow = new google.maps.InfoWindow();

   		var marker, i;

   		for (i = 0; i < JSON.parse(markersOnMap).length; i++) {

   			marker = new google.maps.Marker({
   				position: new google.maps.LatLng(JSON.parse(markersOnMap)[i].latitude, JSON.parse(markersOnMap)[i].longitude),
   				map: map

   			});

   			content = "<div class='map_info_wrapper' style='width:350px'>" +
   				"<div style='width:130px; float:left'>" +
   				"<img src=" + formURL + JSON.parse(markersOnMap)[i].subMoculeIcon + " width='130'/>" +

   				"</div>" +
   				"<div style='width:200px;float:left;padding-left:20px'>" +
   				"<h6 style='font-size:150%;color:black'>" + JSON.parse(markersOnMap)[i].subModuleName + "</h6>" +
   				"<p style='font-size:150%;color:blue'>" + JSON.parse(markersOnMap)[i].name + "</p>" +
   				"</div>"
   			"</div>";

   			google.maps.event.addListener(marker, 'click', (function(marker, content, i) {
   				return function() {
   					infowindow.setContent(content);
   					infowindow.open(map, marker);
   				}
   			})(marker, content, i));

   		}


   	}


   } else {
   	alert("No Data Available");

   }



   </script>