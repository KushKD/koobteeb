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
   <div class="row" style="box-shadow: 0 0 8px 2px #888; padding:10px;">
      <div class="container">
      <h2 class="form-signin-heading">Officer Login Logs</h2>
         <table id="sampleTable" class="table table-striped table-bordered">
            <thead>
               <tr>
                  <th>Id</th>
                  <th>User Name</th>
                  <th>Mobile Number</th>
                  <th>Beat Name</th>
                  <th>Police Station Name</th>
                  <th>Date</th>

               </tr>
            </thead>
         </table>
      </div>
   </div>
</main>


<script>

	$('table#sampleTable').DataTable({
		ajax: '/apidataTable/allLogs',
		serverSide: true,
		columns: [

			{
				data: 'logsId'
			},
			{
				data: 'userId.username'
			},
            {
				data: 'userId.mobileNumber'
			},
			{
				data: 'userId.beatId.beatName'
			},
			{
				data: 'userId.psId.psName',
			},
            {
            	data: 'createdDate',
            	render: function (data) {
                    const d = new Date( data );
                    date = d.getHours() + ":" + d.getMinutes() + ", " + d.toDateString();

                        return '<div class="button-group btn btn-success" style="width:100%">' + date + '</div>'

                }
            }

		]
	});

</script>