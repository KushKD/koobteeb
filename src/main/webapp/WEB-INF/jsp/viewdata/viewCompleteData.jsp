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
      <h2 class="form-signin-heading">All Entries</h2>
         <table id="sampleTable" class="table table-striped table-bordered">
            <thead>
               <tr>
                  <th> Id</th>
                  <th>Name</th>
                  <th>Sub Module Name</th>
                  <th>Beat Name</th>
                  <th>Police Station Name</th>
                  <th>Uploaded By</th>
                  <th> Data Creation Date</th>
                  <th> Action</th>
               </tr>
            </thead>
         </table>
      </div>
   </div>
</main>


<script>
	$('table#sampleTable').DataTable({
		ajax: '/apidataTable/allInformation',
		serverSide: true,
		order: [[ 6, "desc" ]],
		columns: [

			{
				data: 'id'
			},
			{
				data: 'name'
			},
			{
				data: 'submoduleId.submoduleName'
			},
			{
				data: 'beatId.beatName'
			},
			{
				data: 'psId.psName'
			},
			{
            	data: 'userId.username'
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
				data: 'id',
				render: function (data) {
					return '<div class="btn-group"><a href="${pageContext.request.contextPath}/updateInformation/${"' + data + '"}" class="btn btn-primary" ;>Update</a></div>'
				}


			}
		]
	});

</script>