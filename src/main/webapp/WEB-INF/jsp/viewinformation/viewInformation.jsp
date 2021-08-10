<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="viewInformationWebForm" action="${pageContext.request.contextPath}/updateInformationEntity" class="form-signin">
      <div class="row user">
         <c:if test="${not empty successMessage}">
            <div id="serverError" class="successMessage col-lg-12 ">${successMessage}</div>
         </c:if>
         <br>
         <c:if test="${not empty serverError}">
            <div id="serverError" class="plErroMessage col-lg-12">${serverError}</div>
         </c:if>
         <div class="col-md-12">
            <div class="tab-content">
               <div id="user-timeline">
                  <div class="timeline-post">
                     <h2> Location Details</h2>
                     <hr>
                     <div class="row">
                        <!-- Feilds -->
                        <div class="col-lg-12">
                           <div class="row">
                              <spring:bind path="id">
                                 <div class="col-md-4  form-group ${status.error ? 'has-error' : ''}" >
                                    <form:label path="id"  >Information ID</form:label>
                                    <form:input type="text" path="id" readonly="true" value="${information.id}" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false"></form:input>
                                    <form:errors path="id"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="stateId">
                                 <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="stateId" for="roles">State</form:label>
                                    <form:select path="stateId" name="stateId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="states" onchange="getDistrictsViaStates(this.value)"></form:select>
                                    <form:errors path="stateId"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="districtId">
                                 <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="districtId" for="roles">District</form:label>
                                    <form:select path="districtId" name="stateId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="districts" onchange="getBarriers(this.value)" ></form:select>
                                    <form:errors path="districtId"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="sosdpoId">
                                 <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="sosdpoId" for="roles">SO /SDPO </form:label>
                                    <form:select path="sosdpoId" name="sosdpoId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="sosdpoId" onchange="getPoliceStationsViasosdpoid(this.value)"></form:select>
                                    <form:errors path="sosdpoId"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="psId">
                                 <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="psId" for="poicestation">Police Station </form:label>
                                    <form:select path="psId" name="psId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="psId" onchange="getBeat(this.value);"></form:select>
                                    <form:errors path="psId"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="beatId">
                                 <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="beatId" for="roles">Beat</form:label>
                                    <form:select path="beatId" name="beatId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="beats" ></form:select>
                                    <form:errors path="beatId"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="submoduleId">
                                 <div class="col-md-4 form-group  ${status.error ? 'has-error' : ''}">
                                    <form:label path="submoduleId" for="roles">Sub Module</form:label>
                                    <form:select path="submoduleId" name="submoduleId" autocomplete="off"  oncopy="return false" onpaste="return false" class="form-control" id="submodule" ></form:select>
                                    <form:errors path="submoduleId"></form:errors>
                                 </div>
                              </spring:bind>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-md-12">
            <div class="tab-content">
               <div id="user-timeline">
                  <div class="timeline-post">
                     <h2> Details</h2>
                     <hr>
                     <div class="row">
                        <!-- Feilds -->
                        <div class="col-lg-12">
                           <div class="row">
                           <c:if test="${not empty information.name}">
                              <spring:bind path="name">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="name" for="roles">Name</form:label>
                                    <form:input type="text" path="name" class="form-control" autocomplete="off" value="${information.name}"  oncopy="return false" onpaste="return false" placeholder="Name"
                                       autofocus="true"></form:input>
                                    <form:errors  path="name"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                               <c:if test="${not empty information.ownerName}">
                              <spring:bind path="owner_name">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="owner_name" for="roles">Owner Name</form:label>
                                    <form:input type="text" path="owner_name" class="form-control" autocomplete="off" value="${information.ownerName}"  oncopy="return false" onpaste="return false" placeholder="Owner Name"
                                       autofocus="true"></form:input>
                                    <form:errors  path="owner_name"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                               <c:if test="${not empty information.ownerNameTwo}">
                              <spring:bind path="owner_nametwo">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="owner_nametwo" for="roles">Secondary Contact Name</form:label>
                                    <form:input type="text" path="owner_nametwo" class="form-control" autocomplete="off" value="${information.ownerNameTwo}"  oncopy="return false" onpaste="return false" placeholder="Contact Name Secondary"
                                       autofocus="true"></form:input>
                                    <form:errors  path="owner_nametwo"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                               <c:if test="${not empty information.contactNoOne}">
                              <spring:bind path="contact_numberone">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="contact_numberone" for="roles">Primary Contact Number </form:label>
                                    <form:input type="text" path="contact_numberone" class="form-control" autocomplete="off" value="${information.contactNoOne}"  oncopy="return false" onpaste="return false" placeholder="Primary Contact Number"
                                       autofocus="true"></form:input>
                                    <form:errors  path="contact_numberone"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                                <c:if test="${not empty information.contactNoTwo}">
                              <spring:bind path="contact_numbertwo">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="contact_numbertwo" for="roles">Secondary Contact Number</form:label>
                                    <form:input type="text" path="contact_numbertwo" class="form-control" autocomplete="off" value="${information.contactNoTwo}"  oncopy="return false" onpaste="return false" placeholder="Secondary Contact Number"
                                       autofocus="true"></form:input>
                                    <form:errors  path="contact_numbertwo"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                               <c:if test="${not empty information.helplineNumber}">
                              <spring:bind path="helpline_number">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="helpline_number" for="roles">Helpline Number</form:label>
                                    <form:input type="text" path="helpline_number" class="form-control" autocomplete="off" value="${information.helplineNumber}"  oncopy="return false" onpaste="return false" placeholder="Helpline Number"
                                       autofocus="true"></form:input>
                                    <form:errors  path="helpline_number"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                  <c:if test="${not empty information.landlineNumber}">
                              <spring:bind path="landline_number">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="landline_number" for="roles">Landline Number</form:label>
                                    <form:input type="text" path="landline_number" class="form-control" autocomplete="off" value="${information.landlineNumber}"  oncopy="return false" onpaste="return false" placeholder="Landline Number"
                                       autofocus="true"></form:input>
                                    <form:errors  path="landline_number"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                <c:if test="${not empty information.cctv}">
                              <spring:bind path="cctv">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="cctv" for="roles">CCTV</form:label>
                                    <form:input type="text" path="cctv" class="form-control" autocomplete="off" value="${information.cctv}"  oncopy="return false" onpaste="return false" placeholder="CCTV"
                                       autofocus="true"></form:input>
                                    <form:errors  path="cctv"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                               <c:if test="${not empty information.numberIdols}">
                              <spring:bind path="number_idols">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="number_idols" for="roles">Number of Idols</form:label>
                                    <form:input type="text" path="number_idols" class="form-control" autocomplete="off" value="${information.numberIdols}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="number_idols"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                              <c:if test="${not empty information.numberSecurityPersons}">
                              <spring:bind path="number_securitypersons">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="number_securitypersons" for="roles">Number Security Persons</form:label>
                                    <form:input type="text" path="number_securitypersons" class="form-control" autocomplete="off" value="${information.numberSecurityPersons}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="number_securitypersons"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                <c:if test="${not empty information.emailId}">
                              <spring:bind path="email_id">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="email_id" for="roles">Email Address</form:label>
                                    <form:input type="text" path="email_id" class="form-control" autocomplete="off" value="${information.emailId}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="email_id"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                 <c:if test="${not empty information.facbookId}">
                              <spring:bind path="facbook_id">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="facbook_id" for="roles">Facebook ID </form:label>
                                    <form:input type="text" path="facbook_id" class="form-control" autocomplete="off" value="${information.facbookId}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="facbook_id"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                <c:if test="${not empty information.presentAddress}">
                              <spring:bind path="present_address">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="present_address" for="roles">Present Address</form:label>
                                    <form:input type="text" path="present_address" class="form-control" autocomplete="off" value="${information.presentAddress}"  oncopy="return false" onpaste="return false" placeholder="Landline Number"
                                       autofocus="true"></form:input>
                                    <form:errors  path="present_address"></form:errors>
                                 </div>
                              </spring:bind>
                                </c:if>
                                 <c:if test="${not empty information.permanentAddress}">
                              <spring:bind path="permanent_address">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="permanent_address" for="roles">Permanent Address</form:label>
                                    <form:input type="text" path="permanent_address" class="form-control" autocomplete="off" value="${information.permanentAddress}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="permanent_address"></form:errors>
                                 </div>
                              </spring:bind>
                                </c:if>
                                  <c:if test="${not empty information.firNo}">
                              <spring:bind path="fir_no">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="fir_no" for="roles">Fir Number</form:label>
                                    <form:input type="text" path="fir_no" class="form-control" autocomplete="off" value="${information.firNo}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="fir_no"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                               <c:if test="${not empty information.firDetails}">
                              <spring:bind path="fir_details">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="fir_details" for="roles">Fir Details</form:label>
                                    <form:input type="text" path="fir_details" class="form-control" autocomplete="off" value="${information.firDetails}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="fir_details"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                 <c:if test="${not empty information.licenceeNo}">
                              <spring:bind path="licencee_no">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="licencee_no" for="roles">Licencee Number</form:label>
                                    <form:input type="text" path="licencee_no" class="form-control" autocomplete="off" value="${information.licenceeNo}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="licencee_no"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                               <c:if test="${not empty information.licenceeName}">
                              <spring:bind path="licencee_name">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="licencee_name" for="roles">Lecencee Name</form:label>
                                    <form:input type="text" path="licencee_name" class="form-control" autocomplete="off" value="${information.licenceeName}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="licencee_name"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                <c:if test="${not empty information.details}">
                              <spring:bind path="details">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="details" for="roles">Details</form:label>
                                    <form:input type="text" path="details" class="form-control" autocomplete="off" value="${information.details}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="details"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                <c:if test="${not empty information.other}">
                              <spring:bind path="other">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="other" for="roles">Other Relevant Data</form:label>
                                    <form:input type="text" path="other" class="form-control" autocomplete="off" value="${information.other}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="other"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                <c:if test="${not empty information.checkingDateSho}">
                              <spring:bind path="checking_date_sho">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="checking_date_sho" for="roles">Checking Date SHO</form:label>
                                    <form:input type="text" path="checking_date_sho" class="form-control" autocomplete="off" value="${information.checkingDateSho}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="checking_date_sho"></form:errors>
                                 </div>
                              </spring:bind>
                                </c:if>
                                 <c:if test="${not empty information.totalPopulation}">
                              <spring:bind path="total_population">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="total_population" for="roles">Total Population</form:label>
                                    <form:input type="text" path="total_population" class="form-control" autocomplete="off" value="${information.totalPopulation}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="total_population"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                               <c:if test="${not empty information.periodFair}">
                              <spring:bind path="period_fair">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="period_fair" for="roles">Fair Period</form:label>
                                    <form:input type="text" path="period_fair" class="form-control" autocomplete="off" value="${information.periodFair}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="period_fair"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                               <c:if test="${not empty information.authority}">
                              <spring:bind path="authority">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="authority" for="roles">Authority</form:label>
                                    <form:input type="text" path="authority" class="form-control" autocomplete="off" value="${information.authority}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="authority"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                <c:if test="${not empty information.durationParole}">
                              <spring:bind path="duration_parole">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="duration_parole" for="roles">Duration Parole</form:label>
                                    <form:input type="text" path="duration_parole" class="form-control" autocomplete="off" value="${information.durationParole}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="duration_parole"></form:errors>
                                 </div>
                              </spring:bind>
                                </c:if>
                                 <c:if test="${not empty information.idProof}">
                              <spring:bind path="id_proof">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="id_proof" for="roles">ID Proof</form:label>
                                    <form:input type="text" path="id_proof" class="form-control" autocomplete="off" value="${information.idProof}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="id_proof"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                               <c:if test="${not empty information.section}">
                              <spring:bind path="section">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="section" for="roles">Section</form:label>
                                    <form:input type="text" path="section" class="form-control" autocomplete="off" value="${information.section}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="section"></form:errors>
                                 </div>
                              </spring:bind>
                                </c:if>
                                 <c:if test="${not empty information.specialReportedCases}">
                              <spring:bind path="special_reported_cases">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="special_reported_cases" for="roles">Special Reported Cases</form:label>
                                    <form:input type="text" path="special_reported_cases" class="form-control" autocomplete="off" value="${information.specialReportedCases}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="special_reported_cases"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                                 <c:if test="${not empty information.extraOne}">
                              <spring:bind path="extra_one">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="extra_one" for="roles">Extra One</form:label>
                                    <form:input type="text" path="extra_one" class="form-control" autocomplete="off" value="${information.extraOne}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="extra_one"></form:errors>
                                 </div>
                              </spring:bind>
                              </c:if>
                               <c:if test="${not empty information.extraTwo}">
                              <spring:bind path="extra_two">
                                 <div class="col-md-4 form-group ${status.error ? 'has-error' : ''}">
                                    <form:label path="extra_two" for="roles">Extra Two</form:label>
                                    <form:input type="text" path="extra_two" class="form-control" autocomplete="off" value="${information.extraTwo}"  oncopy="return false" onpaste="return false"
                                       autofocus="true"></form:input>
                                    <form:errors  path="extra_two"></form:errors>
                                 </div>
                              </spring:bind>
                               </c:if>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>


  <div class="col-md-12">
                     <div class="tab-content">
                        <div id="user-timeline">
                           <div class="timeline-post">
                              <h2> Geographic Location</h2>
                              <hr>
                              <div class="row">
                                 <!-- Feilds -->
                                 <div class="col-lg-12">
                                    <div class="row">
                                       <spring:bind path="latitude">
                                          <div class="col-md-4  form-group ${status.error ? 'has-error' : ''}" >
                                             <form:label path="latitude"  >Latitude</form:label>
                                             <form:input type="text" path="latitude" readonly="true" value="${information.latitude}" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false"></form:input>
                                             <form:errors path="latitude"></form:errors>
                                          </div>
                                       </spring:bind>

                                      <spring:bind path="longitude">
                                        <div class="col-md-4  form-group ${status.error ? 'has-error' : ''}" >
                                           <form:label path="longitude" >Latitude</form:label>
                                           <form:input type="text" path="longitude" readonly="true" value="${information.longitude}" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false"></form:input>
                                           <form:errors path="longitude"></form:errors>
                                        </div>
                                     </spring:bind>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>


          <div class="col-md-12">
                              <div class="tab-content">
                                 <div id="user-timeline">
                                    <div class="timeline-post">
                                       <h2> Information Added By:-</h2>
                                       <hr>
                                       <div class="row">
                                          <!-- Feilds -->
                                          <div class="col-lg-12">
                                             <div class="row">
                                                 <div class=" col-lg-4 form-group">
                                                  <fieldset disabled="">
                                                     <label class="control-label" for="disabledInput">Username:</label>
                                                     <input class="form-control" id="disabledInput" type="text" value="${user.userName}" disabled="">
                                                  </fieldset>
                                               </div>

                                                <div class=" col-lg-4 form-group">
                                                 <fieldset disabled="">
                                                    <label class="control-label" for="disabledInput">Mobile Number:</label>
                                                    <input class="form-control" id="disabledInput" type="text" value="${user.mobileNumber}" disabled="">
                                                 </fieldset>
                                              </div>

                                              <div class=" col-lg-4 form-group">
                                               <fieldset disabled="">
                                                  <label class="control-label" for="disabledInput">Created On :</label>
                                                  <label class="form-label" for="disabledInput"> <fmt:formatDate value='${information.createdDate}' pattern='dd-MM-yyyy HH:mm:ss' /></label>
                                                   </fieldset>
                                            </div>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>

 <input type="submit" id="payment_proceed"  value="Update Information" class="btn btn-primary">
   <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

      </div>
   </form:form>
   <input class="form-control col-md-6"  id="did" type="hidden" value="${state_id}"  />
   <input class="form-control col-md-6"  id="sid" type="hidden" value="${district_id}"  />
   <input class="form-control col-md-6"  id="sosid" type="hidden" value="${sodpo_id}"  />
   <input class="form-control col-md-6"  id="psid" type="hidden" value="${ps_id}"  />
   <input class="form-control col-md-6"  id="beatid" type="hidden" value="${beat_id}"  />
   <input class="form-control col-md-6"  id="submoduleid" type="hidden" value="${submodule_id}"  />
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
        getStatesOnLoad();
           getdistrictsOnLoad();
           getSOSDPOLoad();
           getPoliceStationOnLoad();
           getBeatOnLoad();
           getsubmoduleidLoad();

     });


</script>