<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>

<style>
#files-area{
	width: 30%;
	margin: 0 auto;
}
.file-block{
	border-radius: 10px;
	background-color: rgba(144, 163, 203, 0.2);
	margin: 5px;
	color: initial;
	display: inline-flex;
	& > span.name{
		padding-right: 10px;
		width: max-content;
		display: inline-flex;
	}
}
.file-delete{
	display: flex;
	width: 24px;
	color: initial;
	background-color: #6eb4ff00;
	font-size: large;
	justify-content: center;
	margin-right: 3px;
	cursor: pointer;
	&:hover{
		background-color: rgba(144, 163, 203, 0.2);
		border-radius: 10px;
	}
	& > span{
		transform: rotate(45deg);
	}
}

</style>

<main class="app-content">
   <form:form method="POST" modelAttribute="transferForm" action="${pageContext.request.contextPath}/saveTransfer" enctype="multipart/form-data" class="form-signin">
      <h2 class="form-signin-heading">Create New Request for Transfer</h2>
      <c:if test="${not empty successMessage}">
         <div id="serverError" class="successMessage">${successMessage}</div>
      </c:if>
      <br>
      <c:if test="${not empty serverError}">
         <div id="serverError" class="plErroMessage">${serverError}</div>
      </c:if>
      <spring:bind path="vehicleNo">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="vehicleNo"  autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" style="text-transform:uppercase" onkeypress="return alpha(event)"  autofocus="true" placeholder="Vehicle Number"></form:input>
            <form:errors path="vehicleNo"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="fromDate">
         <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="fromDate" id="fromDate" onkeypress="return alpha(event)" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="From Date"></form:input>
            <form:errors path="fromDate"></form:errors>
         </div>
      </spring:bind>
      <spring:bind path="thruDate">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" path="thruDate" id="thrueDate" onkeypress="return alpha(event)" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Thru Date"></form:input>
                  <form:errors path="thruDate"></form:errors>
               </div>
            </spring:bind>



       <spring:bind path="sourceAddress">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                               <form:textarea  path="sourceAddress" rows="3" cols="30" onkeypress="return alpha(event)" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Source Address"/>
                               <form:errors path="sourceAddress"></form:errors>
                            </div>
                         </spring:bind>

          <spring:bind path="destAddress">
                                   <div class="form-group ${status.error ? 'has-error' : ''}">
                                      <form:textarea  path="destAddress" rows="3" cols="30" onkeypress="return alpha(event)" class="form-control"  autocomplete="off"  oncopy="return false" onpaste="return false" placeholder="Destination Address" />
                                      <form:errors path="destAddress"></form:errors>
                                   </div>
                                </spring:bind>

        <spring:bind path="amount">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" path="amount"  autocomplete="off"  oncopy="return false" onpaste="return false"  class="form-control" style="text-transform:uppercase" onkeypress="return alpha(event)"  autofocus="true" placeholder="Amount to Transfer"></form:input>
                  <form:errors path="amount"></form:errors>
               </div>
            </spring:bind>

      <div  id="attachFiles1" class="form-group col-lg-4">
         <label for="attachFiles" class="form-label"> <spring:message code="form.documentry.aadhaar" text="Attach Files" />
            * </label>
         <form:input class="form-control" oncopy="return false" onpaste="return false" type="file" id="attachFiles" multiple="multiple" path="attachFiles" name="attachFiles" />
         <form:errors style="color:red;" path="attachFiles"></form:errors>
         <div class="col-lg-12"><div class="image-preview row"></div></div>
      </div>



      <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
       <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
      <c:remove var="successMessage" scope="session" />
   </form:form>
   </div>
</main>


<script type="text/javascript">

  $( document ).ready(function() {


  var date = new Date();
  var from_today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
 $('#fromDate').datepicker({
        	format: "dd/mm/yyyy",
        	autoclose: true,
        	todayHighlight: true,
        	startDate: new Date()
        	//endDate:from_today

        });


  $('#thrueDate').datepicker({
         	format: "dd/mm/yyyy",
         	autoclose: true,
         	todayHighlight: true,
         	startDate: new Date()
         	//endDate:from_today

         });


  });

</script>
  <script>
  let file_input = document.querySelector('#attachFiles');
  let image_preview = document.querySelector('.image-preview');

  const handle_file_preview = (e) => {
   let files = e.target.files;
    let length = files.length;

    for(let i = 0; i < length; i++) {
     var div = document.createElement('div');
     div.style.cssText += 'display: inline; align-items: center; justify-content: center';
     div.classList.add("col-lg-3");
     div.id = 'div'+i;

        let image = document.createElement('img');
        image.style.cssText += 'height: 75px; border: 1px solid #000; margin: 5px';

        image.src = window.URL.createObjectURL(files[i]);
        console.log(files[i].name);
        image_preview.appendChild(image);
         let label = document.createElement('label');
         label.style.cssText += 'color:black; margin: 5px';
         label.innerHTML = files[i].name;
         div.appendChild(image);
         div.appendChild(label);
         image_preview.appendChild(div);

    }
  }

  file_input.addEventListener('change', handle_file_preview);
  </script>