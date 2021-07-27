<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrapd.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plugins/dataTables.bootstrap.min.js"></script>
<main class="app-content">

<h2 class="form-signin-heading">Module Master</h2>

	<c:if test="${not empty modules}">
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<div class="tile-body">
						<div class="table-responsive">
							<table class="table table-hover table-bordered" id="sampleTable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Module Name</th>
										<th>Module Id</th>
										<th>Module Image</th>
										<th>Update</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${modules}" var="module" varStatus="loopCounter">
										<tr>
											<td>
												<c:out value="${loopCounter.count}"/>
											</td>
											<td>${module.moduleName}</td>
											<td>${module.moduleId}</td>
											<td> <a style="text-decoration:none;" href="${pageContext.request.contextPath}/downloadFile/${module.iconName}" targer="_blank">${module.iconName}</a></td>
											<td>
												<a href="${pageContext.request.contextPath}/updateModule/${module.moduleId}" class="button button-success" ;>Update</a>
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