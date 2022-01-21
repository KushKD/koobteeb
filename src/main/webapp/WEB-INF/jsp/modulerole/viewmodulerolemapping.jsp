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
      <h2 class="form-signin-heading">Module Role Mapping</h2>
         <table id="sampleTable" class="table table-striped table-bordered">
            <thead>
               <tr>
                  <th>Id</th>
                  <th>Module Name</th>
                  <th>Role Name</th>
                  <th> Active/Inactive</th>
                  <th> Action</th>
               </tr>
            </thead>
         </table>
      </div>
   </div>
</main>


<script>


	$('table#sampleTable').DataTable({
		ajax: formURL +'/apidataTable/allmodulerole',
		serverSide: true,
		columns: [

			{
				data: 'id'
			},
			{
				data: 'moduleId.moduleName'
			},

			{
				data: 'roleId.roleName'
			},


			{
				data: 'active',
				render: function (data) {
					console.log(data);
					if (data == true) {
						return '<div class="button-group btn btn-success" style="width:100%">' + data + '</div>'
					} else {
						return '<div class="button-group btn btn-danger" style="width:100%">' + data + '</div>'
					}
				}
			},


			{
				data: 'id',
				render: function (data) {
					return '<div class="btn-group"><a href="${pageContext.request.contextPath}/updatemodulerolemapping/${"' + data + '"}" class="btn btn-primary" ;>Update</a></div>'
				}


			}
		]
	});

</script>