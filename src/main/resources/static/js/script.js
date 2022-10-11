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

//getBanks
function getBanks() {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getsospdo",

		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#banks'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select Banks---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('baid') != null && document.getElementById('baid').value == json_.RESPONSE[i].bankId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].bankId + " >" + json_.RESPONSE[i].bankName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].bankId + " >" + json_.RESPONSE[i].bankName + "</option>")
				}
			}

		},
		error: function(data) {
			console.log(data)
		}

	});


}

//getbanksUpdate
function getbanksUpdate(baid) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getsospdo",

		success: function(data) {
			//Tomcat
			var json_ = JSON.parse(JSON.stringify(data));
			//Jboss
			//var json_ = JSON.parse(data);
			console.log(json_);
			var selectRole = $('#banks'); // the state select element
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select Banks---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
				if (document.getElementById('baid') != null && document.getElementById('baid').value == json_.RESPONSE[i].bankId) {
					selectRole.append("<option selected value=" + json_.RESPONSE[i].bankId + " >" + json_.RESPONSE[i].bankName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].bankId + " >" + json_.RESPONSE[i].bankName + "</option>")
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
					selectRole.append("<option selected value=" + json_.RESPONSE[i].sosdpoId + " >" + json_.RESPONSE[i].sosdpoName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].sosdpoId + " >" + json_.RESPONSE[i].sosdpoName + "</option>")
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
					selectRole.append("<option selected value=" + json_.RESPONSE[i].sosdpoId + " >" + json_.RESPONSE[i].sosdpoName + "</option>")
				} else {
					selectRole.append("<option value=" + json_.RESPONSE[i].sosdpoId + " >" + json_.RESPONSE[i].sosdpoName + "</option>")
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
				if (document.getElementById('sid') != null && document.getElementById('sid').value == json_.RESPONSE[i].barrierId) {
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

function getUsers(id) {
	$.ajax({
		type: "GET",
		url: formURL + "/ajax/getUsers",
		data: {
			"id": id
		},
		success: function(data) {
			var json_ = JSON.parse(JSON.stringify(data));
			console.log(json_);
			var selectRole = $('#users');
			selectRole.find('option').remove();
			selectRole.append("<option value=" + 0 + " >" + "---Select User ---" + "</option>")
			for (i = 0; i < json_.RESPONSE.length; i++) {
			if (document.getElementById('uid') != null && document.getElementById('uid').value == json_.RESPONSE[i].userId) {
								selectRole.append("<option selected value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].username + "</option>");
            				} else {
            					selectRole.append("<option value=" + json_.RESPONSE[i].userId + " >" + json_.RESPONSE[i].username + "</option>")
            				}
			};

		},
		error: function(data) {
			console.log(data)
		}

	});


}


function getData(id) {
    $.ajax({
        type: "GET",
        url: formURL + "/ajax/getTransferRequestData",
        data: {
            "id": id
        },
        success: function(data) {
        console.log(data.RESPONSE);
            var html = "";
            html += "<div class='row' >";

            html += "<div class='col-md-4 form-group' >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Vehicle Number:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.vehicleNo + "</label> </div>";

 html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Bank Name :  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.bankName + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Source Address :  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.sourceAddress + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Destination Address :  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.destAddress + "</label> </div>";


            //html += "<div class='col-md-3 form-group'  >";
            //html += " <img class='img-responsive img-thumbnail' style='width:100px; height:100px;' src='" + window.location.protocol + "//" + window.location.hostname + "/downloadFile/" + data.RESPONSE.vehicleOwnerImageName + "'></img> </div>";

            html += "</div>";


            html += "<div class='row' >";
            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'>  From Date:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.fromDate + "</label> </div>";


            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Thru Date:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.thruDate + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Amount:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.amount + "</label> </div>";


            html += "</div>";



            html += "<div class='row' >";

             html += "<div class='col-md-4 form-group'  >";
           html += " <label class='control-label' style='font-weight: bold; color:red;'> Entered By  </label>";
           html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.enteredBy + "</label> </div>";

            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Mobile Number:  </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.enteredByMobile + "</label> </div>";


            html += "<div class='col-md-4 form-group'  >";
            html += " <label class='control-label' style='font-weight: bold; color:red;'> Role   </label>";
            html += " <label class='control-label' style='font-weight: bold;'>" + data.RESPONSE.role + "</label> </div>";


            html += "</div>";

            html += "<div class='row' >";
            html += "<div class='col-md-12 form-group'  >";
            html += " <h4 class='control-label' style='font-weight: bold;'> View Uploaded Documents  </h4>";
            html += "</div>";

             html += "<div class='col-md-12 form-group'  >";
             html += "<table class='col-lg-12 table table-stripped'>";
             html += "<thead>";
             html += "<tr><th>Document Name</th><th>Document Updated On</th><th>View</th></tr></thead>";

             html += "<tbody>";

            for (var i = 0; i < data.RESPONSE.documents.length; i++) {
                var documents = data.RESPONSE.documents[i];
                html += "<tr><td>"+documents.documentName+"</td>";
                const d = new Date(documents.createdDate );
                date = d.getHours() + ":" + d.getMinutes() + ", " + d.toDateString();
                html += "<td>"+date+"</td>";
                html += "<td><a href='"+ window.location.protocol + "//" + window.location.host + "/downloadFile/" +documents.documentName+"'style='text-decoration:none; color:#FFFFFF;'class='btn btn-success' ;>Click to View</a></td></tr>";

            }
             html += "</tbody>";
             html += "</table>";
             html += "</div>";


            $('.modal-body').html(html);

            $('#empModal').modal('show');

        },
        error: function(data) {
            console.log(data)
        }

    });
    }

