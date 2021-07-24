<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/charts.js"></script>
<main class="app-content">
   <div class="app-title">
      <div>
         <h1><i class="fa fa-dashboard"></i> Dashboard</h1>
         <p>Himachal Police Transport ID Card Panel</p>
      </div>
      <ul class="app-breadcrumb breadcrumb">
         <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
         <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
      </ul>
   </div>
   <div class="row">
       <sec:authorize access="hasAnyAuthority('ADMIN', 'REVENUE')">
            <div class="col-md-6 col-lg-3">
               <div class="widget-small primary coloured-icon">
                  <i class="icon fa fa-id-badge fa-3x"></i>
                  <div class="info">
                     <a href="${pageContext.request.contextPath}/showIdCards" style=" text-decoration: none !important;">
                        <h4>View ID Card List</h4>
                     </a>
                  </div>
               </div>
            </div>
            </sec:authorize>
              <sec:authorize access="hasAnyAuthority('ADMIN', 'REVENUE')">
            <div class="col-md-6 col-lg-3">
               <div class="widget-small info coloured-icon">
                  <i class="icon fa fa-search fa-3x"></i>
                  <div class="info">
                     <a href="${pageContext.request.contextPath}/searchId" style=" text-decoration: none !important;">
                        <h4>Search ID Card</h4>
                     </a>
                  </div>
               </div>
            </div>
            </sec:authorize>
      <sec:authorize access="hasAuthority('ADMIN')">
         <div class="col-md-6 col-lg-3">
            <div class="widget-small warning coloured-icon">
               <i class="icon fa fa-user fa-3x"></i>
               <div class="info">
                  <a href="${pageContext.request.contextPath}/createUser" style=" text-decoration: none !important;">
                     <h4>Create User</h4>
                  </a>
               </div>
            </div>
         </div>
      </sec:authorize>
        <sec:authorize access="hasAnyAuthority('CASHIER','ADMIN')">
                     <div class="col-md-6 col-lg-3">
                        <div class="widget-small warning coloured-icon">
                           <i class="icon fa fa-inr fa-3x"></i>
                           <div class="info">
                              <a href="${pageContext.request.contextPath}/cashVerificationn" style=" text-decoration: none !important;">
                                 <h4>Cash Collection</h4>
                              </a>
                           </div>
                        </div>
                     </div>
                  </sec:authorize>
                  <sec:authorize access="hasAuthority('REVENUE')">
                                 <div class="col-md-6 col-lg-3">
                                    <div class="widget-small warning coloured-icon">
                                       <i class="icon fa fa-money fa-3x"></i>
                                       <div class="info">
                                          <a href="${pageContext.request.contextPath}/feeCollectionRevenue" style=" text-decoration: none !important;">
                                             <h4>Fee Collection</h4>
                                          </a>
                                       </div>
                                    </div>
                                 </div>
                              </sec:authorize>
      <sec:authorize access="hasAuthority('ADMIN')">
         <div class="col-md-6 col-lg-3">
            <div class="widget-small danger coloured-icon">
               <i class="icon fa fa-file-excel-o fa-3x"></i>
               <div class="info">
                  <a href="${pageContext.request.contextPath}/generateReport" style=" text-decoration: none !important;">
                     <h4>Generate Reports</h4>
                  </a>
               </div>
            </div>
         </div>
      </sec:authorize>


   </div>
   </div>
   <sec:authorize access="hasAuthority('ADMIN')">
   <div class="row">
      <div class="col-md-6">
         <div class="tile" style="height:100%; width:100%;">
            <h3 class="tile-title">Barrier Wise ID Card Generation</h3>
            <div>
               <canvas id="barChartCustom1"></canvas>
            </div>
            <div class="row">
               <div class="col-lg-12">
                  <p class="bs-component text-center">
                     <button class="btn btn-outline-primary" onclick="getTotalCards();" type="button">Total</button>
                     <button class="btn btn-outline-secondary" onclick="getTotalCardsMonthly();" type="button">Monthly</button>
                     <button class="btn btn-outline-success" onclick="getTotalCardsDaily();" type="button">Daily</button>
                  </p>
               </div>
            </div>
         </div>
      </div>
      <div class="col-md-6">
         <div class="tile" style="height:100%; width:100%;">
            <h3 class="tile-title">Vehicle Type</h3>
                <div>
                   <canvas id="pieChartVehicleType"></canvas>
                </div>

                <div class="row">
                  <div class="col-lg-12">
                     <p class="bs-component text-center">
                        <button class="btn btn-outline-primary" onclick="getTotalVehicleType();" type="button">Total</button>
                        <button class="btn btn-outline-secondary" onclick="getMonthlyVehicleType();" type="button">Monthly</button>
                        <button class="btn btn-outline-success" onclick="getDailyVehicleType();" type="button">Daily</button>
                     </p>
                  </div>
                </div>
         </div>
      </div>
   </div>
   </sec:authorize>

   <sec:authorize access="hasAuthority('ADMIN')">
   <br><hr>
      <div class="row">
         <div class="col-md-6">
            <div class="tile" style="height:100%; width:100%;">
               <h3 class="tile-title">Collection Report Barrier Wise</h3>
               <div>
                  <canvas id="barrierCollection"></canvas>
               </div>
               <div class="row">
                  <div class="col-lg-12">
                     <p class="bs-component text-center">
                        <button class="btn btn-outline-primary" onclick="getBarrierWiseAmountTotal()" type="button">Total</button>
                        <button class="btn btn-outline-secondary" onclick="getBarrierWiseAmountMonthly()" type="button">Monthly</button>
                        <button class="btn btn-outline-success" onclick="getBarrierWiseAmountDaily()" type="button">Daily</button>
                     </p>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-md-6">
            <div class="tile" style="height:100%; width:100%;">
               <h3 class="tile-title">Apple Boxes Report Barrier Wise</h3>
                   <div>
                      <canvas id="appleCollection"></canvas>
                   </div>

                   <div class="row">
                     <div class="col-lg-12">
                        <p class="bs-component text-center">
                           <button class="btn btn-outline-primary" onclick="getBarrierWiseBoxesTotal();" type="button">Total</button>
                           <button class="btn btn-outline-secondary" onclick="getBarrierWiseBoxesMonthly();" type="button">Monthly</button>
                           <button class="btn btn-outline-success" onclick="getBarrierWiseBoxesDaily();" type="button">Daily</button>
                        </p>
                     </div>
                   </div>
            </div>
         </div>
      </div>
      </sec:authorize>
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
					label: 'Barrier Wise ID Card Generation ',
					data: data,
					backgroundColor: [
					      'rgb(54, 162, 235)',
                          'rgb(255, 99, 132)',
                          'rgb(255, 205, 86)',
                          'rgb(54, 162, 235)',
                          'rgb(255, 99, 132)'
                        ],
                        hoverOffset: 4
				}]

			}
		})
	}

	function setChartVehicleType(data) {
    		var obj = JSON.constructor(JSON.parse(data));
    		console.log(obj);

    		var labels = obj.members.map(e => e.vehicleType);
    		console.log(labels);
    		var data = obj.members.map(e => e.count);
    		console.log(data);

    		Chart.defaults.global.defaultFontColor = '#75787c';
    		// ------------------------------------------------------- //
    		// Pie Chart Custom 2
    		// ------------------------------------------------------ //
    		var $chart = $('#pieChartVehicleType');
    		var pieChartHome = new Chart($chart[0].getContext("2d"), {
    			type: 'pie',
    			data: {
    				labels: labels,
    				datasets: [{
    					label: 'Vehicle Type',
    					data: data,
    					backgroundColor: [
                              'rgb(54, 162, 235)',
                              'rgb(255, 205, 86)'
                            ],
                            hoverOffset: 4
    				}]

    			}
    		})
    	}


    	function setChartBarrierCollection(data) {
            		var obj = JSON.constructor(JSON.parse(data));
            		console.log(obj);

            		var labels = obj.members.map(e => e.barrierName);
            		console.log(labels);
            		var data = obj.members.map(e => e.amount);
            		console.log(data);

            		Chart.defaults.global.defaultFontColor = '#75787c';
            		// ------------------------------------------------------- //
            		// Pie Chart Custom 2
            		// ------------------------------------------------------ //
            		var $chart = $('#barrierCollection');
            		var pieChartHome = new Chart($chart[0].getContext("2d"), {
            			type: 'bar',
            			data: {
            				labels: labels,
            				datasets: [{
            					label: 'Barrier Name',
            					data: data,
            					backgroundColor: [
                                      'rgb(54, 162, 235)',
                                      'rgb(255, 205, 86)',
                                      'rgb(255, 205, 86)',
                                      'rgb(255, 205, 86)',
                                      'rgb(255, 205, 86)'
                                    ],
                                    hoverOffset: 4
            				}]

            			}
            		})
            	}


function setChartBarrierBoxesCollection(data) {
            		var obj = JSON.constructor(JSON.parse(data));
            		console.log(obj);

            		var labels = obj.members.map(e => e.barrierName);
            		console.log(labels);
            		var data = obj.members.map(e => e.count);
            		console.log(data);

            		Chart.defaults.global.defaultFontColor = '#75787c';
            		// ------------------------------------------------------- //
            		// Pie Chart Custom 2
            		// ------------------------------------------------------ //
            		var $chart = $('#appleCollection');
            		var pieChartHome = new Chart($chart[0].getContext("2d"), {
            			type: 'bar',
            			data: {
            				labels: labels,
            				datasets: [{
            					label: 'Barrier Name',
            					data: data,
            					backgroundColor: [
                                      'rgb(54, 162, 235)',
                                      'rgb(255, 205, 86)',
                                      'rgb(255, 205, 86)',
                                      'rgb(255, 205, 86)',
                                      'rgb(255, 205, 86)'
                                    ],
                                    hoverOffset: 4
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
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChart(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChart(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}


function getTotalCardsMonthly() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getTotalIDCardsMonth",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChart(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChart(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getTotalCardsDaily() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getTotalIDCardsDay",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChart(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChart(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

/* Get Vehicle Type*/

function getTotalVehicleType() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getTotalVehicleType",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartVehicleType(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartVehicleType(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getMonthlyVehicleType() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getMonthlyVehicleType",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartVehicleType(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartVehicleType(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getDailyVehicleType() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getDailyVehicleType",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartVehicleType(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartVehicleType(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

// Barrier Wise Collection of Amount Report

function getBarrierWiseAmountTotal() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBarrierWiseAmountTotal",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartBarrierCollection(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartBarrierCollection(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getBarrierWiseAmountMonthly() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBarrierWiseAmountMonthly",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartBarrierCollection(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartBarrierCollection(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getBarrierWiseAmountDaily() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBarrierWiseAmountDaily",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartBarrierCollection(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartBarrierCollection(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

// Barrier Wise Collection of Boxes Report

function getBarrierWiseBoxesTotal() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBarrierWiseBoxesTotal",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartBarrierBoxesCollection(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartBarrierBoxesCollection(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getBarrierWiseBoxesMonthly() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBarrierWiseBoxesMonthly",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartBarrierBoxesCollection(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartBarrierBoxesCollection(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getBarrierWiseBoxesDaily() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBarrierWiseBoxesDaily",
		success: function(data) {
			//Tomcat
			//var json_ = JSON.parse(JSON.stringify(data));
			//setChartBarrierBoxesCollection(json_.RESPONSE);

			//Jboss
			var json_ = JSON.parse(data);
			var obj = JSON.constructor(json_);
			console.log(obj);
			console.log(obj.RESPONSE)
			setChartBarrierBoxesCollection(obj.RESPONSE);


		},
		error: function(data) {
			console.log(data)
		}

	});
}


getTotalCards();
getTotalVehicleType();
getBarrierWiseAmountTotal();
getBarrierWiseBoxesTotal();

</script>