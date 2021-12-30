function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 0)); //2  0
}

//Tomcat
function getContextPathTwo() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 0));
}


var formURL = getContextPath();
var formURL_Two = getContextPathTwo();


function getFormData() {

	var loginForm = {};

	loginForm.username = document.getElementById("username").value;
	loginForm.password = document.getElementById("password").value;
	//loginForm.captcha=grecaptcha.getResponse();

	alert(loginForm);
	return loginForm;

}

function submit1() {
	var data_ = JSON.stringify(getFormData());
	alert(formURL);
	$.ajax({
		type: "GET",
		url: formURL,
		contentType: 'application/json',
		dataType: "json",
		data: data_,
		success: function(data) {
			alert(data.message);
			console.log(data.message)

		},
		error: function(data) {
			alert(data);
			console.log(data)
		}

	});
}

//ajaxcall

function getroles() {

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getRoles",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);
			var selectRole = $('#roles'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				selectRole.append("<option value=" + json_.RESPONSE[i].role_id + " >" + json_.RESPONSE[i].role_name + "</option>")
			}

		},
		error: function(data) {
			console.log(data)
		}

	});
}

//getmodules
function getmodules() {

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getModules",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);
			var selectRole = $('#modules'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				selectRole.append("<option value=" + json_.RESPONSE[i].moduleId + " >" + json_.RESPONSE[i].moduleName + "</option>")
			}

		},
		error: function(data) {
			console.log(data)
		}

	});
}

function getModulesUpdated(mid) {

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getModules",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);
			var selectRole = $('#modules'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('mid') != null && document.getElementById('mid').value == json_.RESPONSE[i].moduleId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].moduleId + " >" + json_.RESPONSE[i].moduleName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].moduleId + " >" + json_.RESPONSE[i].moduleName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});
}


function getRolesUpdated(rid) {

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getRoles",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);
			var selectRole = $('#roles'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('rid') != null && document.getElementById('rid').value == json_.RESPONSE[i].role_id) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].role_id + " >" + json_.RESPONSE[i].role_name + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].role_id + " >" + json_.RESPONSE[i].role_name + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});
}





function getdistricts() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getDistricts",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#districts'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select District---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('did') != null && document.getElementById('did').value == json_.RESPONSE[i].districtId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].districtId + " >" + json_.RESPONSE[i].districtName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].districtId + " >" + json_.RESPONSE[i].districtName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}



function getStates() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getStates",

		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#states'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select States---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('did') != null && document.getElementById('did').value == json_.RESPONSE[i].districtId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].stateID + " >" + json_.RESPONSE[i].stateName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].stateID + " >" + json_.RESPONSE[i].stateName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}


function getStatesUpdate() {
	$.ajax({
		type: "GET",
		url: formURL_Two + "/ajax/getStates",

		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#states'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select States---" + "</option>")



			for (i = 0; i < json_.RESPONSE.length; i++) {
				console.log("====" + document.getElementById('did').value);
				console.log(json_.RESPONSE[i].stateID);
				if (document.getElementById('did') != null && document.getElementById('did').value == json_.RESPONSE[i].stateID) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].stateID + " >" + json_.RESPONSE[i].stateName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].stateID + " >" + json_.RESPONSE[i].stateName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}


//getdistrictsUpdate
function getdistrictsUpdate(id) {
	$.ajax({
		type: "GET",
		url: formURL_Two + "/ajax/getDistrictViaState",
		data: {
			"id": id
		},

		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			// var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#districts'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select Districts---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				console.log("====" + document.getElementById('sid').value);
				console.log(json_.RESPONSE[i].stateID);
				if (document.getElementById('sid') != null && document.getElementById('sid').value == json_.RESPONSE[i].districtId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].districtId + " >" + json_.RESPONSE[i].districtName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].districtId + " >" + json_.RESPONSE[i].districtName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}


function getVehicleType() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getVehicleType",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#vehicletype'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('vid') != null && document.getElementById('vid').value == json_.RESPONSE[i].vehicleId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].vehicleId + " >" + json_.RESPONSE[i].vehicleName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].vehicleId + " >" + json_.RESPONSE[i].vehicleName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}


//getBarrierType  "/ajax/getBarrierType"


//getSOSDPO
function getSOSDPO() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getsospdo",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#sosdpoId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierTypeId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].policelineId + " >" + json_.RESPONSE[i].policelineName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].policelineId + " >" + json_.RESPONSE[i].policelineName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getPoliceLines
function getPoliceLines() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getsospdo",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#policelineId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierTypeId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].policelineId + " >" + json_.RESPONSE[i].policelineName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].policelineId + " >" + json_.RESPONSE[i].policelineName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getItems


//getUnits
function getUnits() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getUnits",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#unitId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierTypeId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].unitId + " >" + json_.RESPONSE[i].unitName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].unitId + " >" + json_.RESPONSE[i].unitName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

function getUnitsOnLoad(unitid) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getUnits",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#unitId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('unitid') != null && document.getElementById('unitid').value == json_.RESPONSE[i].unitId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].unitId + " >" + json_.RESPONSE[i].unitName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].unitId + " >" + json_.RESPONSE[i].unitName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getStores
function getStores() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getStores",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#storeId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierTypeId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].storeID + " >" + json_.RESPONSE[i].storeName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].storeID + " >" + json_.RESPONSE[i].storeName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

function getStoresUpdated(storeid) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getStores",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#storeId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('storeid') != null && document.getElementById('storeid').value == json_.RESPONSE[i].storeID) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].storeID + " >" + json_.RESPONSE[i].storeName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].storeID + " >" + json_.RESPONSE[i].storeName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}



//getCategory
function getCategory() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getCategory",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#categoryId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierTypeId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].categoryId + " >" + json_.RESPONSE[i].categoryName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].categoryId + " >" + json_.RESPONSE[i].categoryName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

function getCategoryUpdated(catid) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getCategory",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#categoryId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('catid') != null && document.getElementById('catid').value == json_.RESPONSE[i].categoryId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].categoryId + " >" + json_.RESPONSE[i].categoryName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].categoryId + " >" + json_.RESPONSE[i].categoryName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

function getSOSDPOUpdate() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getsospdo",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#sosdpoId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('sosid') != null && document.getElementById('sosid').value == json_.RESPONSE[i].sosdpoId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].policelineId + " >" + json_.RESPONSE[i].policelineName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].policelineId + " >" + json_.RESPONSE[i].policelineName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getSubmodule
function getSubmodule() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getSubModules",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#submodule'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('sosid') != null && document.getElementById('submoduleId').value == json_.RESPONSE[i].submoduleId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].submoduleId + " >" + json_.RESPONSE[i].submoduleName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].submoduleId + " >" + json_.RESPONSE[i].submoduleName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getSubmoduleUpdate
function getSubmoduleUpdate() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getSubModules",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#submodule'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('submoduleid') != null && document.getElementById('submoduleid').value == json_.RESPONSE[i].submoduleId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].submoduleId + " >" + json_.RESPONSE[i].submoduleName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].submoduleId + " >" + json_.RESPONSE[i].submoduleName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getPoliceStationsViasosdpoid
function getPoliceStationsViasosdpoid(id) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getPoliceStations",
		data: {
			"id": id
		},
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log("======= police Stations" + json_);
			var selectRole = $('#psId'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('psid') != null && document.getElementById('psid').value == json_.RESPONSE[i].psId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].psId + " >" + json_.RESPONSE[i].psName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].psId + " >" + json_.RESPONSE[i].psName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//script getBeat
function getBeat(id) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBeatsViaPolceStation",
		data: {
			"id": id
		},
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log("======= Beats" + json_);
			var selectRole = $('#beats'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('beatid') != null && document.getElementById('beatid').value == json_.RESPONSE[i].beatId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].beatId + " >" + json_.RESPONSE[i].beatName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].beatId + " >" + json_.RESPONSE[i].beatName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}


//getBarriers
function getBarriers(id) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getAllBarriersViaDistrict",
		data: {
			"id": id
		},
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log("======= barriers" + json_);
			var selectRole = $('#barriers'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].barrierId + " >" + json_.RESPONSE[i].barrierName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].barrierId + " >" + json_.RESPONSE[i].barrierName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getBarriersDisable
function getBarriersDisable(id) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getAllBarriersViaDistrict",
		data: {
			"id": id
		},
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log("======= barriersdisabled" + json_);
			var selectRole = $('#barriersdisabled'); // the state select element
			selectRole.find('option').remove();
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].barrierId + " >" + json_.RESPONSE[i].barrierName + "</option>")
				} else {
					selectRole.append("<option disabled value=" + json_.RESPONSE[i].barrierId + " >" + json_.RESPONSE[i].barrierName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}


function getBarrierTypeTwo() {
	$.ajax({
		type: "GET",
		url: formURL_Two + "/ajax/getBarrierType",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#barrierType'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierTypeId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].barrierTypeId + " >" + json_.RESPONSE[i].barrierTypename + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].barrierTypeId + " >" + json_.RESPONSE[i].barrierTypename + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}



function getOwnerType() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getOwnerType",
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			// var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#ownertype'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('oid') != null && document.getElementById('oid').value == json_.RESPONSE[i].vehicleOwnerTypeId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].vehicleOwnerTypeId + " >" + json_.RESPONSE[i].vehicleOwnerTypeName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].vehicleOwnerTypeId + " >" + json_.RESPONSE[i].vehicleOwnerTypeName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//etDistrictsViaStates(this.value)
function getDistrictsViaStates(id) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getDistrictViaState",
		data: {
			"id": id
		},
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			// var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#districts'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select District ---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].districtId + " >" + json_.RESPONSE[i].districtName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].districtId + " >" + json_.RESPONSE[i].districtName + "</option>")
				}

			};

		},
		error: function(data) {
			console.log(data)
		}

	});


}

function loadBarriers(id) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getBarriers",
		data: {
			"id": id
		},
		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#barriers'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select Barrier ---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('bid') != null && document.getElementById('bid').value == json_.RESPONSE[i].barrierId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].barrierId + " >" + json_.RESPONSE[i].barrierName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].barrierId + " >" + json_.RESPONSE[i].barrierName + "</option>")
				}

			};

		},
		error: function(data) {
			console.log(data)
		}

	});


}

function getUsers() {

	var stateID = $("#states :selected").val()
	var districtID = $("#districts :selected").val()
	var barrierId = $("#barriers :selected").val()

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getAllRevenueUsers",
		data: {

			"stateId": stateID,
			"districtId": districtID,
			"barrierId": barrierId
		},
		success: function(data) {
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);
			var selectRole = $("#users");
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('uid') != null && document.getElementById('uid').value == json_.RESPONSE[i].role_id) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].userName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].userName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});
}


function getUsersAll() {

	var stateID = $("#states :selected").val()
	var districtID = $("#districts :selected").val()
	var barrierId = $("#barriers :selected").val()

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getAllRevenueUsers",
		data: {

			"stateId": stateID,
			"districtId": districtID,
			"barrierId": barrierId
		},
		success: function(data) {
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);
			var selectRole = $("#users");
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			selectRole.append("<option value=" + 999 + " >" + "All" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('uid') != null && document.getElementById('uid').value == json_.RESPONSE[i].role_id) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].userName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].userName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});
}



//getUsersOnLoad
function getUsersOnReLoad(did, sid, bid) {

	var stateID = did;

	var districtID = sid;
	var barrierId = bid;

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getAllRevenueUsers",
		data: {

			"stateId": stateID,
			"districtId": districtID,
			"barrierId": barrierId
		},
		success: function(data) {
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);
			var selectRole = $("#users");
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Please Select---" + "</option>")
			selectRole.append("<option value=" + 999 + " >" + "All" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('uid') != null && document.getElementById('uid').value == json_.RESPONSE[i].userId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].userName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].userName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});
}

function sendOtp(uid, amount,barrierId) {

 $('#otpmessage').html("One Time Password has been sent to the respective User.");
 $('#sendotp').text("Resend OTP");

	var revenueUserId = uid;
	var amountDeposited = amount;
	var barrierID = barrierId;

	 document.getElementById('amount').value = amountDeposited;

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getOtpCashier",
		data: {

			"uid": revenueUserId,
			"amount": amountDeposited,
			"barrier_id": barrierId
		},
		success: function(data) {

		var selectRole = $("#otpmessage");
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);

        if(json_.STATUS === "OK"){
        $('#otpmessage').html(json_.RESPONSE);
        $('#verifyOTPRow').show();




        }else{
         $('#otpmessage').html(json_.RESPONSE);
         $('#verifyOTPRow').hide();
        }


		},
		error: function(data) {
			console.log(data)
			//$('#otpmessage').html(json_.RESPONSE);
		}
		

	});
}




function sendOtp_TwoStep(uid,mobileNumber) {

 $('#otpmessage').html("One Time Password has been sent to the respective User.");
 $("#enterotp").value = "";

	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getOtpTwoStep",
		data: {

			"uid": uid,
			"mobileNumber": mobileNumber
		},
		success: function(data) {

		var selectRole = $("#otpmessage");
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);

			console.log(json_);

        if(json_.STATUS === "OK"){
        $('#otpmessage').html(json_.RESPONSE);


        }else{
         $('#otpmessage').html(json_.RESPONSE);
        }


		},
		error: function(data) {
			console.log(data)
		}


	});
}