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
            <li style="display:none;" class="dropdown">
               <a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Show notifications"><i class="fa fa-bell-o fa-lg"></i></a>
               <ul class="app-notification dropdown-menu dropdown-menu-right">
               </ul>
            </li>
            <!-- User Menu-->
            <li class="dropdown">
               <a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class="fa fa-user fa-lg"></i></a>
               <ul class="dropdown-menu settings-menu dropdown-menu-right">
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-lg"></i> Logout</a></li>
               </ul>
            </li>
         </ul>
      </header>
      <!-- Sidebar menu-->
      <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
      <aside class="app-sidebar">
         <div class="app-sidebar__user">
            <img class="app-sidebar__user-avatar" src="${pageContext.request.contextPath}/resources/images/user.png" alt="User Image">
            <div>
               <p class="app-sidebar__user-name">${pageContext.request.userPrincipal.name}</p>
               <!-- <p class="app-sidebar__user-designation">Mobile Number</p> -->
            </div>
         </div>
         <ul class="app-menu">
            <li><a class="app-menu__item active" href="${pageContext.request.contextPath}/dashboard"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Dashboard</span></a></li>
            <sec:authorize access="hasAuthority('SUPER ADMIN')">
               <li class="treeview">
                  <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">States Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                  <ul class="treeview-menu">
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/createState" ><i class="icon fa fa-circle-o"></i> Create State</a></li>
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewStates"><i class="icon fa fa-circle-o"></i> Update State</a></li>
                  </ul>
               </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('SUPER ADMIN')">
               <li class="treeview">
                  <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Districts Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                  <ul class="treeview-menu">
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/createDistrict" ><i class="icon fa fa-circle-o"></i> Create District</a></li>
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewDistrict"><i class="icon fa fa-circle-o"></i> Update District</a></li>
                  </ul>
               </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('SUPER ADMIN')">
                           <li class="treeview">
                              <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Bank Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                              <ul class="treeview-menu">
                                 <li><a class="treeview-item" href="${pageContext.request.contextPath}/createsoSdpo" ><i class="icon fa fa-circle-o"></i> Create Bank</a></li>
                                 <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewsoSdpo"><i class="icon fa fa-circle-o"></i> Update Bank</a></li>
                              </ul>
                           </li>
                        </sec:authorize>
             <sec:authorize access="hasAuthority('SUPER ADMIN')">
                           <li class="treeview">
                              <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Role Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                              <ul class="treeview-menu">
                                 <li><a class="treeview-item" href="${pageContext.request.contextPath}/createRole" ><i class="icon fa fa-circle-o"></i> Create Role</a></li>
                                 <li><a class="treeview-item" href="${pageContext.request.contextPath}/getRoles"><i class="icon fa fa-circle-o"></i> Update Role</a></li>
                              </ul>
                           </li>
                        </sec:authorize>
              <sec:authorize access="hasAuthority('SUPER ADMIN')">
                            <li class="treeview">
                               <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">User Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                               <ul class="treeview-menu">

                                  <li><a class="treeview-item" href="${pageContext.request.contextPath}/createUser"><i class="icon fa fa-circle-o"></i> Create User</a></li>
                                  <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewUsers"><i class="icon fa fa-circle-o"></i> Update Users</a></li>
                               </ul>
                            </li>
                         </sec:authorize>

            <sec:authorize access="hasAuthority('SUPER ADMIN')">
               <li class="treeview">
                  <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Module Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                  <ul class="treeview-menu">
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/createmodule" ><i class="icon fa fa-circle-o"></i> Create Module</a></li>
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewmodule"><i class="icon fa fa-circle-o"></i> View Modules </a></li>
                  </ul>
               </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('SUPER ADMIN')">
               <li class="treeview">
                  <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Module Role Master</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                  <ul class="treeview-menu">
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/createmodulerolemapping"><i class="icon fa fa-circle-o"></i> Create Module Role Mapping </a></li>
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewmodulerolemapping"><i class="icon fa fa-circle-o"></i> View Module Role Mapping </a></li>
                  </ul>
               </li>
            </sec:authorize>

            <sec:authorize access="hasAuthority('SUPER ADMIN')">
               <li class="treeview">
                  <a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">Pin Management</span><i class="treeview-indicator fa fa-angle-right"></i></a>
                  <ul class="treeview-menu">
                     <li><a class="treeview-item" href="${pageContext.request.contextPath}/viewpin"><i class="icon fa fa-circle-o"></i> View Pin</a></li>
                  </ul>
               </li>
            </sec:authorize>

         </ul>
      </aside>
   </body>
</html>