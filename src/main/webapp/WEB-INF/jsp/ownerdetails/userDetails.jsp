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
   <div class="row user">
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage col-lg-12 ">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage col-lg-12">${serverError}</div>
      </c:if>

      <div class="modal fade bd-example-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1">
          <div class="modal-dialog modal-sm">
              <div class="modal-content" style="width: 200px;">
                  <span class="fa fa-spinner fa-spin fa-5x fa-fw" style="width: 200px;"></span>
                  <p class="please-wait-message">Processing .. Please wait</p>
              </div>
          </div>
      </div>


      <div class="col-md-12">
         <div class="tab-content">
            <div id="user-timeline">
               <div class="timeline-post">
                  <h2> Vehicle Owner Details</h2>
                  <hr>
                  <div class="row">
                     <!-- Feilds -->
                     <div class="col-lg-10">
                        <div class="row">
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Vehicle Owner Name:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerName}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Vehicle Owner Mobile Number :</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerMobileNumber}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Aadhaar Number:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerAadhaarNumber}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Vehicle Owner Address:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerAddress}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Vehicle Driver Address:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleDriverAddress}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Vehicle Owner Type:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleUser.vehicleOwnerTypeName}" disabled="">
                              </fieldset>
                           </div>
                        </div>
                     </div>
                     <!-- Image -->
                     <div class="col-lg-2">
                        <div class="row">
                           <div class=" col-lg-12 form-group">
                              <img style="width:200px;" class="img-thumbnail img-responsive"  src="${pageContext.request.contextPath}/downloadFile/${vehicleData.vehicleOwnerImageNameOne}" >
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   </div>
   <!-- Section Two -->
   <div class="row user">
      <div class="col-md-12">
         <div class="tab-content">
            <div id="user-timeline">
               <div class="timeline-post">
                  <h2>Vehicle Details</h2>
                  <hr>
                  <div class="row">
                     <!-- Feilds -->
                     <div class="col-lg-10">
                        <div class="row">
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Vehicle Type:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleType.vehicleName}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Vehicle Number :</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerVehicleNumber}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Engine Number:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerEngineNumber}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Chassis Number:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerChassisNumber}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Driving Licence Number:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.vehicleOwnerDrivingLicence}" disabled="">
                              </fieldset>
                           </div>
                        </div>
                     </div>
                     <!-- Image -->
                     <div class="col-lg-2">
                        <div class="row">
                           <div class=" col-lg-12 form-group">
                              <img style="width:200px;" class="img-thumbnail img-responsive"  src="${pageContext.request.contextPath}/downloadFile/${vehicleData.vehicleOwnerImageNameTwo}" >
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   </div>
   <!-- Section Two Ends -->
   <!-- Section Three -->
   <div class="row user">
      <div class="col-md-12">
         <div class="tab-content">
            <div id="user-timeline">
               <div class="timeline-post">
                  <h2>Identity Details</h2>
                  <hr>
                  <div class="row">
                     <!-- Feilds -->
                     <div class="col-lg-12">
                        <div class="row">
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Identity Number:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.idCardNumber}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Identity Valid From :</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.isValidFrom}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">Identity Valid Upto:</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.isValidUpto}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">ID Generated at Barrier :</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.barriermaster.barrierName}" disabled="">
                              </fieldset>
                           </div>
                           <div class=" col-lg-4 form-group">
                              <fieldset disabled="">
                                 <label class="control-label" for="disabledInput">ID Created Date</label>
                                 <input class="form-control" id="disabledInput" type="text" value="${vehicleData.cardCreation}" disabled="">
                              </fieldset>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   </div>
   <!-- Section Three Ends -->
   <!-- Section Four -->
   <c:if test="${not empty routesData}">
      <div class="row user">
         <div class="col-md-12">
            <div class="tab-content">
               <div id="user-timeline">
                  <div class="timeline-post">
                     <h2>Route Details</h2>
                     <hr>
                     <div class="row">
                        <div class="col-md-12">
                           <div class="tile">
                              <div class="tile-body">
                                 <div class="table-responsive">
                                    <table class="table table-hover table-bordered" id="sampleTable">
                                       <thead>
                                          <tr>
                                             <th>S.No</th>
                                             <th>Coming From</th>
                                             <th>No. of Boxes</th>
                                             <th>State (Going to)</th>
                                             <th>District (Going to)</th>
                                             <th>Address( Going to)</th>
                                             <th>Timings</th>
                                          </tr>
                                       </thead>
                                       <tbody>
                                          <c:forEach items="${routesData}" var="route" varStatus="loopCounter">
                                             <tr>
                                                <td>
                                                   <c:out value="${loopCounter.count}"/>
                                                </td>
                                                <td>${route.comingFrom.conformationlocationName}</td>
                                                <td>${route.boxesnumber_}</td>
                                                <td>${route.stateId.stateName}</td>
                                                <td>${route.districtId.districtName}</td>
                                                <td>${route.addressgoingto_}</td>
                                                <td style="color:red;">
                                                   <fmt:formatDate value='${route.createdDate}' pattern='dd-MM-yyyy HH:mm:ss' />
                                                </td>
                                             </tr>
                                          </c:forEach>
                                       </tbody>
                                    </table>
                                 </div>
                              </div>
                              <div></div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
   </c:if>
   </div>
   <!-- Section Four Ends -->
   <!-- Section Four Scan-->
   <c:if test="${not empty scannedItems}">
      <div class="row user">
         <div class="col-md-12">
            <div class="tab-content">
               <div id="user-timeline">
                  <div class="timeline-post">
                     <h2>ID Card Scanned at:-</h2>
                     <hr>
                     <div class="row">
                        <div class="col-md-12">
                           <div class="tile">
                              <div class="tile-body">
                                 <div class="table-responsive">
                                    <table class="table table-hover table-bordered" id="sampleTable">
                                       <thead>
                                          <tr>
                                             <th>S.No</th>
                                             <th>Scanned at Barrier</th>
                                             <th>Time of Scan</th>
                                          </tr>
                                       </thead>
                                       <tbody>
                                          <c:forEach items="${scannedItems}" var="scan" varStatus="loopCounter">
                                             <tr>
                                                <td>
                                                   <c:out value="${loopCounter.count}"/>
                                                </td>
                                                <td>${scan.barriermaster.barrierName}</td>
                                                <td style="color:red;">
                                                   <fmt:formatDate value='${scan.createdDate}' pattern='dd-MM-yyyy HH:mm:ss' />
                                                </td>
                                             </tr>
                                          </c:forEach>
                                       </tbody>
                                    </table>
                                 </div>
                              </div>
                              <div></div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
   </c:if>
   </div>
   <!-- Section Four Scan Ends -->
   <!-- Section Five -->
   <c:if test="${not empty paymentTransactions}">
      <div class="row user">
         <div class="col-md-12">
            <div class="tab-content">
               <div id="user-timeline">
                  <div class="timeline-post">
                     <h2>Payment Details</h2>
                     <hr>
                     <div class="row">
                        <div class="col-md-12">
                           <div class="tile">
                              <div class="tile-body">
                                 <div class="table-responsive">
                                    <table class="table table-hover table-bordered" id="sampleTable">
                                       <thead>
                                          <tr>
                                             <th>S.No</th>
                                             <th>Payment Mode</th>
                                             <th>Amount</th>
                                             <th>Payment Made On</th>
                                          </tr>
                                       </thead>
                                       <tbody>
                                          <c:forEach items="${paymentTransactions}" var="paymnet" varStatus="loopCounter">
                                             <tr>
                                                <td>
                                                   <c:out value="${loopCounter.count}"/>
                                                </td>
                                                <td>${paymnet.paymentMode}</td>
                                                <td>${paymnet.amount}</td>
                                                <td style="color:red;">
                                                   <fmt:formatDate value='${paymnet.createdDate}' pattern='dd-MM-yyyy HH:mm:ss' />
                                                </td>
                                             </tr>
                                          </c:forEach>
                                       </tbody>
                                    </table>
                                 </div>
                              </div>
                              <div>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="row">
                        <div class="col-md-12 text-center">
                           <a class="btn btn-primary" href="${pageContext.request.contextPath}/generateId/${vehicleData.vehicleOwnerId}" target="_blank" style="color:white; text-decoration:none;"  >Download ID Card</a>
                           <a class="btn btn-danger" href="${pageContext.request.contextPath}/showIdCards" style="color:white; text-decoration:none;"  >Go Back</a>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
   </c:if>
   </div>
   <!-- Section Five Ends -->
   <!-- Section Payment transactionUser -->
   <c:if test="${empty paymentTransactions}">
      <div class="row user">
         <div class="col-md-12">
            <div class="tab-content">
               <div id="user-timeline">
                  <div class="timeline-post">
                     <h2>Make Payment</h2>
                        <h2 class="bredcum">Amount of Rs ${vehicleData.vehicleType.fee}/- needs to be collected</h2>
                     <hr>
                     <div class="row">
                        <div class="col-lg-12">
                           <form:form method="POST" onsubmit="return submit_form()"  modelAttribute="actionForm" action="${pageContext.request.contextPath}/updatePaymentDetails" class="form-signin">
                              <spring:bind path="modepayment">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:label  path="modepayment" > Please Select Mode of Payment </form:label>
                                    <form:select  path="modepayment" class="form-control" >
                                       <form:option value="CASH"> Cash </form:option>
                                    </form:select>
                                    <form:errors  style="color:red;" path="modepayment"></form:errors>
                                 </div>
                              </spring:bind>

                                 <spring:bind path="amount">
                                    <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                       <label for="amount">
                                          <spring:message code="form.addressss"  text="Enter Amount" />
                                       </label>
                                       <form:input type="text" readonly="true" path="amount" id="amount" class="form-control" value="${vehicleData.vehicleType.fee}"   oncopy="return false" onpaste="return false" />
                                       <form:errors style="color:red;"  path="amount"></form:errors>
                                    </div>
                                 </spring:bind>

                                  <spring:bind path="proceedWithoutPayment">
                                                   <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                                      <form:label  path="proceedWithoutPayment" > Please Select any Action </form:label>
                                                      <form:select  path="proceedWithoutPayment" class="form-control" id="proceedWithoutPayment">

                                                         <option value="C"> Proceed with Payment </option>
                                                          <option value="T"> Proceed Without Payment </option>

                                                      </form:select>
                                                      <form:errors  style="color:red;" path="proceedWithoutPayment"></form:errors>
                                                   </div>
                                                </spring:bind>

                              <spring:bind path="vehicle_number">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:input type="hidden" path="vehicle_number" id="vehicle_number" class="form-control"  value="${vehicleData.vehicleOwnerVehicleNumber}" />
                                    <form:errors style="color:red;"  path="vehicle_number"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="vehicleOwnerId">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:input type="hidden" path="vehicleOwnerId" id="vehicleOwnerId" class="form-control"  value="${vehicleData.vehicleOwnerId}" />
                                    <form:errors style="color:red;"  path="vehicleOwnerId"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="vehicleowner_mobile">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:input type="hidden" path="vehicleowner_mobile" id="vehicleowner_mobile" class="form-control"  value="${vehicleData.vehicleOwnerMobileNumber}" />
                                    <form:errors style="color:red;"  path="vehicleowner_mobile"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="barrierID">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:input type="hidden" path="barrierID" id="barrierID" class="form-control"  value="${vehicleData.barriermaster.barrierId}" />
                                    <form:errors style="color:red;"  path="barrierID"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="idCardNumber">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:input type="hidden" path="idCardNumber" id="idCardNumber" class="form-control"  value="${vehicleData.idCardNumber}" />
                                    <form:errors style="color:red;"  path="idCardNumber"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="vehicleOwnerName">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:input type="hidden" path="vehicleOwnerName" id="vehicleOwnerName" class="form-control"  value="${vehicleData.vehicleOwnerName}" />
                                    <form:errors style="color:red;"  path="vehicleOwnerName"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="barrierName">
                                 <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                    <form:input type="hidden" path="barrierName" id="barrierName" class="form-control"  value="${vehicleData.barriermaster.barrierName}" />
                                    <form:errors style="color:red;"  path="barrierName"></form:errors>
                                 </div>
                              </spring:bind>
                              <spring:bind path="vehicleType">
                                                               <div class="form-group col-lg-12 ${status.error ? 'has-error' : ''}">
                                                                  <form:input type="hidden" path="vehicleType" id="vehicleType" class="form-control"  value="${vehicleData.vehicleType.vehicleId}" />
                                                                  <form:errors style="color:red;"  path="vehicleType"></form:errors>
                                                               </div>
                                                            </spring:bind>


                              <input type="submit" id="payment_proceed"  value="Proceed" class="btn btn-success">
                               <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                           </form:form>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      </div>
   </c:if>
   <!-- Section Five Ends -->
</main>

<script>
function submit_form() {

    document.getElementById('payment_proceed').disabled = 'disabled';

   return true;

}
</script>