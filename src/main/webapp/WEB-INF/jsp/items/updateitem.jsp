<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="updateItem" action="${pageContext.request.contextPath}/saveItemEntity" enctype="multipart/form-data"  class="form-signin">
      <h2 class="form-signin-heading">Update Item</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <div class="row">

<spring:bind path="itemsId">
            <div class="form-group col-lg-3 ${status.error ? 'has-error' : ''}">
               <form:label path="itemsId" for="roles">Item Id</form:label>
               <form:input type="text" readonly="true" value="${item_to_update.itemsId}" autocomplete="off"  oncopy="return false" onpaste="return false" path="itemsId" class="form-control"
                  autofocus="true"></form:input>
               <form:errors  path="itemsId"></form:errors>
            </div>
         </spring:bind>

         <spring:bind path="storeId">
            <div class="form-group col-lg-3  ${status.error ? 'has-error' : ''}">
               <form:label path="storeId" for="storeId">Select Store Type </form:label>
               <form:select path="storeId" name="storeId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="storeId" ></form:select>
               <form:errors path="storeId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="categoryId">
            <div class="form-group col-lg-3  ${status.error ? 'has-error' : ''}">
               <form:label path="categoryId" for="poicestation">Select Category for Item </form:label>
               <form:select path="categoryId" name="categoryId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="categoryId" ></form:select>
               <form:errors path="categoryId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="itemsName">
            <div class="form-group col-lg-3 ${status.error ? 'has-error' : ''}">
               <form:label path="itemsName" for="roles">Enter Item Name</form:label>
               <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="itemsName" class="form-control" value="${item_to_update.itemsName}"
                  autofocus="true"></form:input>
               <form:errors  path="itemsName"></form:errors>
            </div>
         </spring:bind>

         <spring:bind path="unitId">
            <div class="form-group col-lg-3  ${status.error ? 'has-error' : ''}">
               <form:label path="unitId" for="unitId">Select Units for Item </form:label>
               <form:select path="unitId" name="psId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="unitId" ></form:select>
               <form:errors path="unitId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="itemsDesc">
            <div class="form-group col-lg-3 ${status.error ? 'has-error' : ''}">
               <form:label path="itemsDesc" for="roles">Enter Item Description</form:label>
               <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="itemsDesc" class="form-control" value="${item_to_update.itemsDesc}"
                  autofocus="true"></form:input>
               <form:errors  path="itemsDesc"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="itemsLetterrefno">
             <div class="form-group col-lg-3 ${status.error ? 'has-error' : ''}">
                <form:label path="itemsLetterrefno" for="roles">Enter Letter Reference No.</form:label>
                <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="itemsLetterrefno" class="form-control"  value="${item_to_update.itemsLetterno}"
                   autofocus="true"></form:input>
                <form:errors  path="itemsLetterrefno"></form:errors>
             </div>
          </spring:bind>
          <div  id="itemsLetterrefdoc" class="form-group col-lg-4">
                        <label for="aadhaar_doc" class="form-label">
                          Upload Letter/Document
                           *
                        </label>
                        <form:input class="form-control" oncopy="return false" onpaste="return false" type="file" id="itemsLetterrefdoc" path="itemsLetterrefdoc" name="itemsLetterrefdoc" />
                        <form:errors style="color:red;" path="itemsLetterrefdoc"></form:errors>
                     </div>
            <div  class="form-group col-lg-4">
             <label  class="form-label">View Uploaded Document/Letter *</label>
                  <a style="text-decoration:none;" href="${pageContext.request.contextPath}/downloadFile/${item_to_update.itemsLetterdoc}" target="_blank"> ${item_to_update.itemsLetterdoc}</a>
                  </div>

                   <spring:bind path="active">
                           <div class=" col-lg-3 form-group ${status.error ? 'has-error' : ''}">
                              <form:label path="active"> Is Active </form:label>
                              <form:select path="active" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="earlierService">
                                 <form:option value=""> --Select-- </form:option>
                                 <form:option value="T"> Yes </form:option>
                                 <form:option value="F"> No </form:option>
                              </form:select>
                              <form:errors style="color:red;" path="active"></form:errors>
                           </div>
                        </spring:bind>
                        <spring:bind path="deleted">
                           <div class="col-lg-3 form-group ${status.error ? 'has-error' : ''}">
                              <form:label path="deleted"> Is Deleted </form:label>
                              <form:select path="deleted" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="earlierService">
                                 <form:option value=""> --Select-- </form:option>
                                 <form:option value="T"> Yes </form:option>
                                 <form:option value="F"> No </form:option>
                              </form:select>
                              <form:errors style="color:red;" path="deleted"></form:errors>
                           </div>
                        </spring:bind>

      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
      <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
       <input class="form-control col-md-6"  id="storeid" type="hidden" value="${item_to_update.store.storeID}"  />
        <input class="form-control col-md-6"  id="catid" type="hidden" value="${item_to_update.categoryItemsEntity.categoryId}"  />
         <input class="form-control col-md-6"  id="unitid" type="hidden" value="${item_to_update.units.unitId}"  />
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
</main>
<script type="text/javascript">
function getStoresOnLoad(){
                       if(document.getElementById('storeid') != null && document.getElementById('storeid').value  != null ){
                               getStoresUpdated(document.getElementById('storeid').value);
                               }
                           }

 function getCategoriesOnLoad(){
                        if(document.getElementById('catid') != null && document.getElementById('catid').value  != null ){
                                getCategoryUpdated(document.getElementById('catid').value);
                                }
                            }
 function getUnitsOnLoad(){
                         if(document.getElementById('unitid') != null && document.getElementById('unitid').value  != null ){
                                 getUnitsUpdated(document.getElementById('unitid').value);
                                 }
                             }
   $(document).ready(function() {
getStoresOnLoad();
getCategoriesOnLoad();
getUnitsOnLoad();


   });
</script>