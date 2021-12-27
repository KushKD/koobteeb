<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
  <head>

   <title>Dashboard</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
  <body oncontextmenu="return false" class="app sidebar-mini">
    <!-- Navbar-->
    <header class="app-header">

      <ul class="app-nav">
        <li style="display:none;" class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Show notifications"><i class="fa fa-bell-o fa-lg"></i></a>
          <ul class="app-notification dropdown-menu dropdown-menu-right">


          </ul>
        </li>
        <!-- User Menu-->
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
          <ul class="dropdown-menu settings-menu dropdown-menu-right">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
          </ul>
        </li>
      </ul>
    </header>
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="${pageContext.request.contextPath}/resources/images/user.png" alt="User Image">
        <div>
          <p class="app-sidebar__user-name">${pageContext.request.userPrincipal.name}</p>
        <!-- <p class="app-sidebar__user-designation">Mobile Number</p> -->
        </div>
      </div>
      <ul class="app-menu">
        <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/dashboard"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Dashboard</span></a></li>


        <sec:authorize access="hasAuthority('SUPER ADMIN')">
                <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">States Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                  <ul class="treeview-menu">
                  <li><a class="treeview-item" href="${pageContext.request.contextPath}/createState" ><i class="icon fa fa-circle-o"></i> Create State</a></li>
                    <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewStates"><i class="icon fa fa-circle-o"></i> Update State</a></li>
                  </ul>
                </li>
        </sec:authorize>

         <sec:authorize access="hasAuthority('SUPER ADMIN')">
                        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Districts Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                          <ul class="treeview-menu">
                          <li><a class="treeview-item" href="${pageContext.request.contextPath}/createDistrict" ><i class="icon fa fa-circle-o"></i> Create District</a></li>
                            <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewDistrict"><i class="icon fa fa-circle-o"></i> Update District</a></li>
                          </ul>
                        </li>
                </sec:authorize>

                <sec:authorize access="hasAuthority('SUPER ADMIN')">
                                <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Police Lines</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                                  <ul class="treeview-menu">
                                  <li><a class="treeview-item" href="${pageContext.request.contextPath}/createsoSdpo" ><i class="icon fa fa-circle-o"></i> Create Police Line</a></li>
                                    <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewsoSdpo"><i class="icon fa fa-circle-o"></i> Update Police Line</a></li>
                                  </ul>
                                </li>
                        </sec:authorize>



 <sec:authorize access="hasAuthority('SUPER ADMIN')">
                                <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Police Station Masters</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                                  <ul class="treeview-menu">
                                  <li><a class="treeview-item" href="${pageContext.request.contextPath}/createps" ><i class="icon fa fa-circle-o"></i> Create Police Station</a></li>
                                    <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewps"><i class="icon fa fa-circle-o"></i> Update Police Station</a></li>
                                  </ul>
                                </li>
                        </sec:authorize>

   <sec:authorize access="hasAuthority('SUPER ADMIN')">
                      <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Store Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                        <ul class="treeview-menu">
                        <li><a class="treeview-item" href="${pageContext.request.contextPath}/createCategory" ><i class="icon fa fa-circle-o"></i> Create Store</a></li>
                          <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewCategory"><i class="icon fa fa-circle-o"></i> Update Store</a></li>
                        </ul>
                      </li>
                          </sec:authorize>

   <sec:authorize access="hasAuthority('SUPER ADMIN')">
          <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label"> Units Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
            <li><a class="treeview-item" href="${pageContext.request.contextPath}/createUnit" ><i class="icon fa fa-circle-o"></i> Create Unit</a></li>
              <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewUnits"><i class="icon fa fa-circle-o"></i> Update Unit</a></li>
            </ul>
          </li>
   </sec:authorize>



   <sec:authorize access="hasAuthority('SUPER ADMIN')">
                      <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label"> Item Category</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                        <ul class="treeview-menu">
                        <li><a class="treeview-item" href="${pageContext.request.contextPath}/createItemCategory" ><i class="icon fa fa-circle-o"></i> Create Item Category</a></li>
                          <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewItemCategory"><i class="icon fa fa-circle-o"></i> Update Item Category</a></li>
                        </ul>
                      </li>
                          </sec:authorize>



    <sec:authorize access="hasAuthority('SUPER ADMIN')">
                         <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label"> Add Item</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                           <ul class="treeview-menu">
                           <li><a class="treeview-item" href="${pageContext.request.contextPath}/createbeat" ><i class="icon fa fa-circle-o"></i> Add Item</a></li>
                             <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewbeat"><i class="icon fa fa-circle-o"></i> Update Item</a></li>
                           </ul>
                         </li>
                             </sec:authorize>


        <sec:authorize access="hasAuthority('SUPER ADMIN')">
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">User Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
          <li><a class="treeview-item" href="${pageContext.request.contextPath}/createRole" ><i class="icon fa fa-circle-o"></i> Create Role</a></li>
          <li><a class="treeview-item" href="${pageContext.request.contextPath}/createUser"><i class="icon fa fa-circle-o"></i> Create User</a></li>
           <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewUsers"><i class="icon fa fa-circle-o"></i> Update Users</a></li>
          </ul>
        </li>
        </sec:authorize>


      </ul>
    </aside>





  </body>
</html>