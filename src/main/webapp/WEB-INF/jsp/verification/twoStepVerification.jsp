<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap441.js"></script>
<body>
   <!--  <img src="${pageContext.request.contextPath}/resources/images/login/1200.png" class="avatar1" > -->
   <!--  <img src="${pageContext.request.contextPath}/resources/images/login/images/1111.png" class="avatar2" > -->
   <main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
      <div class="container">
         <div class="card login-card">
            <div class="row no-gutters">
               <div class="col-md-5">
                  <img src="${pageContext.request.contextPath}/resources/images/login/new.jpg" alt="login" class="login-card-img">
               </div>
               <div class="col-md-7">
                  <div class="card-body">
                     <div class="brand-wrapper">
                        <img src="${pageContext.request.contextPath}/resources/images/login/1q.jpg" class="avatar">
                     </div>
                     <p class="login-card-description" align="center">
                        <spring:message code="loginpage.heading" text="Two Step Verification"/>
                     </p>
                      <form:form method="POST" modelAttribute="CashCollectionFormSave" action="${pageContext.request.contextPath}/verifyLogin" class="form-signin">
                              <br>
                              <c:if test="${not empty serverError}">
                                 <div id="serverError" style="color:red;" class="plErroMessage">${serverError}</div>
                              </c:if>
                        <div class="form-group">
                           <p class="control-label" >Dear ${UserData.firstName} ${UserData.lastName}, please enter the code we have sent to phone number ${UserData.mobileNumber} </p>
                        </div>
                        <div class="form-group mb-7">
                           <spring:bind path="enterotp">
                              <div class="col-md-12 form-group  ${status.error ? 'has-error' : ''}">
                                 <form:input required="required" class="form-control col-lg-12" path="enterotp" placeholder="Enter OTP" type="text" autocomplete="off"  oncopy="return false" onpaste="return false" name="enterotp"  maxLength="6"  size="6" id="enterotp" ></form:input>
                                 <form:errors path="enterotp"></form:errors>
                              </div>
                           </spring:bind>
                        </div>
                        <p> Didnt receive the code? <a href="#" style="text-decoration:none" onclick="getOtp();";>Resend code by SMS </a></p>
                         <p id="otpmessage" style="color:red;"></p>
                        <input class="btn btn-block btn btn-block login-btn mb-4" type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-block btn btn-block login-btn mb-4"  name="submit" class="btn btn-primary" style="background-color: #324F17 !important;" >Back</a>
                        <p>Note: If you don't have access to your phone or a previously recognized device, please contact Admin</p>
                        <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                     </form:form>
                      <input class="form-control col-md-6"  id="uid" type="hidden" value="${UserData.userId}"  />
                       <input class="form-control col-md-6"  id="mn" type="hidden" value="${UserData.mobileNumber}"  />
                  </div>
               </div>
            </div>
         </div>
      </div>
   </main>
</body>

<script>
function getOtp(){
// alert("HI");

 var uid = document.getElementById("uid").value;
 var mn = document.getElementById("mn").value;


 //alert("uid"+ uid);
 //alert("mn"+ mn);

sendOtp_TwoStep(uid,mn);



}
</script>