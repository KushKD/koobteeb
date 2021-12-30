<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>


<style>
.app-content {
 background-color: #FFFFFF;
}
</style>


<main class="app-content">

<h2 class="form-signin-heading">Districts Master</h2>


		<div class="row">
			<div class="container">
                    <table id="districts" class="table">
                        <thead>
                            <tr>
                                <th>District ID</th>
                                <th>District Name</th>
                                <th>State Name</th>
                                <th>Active</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                    </table>
                </div>
				</div>

		</main>



<script type="text/javascript">

           $('table#districts').DataTable({
               ajax: {
                   contentType: 'application/json',
                   url: '/api/districts',
                   type: 'POST',
                   data: function (d) {
                       return JSON.stringify(d);
                   }
               },
               serverSide: true,
               columns: [
                   {
                       data: 'districtId'
                   },
                   {
                       data: 'districtName'
                   },
                   {
                       data: 'stateEntity.stateName',
                   },
                   {
                       data: 'active',
                      render: function (data) {
                      console.log(data);
                      if(data == true){
                      return '<div class="btn-group btn btn-success ">'+data+'</div>'
                      }else{
                       return '<div class="btn-group btn btn-danger ">'+data+'</div>'
                      }

                      }
                   },{
                        data: 'districtId',
                        render: function (data) { return '<div class="btn-group"><a href="${pageContext.request.contextPath}/updateDistrict/${"'+data+'"}" class="btn btn-success" ;>Update</a></div>' }
                     }
               ]
           });




   </script>