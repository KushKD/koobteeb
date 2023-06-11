<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<main class="app-content">
   <form:form method="POST" id="form" modelAttribute="viewData" action="${pageContext.request.contextPath}/getInformation" class="form-signin">
      <h2 class="form-signin-heading">View Data</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <div class="row">
         <spring:bind path="stateId">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="stateId" for="roles">Select States</form:label>
               <form:select path="stateId" autocomplete="off"  oncopy="return false" onpaste="return false" name="stateId" class="form-control" id="states" onchange="getDistrictsViaStates(this.value)"></form:select>
               <form:errors path="stateId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="districtId">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="districtId" for="roles">Select District</form:label>
               <form:select path="districtId" autocomplete="off"  oncopy="return false" onpaste="return false" name="districtId" class="form-control" id="districts" ></form:select>
               <form:errors path="districtId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="sosdpoId">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="sosdpoId" for="roles">Select SO /SDPO </form:label>
               <form:select path="sosdpoId" name="sosdpoId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="sosdpoId" onchange="getPoliceStationsViasosdpoid(this.value)"></form:select>
               <form:errors path="sosdpoId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="psId">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="psId" for="poicestation">Select Police Station </form:label>
               <form:select path="psId" name="psId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="psId" onchange="getBeat(this.value);"></form:select>
               <form:errors path="psId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="beatId">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="beatId" for="roles">Select Beat</form:label>
               <form:select path="beatId" name="stateId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="beats" ></form:select>
               <form:errors path="beatId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="submoduleId">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="submoduleId" for="roles">Select Sub Module</form:label>
               <form:select path="submoduleId" name="submoduleId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="submodule" ></form:select>
               <form:errors path="submoduleId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="fromDate">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="fromDate" >From Date</form:label>
               <form:input class="form-control" path="fromDate" autocomplete="off"  oncopy="return false" onpaste="return false" id="fromDate" type="text" placeholder="From Date" />
               <form:errors  path="fromDate"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="toDate">
            <div class="col-md-3 form-group  ${status.error ? 'has-error' : ''}">
               <form:label path="toDate" >To Date</form:label>
               <form:input class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" path="toDate" id="toDate" type="text" placeholder="To Date" />
               <form:errors  path="toDate"></form:errors>
            </div>
         </spring:bind>
         <div class="col-lg-12">
         <button class="btn btn-lg btn-primary col-lg-5" type="submit">Submit</button>
          <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-lg btn-danger col-lg-5">Go Back</a>

         <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
         </div>

         <c:remove var="successMessage" scope="session" />
      </div>
   </form:form>
   <input class="form-control col-md-6"  id="did" type="hidden" value="${state_id}"  />
   <input class="form-control col-md-6"  id="sid" type="hidden" value="${district_id}"  />
   <input class="form-control col-md-6"  id="sosid" type="hidden" value="${sodpo_id}"  />
   <input class="form-control col-md-6"  id="psid" type="hidden" value="${ps_id}"  />
   <input class="form-control col-md-6"  id="beatid" type="hidden" value="${beat_id}"  />
   <input class="form-control col-md-6"  id="submoduleid" type="hidden" value="${submodule_id}"  />
   <c:if test="${not empty informationMarkerWebs}">
      <div class="row">
      <div class="col-md-12">
         <div class="tile">
            <div class="tile-body">
               <div class="table-responsive">
                  <table class="table table-hover table-bordered" id="sampleTable">
                     <thead>
                        <tr>
                           <th>S.No</th>
                           <th>Sub Module Name</th>
                           <th>Name</th>
                           <th>Creation Date</th>
                           <th>Action </th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${informationMarkerWebs}" var="inf" varStatus="loopCounter">
                           <tr>
                              <td>
                                 <c:out value="${loopCounter.count}"/>
                              </td>
                              <td>${inf.submoduleName}</td>
                              <td>${inf.name}</td>
                              <td>${inf.date}</td>
                              <td class="btn-success text-center">
                              <a href="${pageContext.request.contextPath}/updateInformation/${inf.id}" style="color:#FFFFFF; text-decoration:none;">Update</a>
                              </td>
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
               </div>
            </div>
            <div></div>
         </div>
   </c:if>
   </div>
</main>
<script type="text/javascript">
   function getStatesOnLoad(){
               if(document.getElementById('did') != null && document.getElementById('did').value  != null ){
                       getStatesUpdate(document.getElementById('did').value);
                       }else{
                                         getStates();
                                         }
                   }

    function getdistrictsOnLoad(){
                 if(document.getElementById('sid') != null && document.getElementById('sid').value  != null ){
                         getdistrictsUpdate(document.getElementById('did').value);
                         }
                     }



      function getSOSDPOLoad(){
                       if(document.getElementById('sosid') != null && document.getElementById('sosid').value  != null ){
                               getSOSDPOUpdate();
                               }else{
                                                         getSOSDPO();
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

   function getsubmoduleidLoad(){
                       if(document.getElementById('submoduleid') != null && document.getElementById('submoduleid').value  != null ){
                               getSubmoduleUpdate();
                               }else{
                                                         getSubmodule();
                                                         }
                           }



     $( document ).ready(function() {

      var date = new Date();
          var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());

     $('#fromDate').datepicker({
           	format: "dd-mm-yyyy",
           	autoclose: true,
           	todayHighlight: true,
           	endDate:today
           });

           $('#toDate').datepicker({
                   	format: "dd-mm-yyyy",
                   	autoclose: true,
                   	todayHighlight: true,
                   	endDate:today
                   });





        getStatesOnLoad();
           getdistrictsOnLoad();
           getSOSDPOLoad();
           getPoliceStationOnLoad();
           getBeatOnLoad();
           getsubmoduleidLoad();

     });

     $('#sampleTable').DataTable();

</script>