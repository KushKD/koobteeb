<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="itemForm" action="${pageContext.request.contextPath}/saveItem" enctype="multipart/form-data"  class="form-signin">
      <h2 class="form-signin-heading">Add Item</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <div class="row">
         <spring:bind path="policelineId">
            <div class="form-group col-lg-3  ${status.error ? 'has-error' : ''}">
               <form:label path="policelineId" for="roles">Select Police Line </form:label>
               <form:select path="policelineId" name="policelineId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="policelineId" onchange="getPoliceStationsViasosdpoid(this.value)"></form:select>
               <form:errors path="policelineId"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="psId">
            <div class="form-group col-lg-3  ${status.error ? 'has-error' : ''}">
               <form:label path="psId" for="poicestation">Select Police Station </form:label>
               <form:select path="psId" name="psId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="psId" onchange="getBeat(this.value);"></form:select>
               <form:errors path="psId"></form:errors>
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
               <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="itemsName" class="form-control" placeholder="Enter Item Name"
                  autofocus="true"></form:input>
               <form:errors  path="itemsName"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="quantity">
            <div class="form-group col-lg-3 ${status.error ? 'has-error' : ''}">
               <form:label path="quantity" for="roles">Enter Quantity</form:label>
               <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="quantity" class="form-control" placeholder="Enter Quantity"
                  autofocus="true"></form:input>
               <form:errors  path="quantity"></form:errors>
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
               <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="itemsDesc" class="form-control" placeholder="Enter Item Desc"
                  autofocus="true"></form:input>
               <form:errors  path="itemsDesc"></form:errors>
            </div>
         </spring:bind>
         <spring:bind path="itemsLetterrefno">
             <div class="form-group col-lg-3 ${status.error ? 'has-error' : ''}">
                <form:label path="itemsLetterrefno" for="roles">Enter Letter Reference No.</form:label>
                <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="itemsLetterrefno" class="form-control" placeholder="Enter Letter Ref. No."
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
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
      <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
</main>
<script type="text/javascript">
   $(document).ready(function() {
   	getPoliceLines();
   	getUnits();
   	getStores();
   	getCategory();

   });
</script>