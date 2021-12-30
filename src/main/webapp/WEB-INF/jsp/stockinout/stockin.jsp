<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<main class="app-content">
   <form:form method="POST" modelAttribute="stockinForm" action="${pageContext.request.contextPath}/saveStockinItem"  class="form-signin">
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
                       <form:label path="policelineId" for="policelineId">Select Police Lines </form:label>
                       <form:select path="policelineId" name="policelineId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="policelineId" ></form:select>
                       <form:errors path="policelineId"></form:errors>
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
                                  <form:label path="categoryId" for="poicestation">Select Category  </form:label>
                                  <form:select path="categoryId" name="categoryId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="categoryId" onchange="getItems(this.value)" ></form:select>
                                  <form:errors path="categoryId"></form:errors>
                               </div>
                            </spring:bind>

           <spring:bind path="itemId">
                              <div class="form-group col-lg-3  ${status.error ? 'has-error' : ''}">
                                 <form:label path="itemId" for="itemId">Select Item </form:label>
                                 <form:select path="itemId" name="itemId" class="form-control" autocomplete="off"  oncopy="return false" onpaste="return false" id="itemId" ></form:select>
                                 <form:errors path="itemId"></form:errors>
                              </div>
                           </spring:bind>




         <spring:bind path="quantity">
            <div class="form-group col-lg-3 ${status.error ? 'has-error' : ''}">
               <form:label path="quantity" for="roles">Enter Item Quantity</form:label>
               <form:input type="text" autocomplete="off"  oncopy="return false" onpaste="return false" path="quantity" class="form-control" placeholder="Enter Item Quantity"
                  autofocus="true"></form:input>
               <form:errors  path="quantity"></form:errors>
            </div>
         </spring:bind>


      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Add Stock</button>
      <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
</main>
<script type="text/javascript">




 function getItems(category){

    var e = document.getElementById("storeId");
    var storeId = e.options[e.selectedIndex].value ;
    alert("Selected Value: "+storeId);

    $.ajax({
    		type: "GET",
    		url: formURL + "/ajax/getItemsViaCatStore",
    		data: {

    			"cid": category,
    			"sid": storeId
    		},

    		success: function(data) {
            			var json_ = JSON.parse(JSON.stringify(data));


            			console.log(json_);
            			var selectRole = $("#itemId");
            			selectRole.find('option').remove();
            			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
            			for (i = 0; i < json_.RESPONSE.length; i++) {

            					selectRole.append("<option value=" + json_.RESPONSE[i].itemId + " >" + json_.RESPONSE[i].itemsName + "</option>")

            			}

            		},
            		error: function(data) {
            			console.log(data)
            		}


    	});

   }

   $(document).ready(function() {
    getPoliceLines();
    getStores();
    getCategory();




   });
</script>