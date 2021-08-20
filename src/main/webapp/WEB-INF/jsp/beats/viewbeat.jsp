<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.spring-friendly.js"></script>
<main class="app-content">
   <h2 class="form-signin-heading">Beat Master</h2>

   <div class="md-12" style="background-color:#FFFFFF">
           <table id="sampleTable" class="table">
               <thead>
                   <tr>
                     <th>Beat Name</th>
                     <th>Beat Id</th>
                     <th> Active/Inactive</th>
                     <th> Created Date</th>
                     <th> Action</th>
                   </tr>
               </thead>
           </table>
       </div>

</main>

<script>
   $('table#sampleTable').DataTable({
       ajax: '/apidataTable/allbeats',
       serverSide: true,
       columns: [
           {
               data: 'beatName'
           },
           {
               data: 'beatId'
           },
           {
               data: 'active'
           },
           {
                          data: 'createdDate'
            },
            {
                   data: 'beatId',
                   render: function (data) { return '<div class="btn-group"><a href="${pageContext.request.contextPath}/updateBeat/${"'+data+'"}" class="btn btn-success" ;>Update</a></div>' }


             }
       ]
   });

    $(".dataTables_wrapper").css("width","100%");
</script>