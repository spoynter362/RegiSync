/*
 * Angular module and controller for the create customer page and features.
 * 
 */
var regisync = angular.module('regisync', []);

regisync.controller('regisyncCtrl', function($scope, $http, $location) {	
	
	$scope.companyName = 'RegiSync';	
	$scope.navName = 'RegiSync';	
	$scope.hideRegistrySearchResults = true;
	$scope.hideRegistrySearchPage = false;
	$scope.hideRegistryUpdatePage = true;
	$scope.hideGiftsSearchResults = true;
	$scope.hideGiftsAnalyticsResults = true;
	
	$scope.registriesResource = '/RestDemo/regisync/registries/';
	$scope.giftsResource = '/RestDemo/regisync/gifts/';
	$scope.analyticsResource = '/RestDemo/regisync/analytics/';
	
	$scope.registrySearch = function() {		
		$scope.hideRegistrySearchResults = true;
		$scope.hideRegistrySearchPage = false;
		$scope.hideRegistryUpdatePage = true;
		$scope.hideGiftsSearchResults = true;
		
	};
	
	
	$scope.goToUpdateRegistry = function(selectedRegistry) {
		$scope.registry = selectedRegistry;
		$scope.hideRegistrySearchPage = true;
		$scope.hideRegistryUpdatePage = false;	
		
	};
	
//	$scope.goToUpdateGifts = function(gifts) {
//		
//		$scope.hideRegistrySearchPage = true;
//		$scope.hideRegistryUpdatePage = false;	
//		$scope.hideGiftsSearchResults = false;
//	};	
	
	$scope.amazonUpdate = function() {
		var data = {name: $scope.registry.firstName + " " + $scope.registry.lastName, registryId:$scope.registry.registryId};
		var config = {params: data};
		$http.get($scope.giftsResource + "amazon", config).then(function(response){
			$scope.amazonGifts = response.data;
			
		});
		
	}
	
	$scope.getAnalytics = function(selectedRegistry){
		$scope.registry = selectedRegistry;
		$scope.hideGiftsAnalyticsResults = false;
		$scope.hideGiftsSearchResults = true;
	
	$http.get($scope.analyticsResource + $scope.registry.registryId).then(function(response) {
		$scope.myAnalytics = response.data;	

	});
	}
	
	$scope.targetUpdate = function() {
		var data = {name: $scope.registry.firstName + " " + $scope.registry.lastName, registryId:$scope.registry.registryId};
		var config = {params: data};
		$http.get($scope.giftsResource + "target", config).then(function(response){
			$scope.targetGifts = response.data;
			
		});
	}
	
	$scope.getAllGiftsByRegistry = function(registryId) {
		
//		$scope.myGifts = [{name: "blah", price: "19.99", sku: "Ankjasd6fj"}, {name: "blue", price: "99.99", sku: "ADGASDF555"}]
		
		
		$http.get($scope.giftsResource + registryId).then(function(response) {
			$scope.myGifts = response.data;	
			$scope.hideRegistrySearchPage = true;
			$scope.hideRegistrySearchPage = true;
			$scope.hideRegistryUpdatePage = false;	
			$scope.hideGiftsSearchResults = false;
		});
	};
	
	$scope.putRegistry = function(){
		
		$scope.jsonObject = angular.toJson($scope.registry, false);
		alert("time to update, registry json: "+$scope.jsonObject);
		
		$http.put($scope.registriesResource, $scope.jsonObject)
		.then(
				function success(response) {
					$scope.registry = response.data;
					alert("registry updated: " + $scope.registry.registryId + ", status: " + response.status);
				},
				function error(response){
					alert("error, return status: " + response.status)
				}
		);	
	}
	
	
$scope.putGift = function(){
		
		$scope.jsonObject = angular.toJson($scope.gift, false);
		alert("time to update, gift json: "+$scope.jsonObject);
		
		$http.put($scope.giftsResource, $scope.jsonObject)
		.then(
				function success(response) {
					$scope.gift = response.data;
					alert("gift updated: " + $scope.gift.sku + ", status: " + response.status);
				},
				function error(response){
					alert("error, return status: " + response.status)
				}
		);	
	}
	
	
	
	
	
	$scope.deleteRegistry = function(registryId){
alert("time to delete, registry json: "+registryId);
		
		$http.delete($scope.registriesResource + registryId)
		.then(
				function success(response) {
					$scope.rowCount = response.data;
					alert("registry deleted: " + $scope.rowCount + ", status: " + response.status);
				},
				function error(response){
					alert("error, return status: " + response.status)
				}
		);	
	};
	
	
	$scope.deleteGift = function(sku){
		alert("time to delete, gift json: "+ sku);
				
				$http.delete($scope.giftsResource + sku)
				.then(
						function success(response) {
							$scope.rowCount = response.data;
							alert("gift deleted: " + $scope.rowCount + ", status: " + response.status);
						},
						function error(response){
							alert("error, return status: " + response.status)
						}
				);	
			};
	
	
	$scope.getAllRegistries = function() {
		$scope.hideRegistrySearchResults = false;
		
		$http.get($scope.registriesResource).then(function(response) {
			$scope.myRegistries = response.data;	
		});
	}
	
	$scope.getAllGifts = function() {
		$scope.hideRegistrySearchResults = true;
		
		$http.get($scope.giftsResource).then(function(response) {
			$scope.myGifts = response.data;	
		});
	}
	
	$scope.ContactUs = function() {		
		alert("Developer has been alerted about your issue! Thank you for reaching out.");
	}
	
	
	
$scope.clearCreate = function() {	
		
		//clear success or error message
		$scope.createStatus = "";
		
		//clear the input elements
		$scope.firstName = "";
		$scope.lastName = "";
		$scope.email = "";
				
		//clear the messages		
		$scope.firstNameMessage = "";
		$scope.lastNameMessage = "";
		$scope.emailMessage = "";
		
		//unlock input
		$scope.lock = false;
	}



$scope.createRegistry = function() {	
	
	if ($scope.newRegistryFirstName == undefined) {
		$scope.firstNameMessage = "First name is required";
	} else if ($scope.newRegistryFirstName.length < 2) {
		$scope.firstNameMessage = "First name must be at least 2 characters";
	} else {
		$scope.firstNameMessage = "";
	}
	
	if ($scope.newRegistryLastName == undefined) {
		$scope.lastNameMessage = "Last name is required";
	} else if ($scope.newRegistryLastName.length < 2) {
		$scope.lastNameMessage = "Last name must be at least 2 characters";
	} else {
		$scope.lastNameMessage = "";
	}
	
	if ($scope.newRegistryEmail == undefined || $scope.newRegistryEmail == "") {
		$scope.emailMessage = "";
		$scope.newRegistryEmail = "";
	} else if ($scope.newRegistryEmail.length < 6) {
		$scope.emailMessage = "email must be at least 6 characters";
	} else {
		$scope.emailMessage = "";
	}		
	
	if ($scope.newRegistryAmazonUrl == undefined || $scope.newRegistryAmazonUrl == "") {
		$scope.amazonMessage = "";
		$scope.newRegistryAmazonUrl = "";
	} else if ($scope.newRegistryAmazonUrl.length < 6) {
		$scope.amazonMessage = "amazon URL must be at least 6 characters";
	} else {
		$scope.amazonMessage = "";
	}	
	
	if ($scope.firstNameMessage == "" && $scope.lastNameMessage == "" && $scope.emailMessage == "" && $scope.amazonMessage == "") 
	{
					
		alert("time to create a registry " +
				", first name: " + $scope.newRegistryFirstName +
				", last name: " + $scope.newRegistryLastName + 					
				", email: " + $scope.newRegistryEmail +
				", amazon URL: " + $scope.newRegistryAmazonUrl);					
		
		var newRegistry = { firstName : $scope.newRegistryFirstName,
							lastName : $scope.newRegistryLastName,
							email: $scope.newRegistryEmail,
							amazonUrl: $scope.newRegistryAmazonUrl};
		
		$http.post("/RestDemo/regisync/registries", newRegistry)
		.then(
				function success(response) {						
					
				},
				function error(response) {
					alert("error, return status: " + response.status);
					$scope.createStatus = "error. press 'Clear' to try again";						
				}				
		);
		
		$scope.isCreateRegistryDisabled = true;
		$scope.lock = true;
		$scope.createStatus = "";
		
	} else {
		$scope.createStatus = "please fix indicated errors";
	}				
		
};
	
});	//controller method ends here,