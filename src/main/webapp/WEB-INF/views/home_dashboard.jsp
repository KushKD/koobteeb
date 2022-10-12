<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/charts.js"></script>
<main class="app-content">
   <div class="app-title">
      <div>
         <h1><i class="fa fa-dashboard"></i> Dashboard</h1>
         <p>Expenditure Monitoring Panel - State Election Commission, Government of Himachal Pradesh</p>
      </div>
      <ul class="app-breadcrumb breadcrumb">
         <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
         <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
      </ul>
   </div>

   </div>

 <div class="row">

    <sec:authorize access="hasAnyAuthority('SUPER ADMIN')">
          <div class="col-md-6 col-lg-3">
             <div class="widget-small warning coloured-icon">
                <i class="icon fa fa-lock fa-3x"></i>
                <div class="info">
                   <a href="${pageContext.request.contextPath}/getRoles" style=" text-decoration: none !important;">
                      <h5>View Roles</h5>
                   </a>
                </div>
             </div>
          </div>
          </sec:authorize>


    <sec:authorize access="hasAnyAuthority('SUPER ADMIN')">
      <div class="col-md-6 col-lg-3">
         <div class="widget-small primary coloured-icon">
            <i class="icon fa fa-id-badge fa-3x"></i>
            <div class="info">
               <a href="${pageContext.request.contextPath}/viewUsers" style=" text-decoration: none !important;">
                  <h5>View Users</h5>
               </a>
            </div>
         </div>
      </div>
      </sec:authorize>

       <sec:authorize access="hasAnyAuthority('SUPER ADMIN')">
            <div class="col-md-6 col-lg-3">
               <div class="widget-small danger coloured-icon">
                  <i class="icon fa fa-get-pocket fa-3x"></i>
                  <div class="info">
                     <a href="${pageContext.request.contextPath}/viewmodule" style=" text-decoration: none !important;">
                        <h5>View Modules</h5>
                     </a>
                  </div>
               </div>
            </div>
            </sec:authorize>



      <sec:authorize access="hasAnyAuthority('SUPER ADMIN')">
         <div class="col-md-6 col-lg-3">
            <div class="widget-small primary coloured-icon">
               <i class="icon fa fa-anchor fa-3x"></i>
               <div class="info">
                  <a href="${pageContext.request.contextPath}/viewmodulerolemapping" style=" text-decoration: none !important;">
                     <h5>View Role Module Mapping</h5>
                  </a>
               </div>
            </div>
         </div>
      </sec:authorize>

       <sec:authorize access="hasAnyAuthority('SUPER ADMIN')">
               <div class="col-md-6 col-lg-3">
                  <div class="widget-small primary coloured-icon">
                     <i class="icon fa fa-sign-in fa-3x"></i>
                     <div class="info">
                        <a href="${pageContext.request.contextPath}/beatOfficerLogs" style=" text-decoration: none !important;">
                           <h5>Mobile Application Access Logs</h5>
                        </a>
                     </div>
                  </div>
               </div>
            </sec:authorize>

       <sec:authorize access="hasAnyAuthority('SUPER ADMIN','Bank Official')">
                      <div class="col-md-6 col-lg-3">
                         <div class="widget-small danger coloured-icon">
                            <i class="icon fa fa-exchange fa-3x"></i>
                            <div class="info">
                               <a href="${pageContext.request.contextPath}/createtransfer" style=" text-decoration: none !important;">
                                  <h5>Create Transfer Request</h5>
                               </a>
                            </div>
                         </div>
                      </div>
                   </sec:authorize>



        <sec:authorize access="hasAnyAuthority('Bank Official')">
                             <div class="col-md-6 col-lg-3">
                                <div class="widget-small info coloured-icon">
                                   <i class="icon fa fa-exchange fa-3x"></i>
                                   <div class="info">
                                      <a href="${pageContext.request.contextPath}/viewTransfer" style=" text-decoration: none !important;">
                                         <h5>View Transfers Role Wise</h5>
                                      </a>
                                   </div>
                                </div>
                             </div>
                          </sec:authorize>

         <sec:authorize access="hasAnyAuthority('SUPER ADMIN')">
                 <div class="col-md-6 col-lg-3">
                    <div class="widget-small warning coloured-icon">
                       <i class="icon fa fa-lock fa-3x"></i>
                       <div class="info">
                          <a href="${pageContext.request.contextPath}/viewAllTransfer" style=" text-decoration: none !important;">
                             <h5>View Complete Transfers</h5>
                          </a>
                       </div>
                    </div>
                 </div>
                 </sec:authorize>

          <sec:authorize access="hasAnyAuthority('SUPER ADMIN')">
                                     <div class="col-md-6 col-lg-3">
                                        <div class="widget-small primary coloured-icon">
                                           <i class="icon fa fa-lock fa-3x"></i>
                                           <div class="info">
                                              <a href="${pageContext.request.contextPath}/viewpin" style=" text-decoration: none !important;">
                                                 <h5>Pin Management</h5>
                                              </a>
                                           </div>
                                        </div>
                                     </div>
                                  </sec:authorize>

  <sec:authorize access="hasAuthority('SUPER ADMIN')">
   <div class="row col-lg-12">
      <div class="col-lg-12">
         <div class="tile" style="height:100%; width:100%;">
            <h3 class="tile-title">Transfer Request Generation (Bank Wise)</h3>
            <div>
               <canvas style="width:100%; height:90%;" id="barChartCustom1"></canvas>
            </div>
         </div>
      </div>

   </div>
   </sec:authorize>






   </div>
   </div>

</main>

<script>
	function setChart(data) {
		var obj = JSON.constructor(JSON.parse(data));
		console.log(obj);

		var labels = obj.members.map(e => e.barrierName);
		console.log(labels);
		var data = obj.members.map(e => e.noOfIdCardGenerated);
		console.log(data);

		Chart.defaults.global.defaultFontColor = '#75787c';
		// ------------------------------------------------------- //
		// Bar Chart Custom 1
		// ------------------------------------------------------ //
		var $chart = $('#barChartCustom1');
		var barChartHome = new Chart($chart[0].getContext("2d"), {
			type: 'bar',
			data: {
				labels: labels,


				datasets: [{
					label: 'Total Request made (Bank Wise) ',
					data: data,
                    hoverOffset: 4,
                    backgroundColor: "rgb(54, 162, 235)",
                    borderWidth: 1
				}]

			}
		})
	}

function getTotalCards() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getTotalIDCards",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			setChart(json_.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}




getTotalCards();

</script>