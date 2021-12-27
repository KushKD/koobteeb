<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<main class="app-content">

<h2 class="form-signin-heading">Units Master</h2>

	<c:if test="${not empty units}">
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<div class="tile-body">
						<div class="table-responsive">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Unit Name</th>
										<th>Unit Id</th>
										<th>Active</th>
										<th>Update</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${units}" var="unit" varStatus="loopCounter">
										<tr>
											<td>
												<c:out value="${loopCounter.count}"/>
											</td>
											<td>${unit.unitName}</td>
											<td>${unit.unitId}</td>
											<c:if test="${unit.active}">
											<td class="btn-success text-center" style="color:#FFFFFF">Currently Active</td>
											</c:if>
											<c:if test="${not unit.active}">
                                            <td class="btn-danger text-center" style="color:#FFFFFF">Not Active</td>
                                            </c:if>

											<td>
												<a href="${pageContext.request.contextPath}/updateUnit/${unit.unitId}" class="button button-success" ;>Update</a>
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