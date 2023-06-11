<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>

<main class="app-content">
   <form:form method="POST" modelAttribute="updateUser" action="${pageContext.request.contextPath}/updateSelectedUser" class="form-signin">
      <h2 class="form-signin-heading">Update User</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>

<spring:bind path="userId">
         <div class="form-group ${status.error ? 'has-error' : ''}" >
            <form:input type="text" path="userId" readonly="true" value="${usersDetails.user_id}" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false"></form:input>
            <form:errors path="userId"></form:errors>
         </div>
      </spring:bind>

       <spring:bind path="firstName">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" path="firstName" class="form-control" autocomplete="off" value="${usersDetails.first_name}"  oncopy="return false" onpaste="return false" placeholder="First Name"
                     autofocus="true"></form:input>
                  <form:errors  path="firstName"></form:errors>
               </div>
            </spring:bind>

             <spring:bind path="lastName">
                     <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="lastName" class="form-control" value="${usersDetails.last_name}"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Last Name"
                           autofocus="true"></form:input>
                        <form:errors  path="lastName"></form:errors>
                     </div>
                  </spring:bind>

      <spring:bind path="username">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="username" class="form-control" autocomplete="off" value="${usersDetails.username}"  oncopy="return false" onpaste="return false" placeholder="Username"
               autofocus="true"></form:input>
            <form:errors  path="username"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="mobileNumber">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:input type="text"  required="required" path="mobileNumber" maxlength="10" autocomplete="off" value="${usersDetails.mobile_number}"  oncopy="return false" onpaste="return false" minlength="10"  class="form-control"  name="mobileNumber" placeholder="Mobile Number" ></form:input>
            <form:errors  path="mobileNumber"></form:errors>
         </div>
      </spring:bind>

      <spring:bind path="rank">
               <div class="form-group  ${status.error ? 'has-error' : ''}">
                  <form:input type="text"  required="required" path="rank"  value="${usersDetails.rank}"  oncopy="return false" onpaste="return false" minlength="10"  class="form-control"  name="rank" placeholder="Rank" ></form:input>
                  <form:errors  path="rank"></form:errors>
               </div>
            </spring:bind>

       <spring:bind path="stateId">
               <div class="form-group  ${status.error ? 'has-error' : ''}">
                  <form:label path="stateId" for="roles">Select State</form:label>
                  <form:select path="stateId" name="stateId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="states" onchange="getDistrictsViaStates(this.value)"></form:select>
                  <form:errors path="stateId"></form:errors>
               </div>
            </spring:bind>
            <spring:bind path="districtId">
               <div class="form-group  ${status.error ? 'has-error' : ''}">
                  <form:label path="districtId" for="roles">Select District</form:label>
                  <form:select path="districtId" name="stateId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="districts" onchange="getBarriers(this.value)" ></form:select>
                  <form:errors path="districtId"></form:errors>
               </div>
            </spring:bind>

        <spring:bind path="sosdpoId">
                           <div class="form-group  ${status.error ? 'has-error' : ''}">
                              <form:label path="sosdpoId" for="roles">Select SO /SDPO </form:label>
                              <form:select path="sosdpoId" name="sosdpoId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="sosdpoId" onchange="getPoliceStationsViasosdpoid(this.value)"></form:select>
                              <form:errors path="sosdpoId"></form:errors>
                           </div>
                        </spring:bind>
                        <spring:bind path="psId">
                                 <div class="form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="psId" for="poicestation">Select Police Station </form:label>
                                    <form:select path="psId" name="psId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="psId" onchange="getBeat(this.value);"></form:select>
                                    <form:errors path="psId"></form:errors>
                                 </div>
                              </spring:bind>

                     <spring:bind path="beatId">
                                 <div class="form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="beatId" for="roles">Select Beat</form:label>
                                    <form:select path="beatId" name="beatId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="beats" ></form:select>
                                    <form:errors path="beatId"></form:errors>
                                 </div>
                              </spring:bind>



      <spring:bind path="roleId">
         <div class="form-group  ${status.error ? 'has-error' : ''}">
            <form:label path="roleId" for="roles">Select Roles</form:label>
            <form:select path="roleId" name="roleId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="roles">
            </form:select>
            <form:errors  path="roleId"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="password">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="password" path="password" autocomplete="off"  oncopy="return false" maxLength="20"  size="20" onpaste="return false" class="form-control" placeholder="Password"></form:input>
            <form:errors path="password"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="passwordConfirm">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="password" path="passwordConfirm" autocomplete="off"  oncopy="return false" maxLength="20"  size="20" onpaste="return false" class="form-control"
               placeholder="Confirm your password"></form:input>
            <form:errors path="passwordConfirm"></form:errors>
         </div>
      </spring:bind>

       <spring:bind path="userIsActive">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:label path="userIsActive"> Is Active </form:label>
                  <form:select path="userIsActive" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" >
                     <form:option value=""> --Select-- </form:option>
                     <form:option value="T"> Yes </form:option>
                     <form:option value="F"> No </form:option>
                  </form:select>
                  <form:errors style="color:red;" path="userIsActive"></form:errors>
               </div>
            </spring:bind>

             <spring:bind path="oldroleid">
             <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                <form:input type="hidden" path="oldroleid" id="oldroleid" class="form-control"  value="${userRoleIdOld}" />
                <form:errors style="color:red;"  path="oldroleid"></form:errors>
             </div>
             </spring:bind>

     <div class="col-lg-12">
      <button class="btn btn-lg btn-primary col-lg-4" type="submit">Submit</button>
            <a href="${pageContext.request.contextPath}/viewUsers" class="btn btn-lg btn-danger col-lg-4">Go Back</a>
            <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
     </div>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
   <input class="form-control col-md-6"  id="did" type="hidden" value="${usersDetails.state_id}"  />
      <input class="form-control col-md-6"  id="sid" type="hidden" value="${usersDetails.district_id}"  />
      <input class="form-control col-md-6"  id="sosid" type="hidden" value="${usersDetails.sodpo_id}"  />
      <input class="form-control col-md-6"  id="psid" type="hidden" value="${usersDetails.ps_id}"  />
      <input class="form-control col-md-6"  id="rid" type="hidden" value="${usersDetails.role_id}"  />
      <input class="form-control col-md-6"  id="beatid" type="hidden" value="${usersDetails.beat_id}"  />

</main>

<script type="text/javascript">
   function getStatesOnLoad(){
             if(document.getElementById('did') != null && document.getElementById('did').value  != null ){
                     getStatesUpdate(document.getElementById('did').value);
                     }
                 }

  function getdistrictsOnLoad(){
               if(document.getElementById('sid') != null && document.getElementById('sid').value  != null ){
                       getdistrictsUpdate(document.getElementById('did').value);
                       }
                   }


   function getrolesOnLoad(){
                    if(document.getElementById('rid') != null && document.getElementById('rid').value  != null ){
                            getRolesUpdated(document.getElementById('rid').value);
                            }
                        }

    function getSOSDPOLoad(){
                     if(document.getElementById('sosid') != null && document.getElementById('sosid').value  != null ){
                             getSOSDPOUpdate();
                             }
                         }



      function getPoliceStationOnLoad(){
                     if(document.getElementById('psid') != null && document.getElementById('psid').value  != null ){
                             getPoliceStationsViasosdpoid(document.getElementById('sosid').value);
                             }
                         }

      function getBeatOnLoad(){
                           if(document.getElementById('beatid') != null && document.getElementById('beatid').value  != null ){
                                   getBeat(document.getElementById('psid').value);
                                   }
                               }
   $(document).ready(function() {
    getStatesOnLoad();
    getdistrictsOnLoad();
    getSOSDPOLoad();
    getPoliceStationOnLoad();
    getrolesOnLoad();
    getBeatOnLoad();
   });
</script>