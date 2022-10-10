<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.spring-friendly.js"></script>
<style>
   .app-content {
   background-color: #FFFFFF;
   }
</style>
<main class="app-content">
<!-- Modal -->
	<div class="modal fade" id="empModal" role="dialog">
		<div class="modal-dialog  modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Cash Transfer Request Details</h4>
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



   <div class="row" style="box-shadow: 0 0 8px 2px #888; padding:10px;">
      <div class="container">
      <h2 class="form-signin-heading">View Cash Transfer Requests</h2>
         <table id="sampleTable" class="table table-striped table-bordered">
            <thead>
               <tr>
                  <th>Transfer Id</th>
                  <th>Vehicle Number </th>
                  <th>From Date </th>
                  <th>Thru Date </th>
                  <th> Download </th>
                   <th> Request Created On </th>
                  <th> Action</th>
               </tr>
            </thead>
         </table>
      </div>
   </div>
</main>


<script>
	$('table#sampleTable').DataTable({
		ajax: '/apidataTable/viewTransferRequests',
		serverSide: true,
		order: [[ 5, "desc" ]],
		columns: [

			{
				data: 'transferRequestID'
			},
			{
				data: 'vehicleNumber'
			},
			{   data: 'fromDate'
			},
			{
			    data: 'thrueDate'
			},
			{
				data: 'transferRequestID',
				render: function (data) {

						return '<div="btn-group"><a style="text-decoration:none;" class=" center icon fa fa-qrcode fa-3x" href="${pageContext.request.contextPath}/updateSoSdpo/${"' + data + '"}";></a></div>'

				}
			},
			{
                                    	data: 'createdDate',
                                    	render: function (data) {
                                            const d = new Date( data );
                                            date = d.getHours() + ":" + d.getMinutes() + ", " + d.toDateString();
                                                return '<div class="button-group btn btn-warning" style="width:100%">' + date + '</div>'
                                        }
                                    },


			{
				data: 'transferRequestID',
				render: function (data) {
					return '<div class="btn-group"><a style="text-decoration:none; color:#FFFFFF;" onclick="getData(' + data + ')" class="btn btn-success" ;>View Details</a></div>'
				}


			}
		]
	});

</script>