<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/charts.js"></script>
<main class="app-content">
   <div class="app-title">
      <div>
         <h1><i class="fa fa-dashboard"></i> Dashboard</h1>
         <p> Police Inventory </p>
      </div>
      <ul class="app-breadcrumb breadcrumb">
         <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
         <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
      </ul>
   </div>

   </div>

 <div class="row">
    <sec:authorize access="hasAnyAuthority('SUPER ADMIN','ADMIN')">
      <div class="col-md-6 col-lg-3">
         <div class="widget-small primary coloured-icon">
            <i class="icon fa fa-users fa-3x"></i>
            <div class="info">
               <a href="${pageContext.request.contextPath}/viewUsers" style=" text-decoration: none !important;">
                  <h4>View Users</h4>
               </a>
            </div>
         </div>
      </div>
      </sec:authorize>


      <sec:authorize access="hasAnyAuthority('SUPER ADMIN','ADMIN')">
         <div class="col-md-6 col-lg-3">
            <div class="widget-small warning coloured-icon">
               <i class="icon fa fa-users fa-3x"></i>
               <div class="info">
                  <a href="${pageContext.request.contextPath}/viewps" style=" text-decoration: none !important;">
                     <h4>View Police Station</h4>
                  </a>
               </div>
            </div>
         </div>
      </sec:authorize>

      <sec:authorize access="hasAnyAuthority('SUPER ADMIN','ADMIN')">
         <div class="col-md-6 col-lg-3">
            <div class="widget-small danger coloured-icon">
               <i class="icon fa fa-users fa-3x"></i>
               <div class="info">
                  <a href="${pageContext.request.contextPath}/createmodulerolemapping" style=" text-decoration: none !important;">
                     <h4>User Module Mapping</h4>
                  </a>
               </div>
            </div>
         </div>
      </sec:authorize>


   </div>
   </div>

</main>

