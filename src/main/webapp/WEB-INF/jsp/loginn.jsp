<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/bootstrap441.js"></script>

<body>




    <main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
        <div class="container">
            <div class="card login-card">
                <div class="row no-gutters">
                    <div class="col-md-5">
                        <img src="${pageContext.request.contextPath}/resources/images/login/new.jpg" alt="login" class="login-card-img">

 <div class="container">
  <!-- <img src="${pageContext.request.contextPath}/resources/images/login/1200.png" style="margin-left:450px; height:80px; width:100px;" class="avatar1" >  -->
    <img src="${pageContext.request.contextPath}/resources/images/login/himsabe.png" style="margin-left:-500px; margin-top:-30px; height:150px; width:150px;" class="avatar2" >
</div>
                    </div>
                    <div class="col-md-7">
                        <div class="card-body">
                            <div class="brand-wrapper">
                                <img src="${pageContext.request.contextPath}/resources/images/login/1q.jpg" class="avatar">

                            </div>
                            <p class="login-card-description" align="center"><spring:message code="loginpage.heading" text="Login"/></p>
                             <form  method="post" class="loginForm"  action="${pageContext.request.contextPath}/login">

                                <div class="form-group">

                                     <label class="control-label" for="username" ><spring:message code="loginpage.username" text="Enter Username"/></label><br>
                                     <input class="form-control" type="text" oncopy="return false" autocomplete="off" onfocus="this.removeAttribute('readonly');" onpaste="return false" maxlength="20" onkeypress="return alpha(event)"  required autofocus name="username" id="username">


                                </div>
                                <div class="form-group mb-7">


                                     <label class="control-label" for="password" ><spring:message code="loginpage.password" text="Enter Password"/></label><br>
                                     <input class="form-control" type="password" autocomplete="off" onfocus="this.removeAttribute('readonly');" oncopy="return false" onpaste="return false" name="password"  maxLength="20"  size="20" id="password">

                                </div>
                                 <!-- Captcha -->
                                            <div class="form-group col-lg-12">
                                               <img id="captcha_id" class="col-lg-9" name="imgCaptcha" src="captcha.jpg">
                                               <a href="javascript:;" class="col-lg-2" title="change captcha text"  onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false">
                                             <!-- <i class="fa fa-refresh"></i> -->
                                             <img src="${pageContext.request.contextPath}/resources/images/refresh.png" style="width:30px; height:30px;">
                                               </a>
                                            </div>
                                            <div class="col-lg-12"><input autocomplete="off" onfocus="this.removeAttribute('readonly');" class="form-control" maxlength="5" required onkeypress="return alpha(event)" oncopy="return false" onpaste="return false"  id="captcha" name="captcha" /></div>
                                            <br>
                                            <!-- Captcha Ends -->

                                 <input class="btn btn-block btn btn-block login-btn mb-4" type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                                <div class="form-group">
                                               <c:if test="${error != null}">
                                                  <div id="serverError" class="alert alert-warning alert-dismissable fade show plErroMessage">
                                                     <button class="close" data-dismiss="alert" aria-label="Close">×</button>
                                                     <strong>Warning!</strong> ${error}
                                                  </div>
                                               </c:if>
                                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </main>
</body>
