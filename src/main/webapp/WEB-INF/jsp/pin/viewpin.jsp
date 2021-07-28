<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<main class="app-content">

<h2 class="form-signin-heading">States Master</h2>

	<c:if test="${not empty pindata}">
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<div class="tile-body">
						<div class="table-responsive">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Pin</th>
										<th>Update</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pindata}" var="pin" varStatus="loopCounter">
										<tr>
											<td>
												<c:out value="${loopCounter.count}"/>
											</td>
											<td>${pin.pin}</td>


											<td>
												<a href="${pageContext.request.contextPath}/updatePin/${pin.pinId}" class="button button-success" ;>Update</a>
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
		</main>



<script type="text/javascript">
   $('#sampleTable').DataTable();
   </script>