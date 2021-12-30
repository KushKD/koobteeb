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

<h2 class="form-signin-heading">View Items in Police Line </h2>

<br>
		<div class="row">
			<div class="container">
                    <table id="stockin" class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Stock In ID</th>
                                <th>Police Line Name</th>
                                <th>Item Name</th>
                                <th>Quantity</th>
                                <th>Units</th>
                                <th>Stock Entered Date</th>
                                <th>View Details</th>
                            </tr>
                        </thead>
                    </table>
                </div>
				</div>

		</main>



<script type="text/javascript">

           $('table#stockin').DataTable({
               ajax: {
                   contentType: 'application/json',
                   url: '/api/stokin',
                   type: 'POST',
                   data: function (d) {
                       return JSON.stringify(d);
                   }
               },
               serverSide: true,
               columns: [
                   {
                       data: 'stockId'
                   },
                   {
                       data: 'policeLines.policelineName'
                   },
                   {
                       data: 'items.itemsName',
                   },{
                                            data: 'quantity'
                                        },
                   {
                       data: 'items.units.unitName'

                   },{
                        data: 'createdDate',
                        render: function(data){
                        var d = new Date(data).toString();
                        var index = d.lastIndexOf(':') +3
                        console.log(d.substring(0, index))
                        return d.substring(0, index);}
                          },
                   {
                                           data: 'stockId',
                                           render: function (data) { return '<div class="btn-group"><a href="${pageContext.request.contextPath}/updateDistrict/${"'+data+'"}" class="btn btn-success" ;>View Details</a></div>' }
                                        }
               ]
           });




   </script>