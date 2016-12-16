angular.module('user_instance', []).controller("UserInstanceCtrl",
		
		function($scope, $state, $filter) {
	
			$scope.userInstance = {
				"status": "OFF"
			};

			$scope.instances = [ {
				"id" : 1,
				"name": "small",
				"planId" : 1,
				"planName" : "Basic",
				"cpu" : 1,
				"memory" : 2,
				"storage" : 40,
				"storageType" : "HD"
			}, {
				"id" : 2,
				"name": "medium",
				"planId" : 1,
				"planName" : "Basic",
				"cpu" : 1,
				"memory" : 4,
				"storage" : 250,
				"storageType" : "HD"
			} ];
			
			$scope.operatingSystems = [ {
				"id" : 1,
				"name": "Windows Server 2003"
			}, {
				"id" : 2,
				"name": "Ubuntu 12.04"
			} ];
			
			$scope.create = function () {
				if($scope.userInstance.status === true) {
					$scope.userInstance.status = 'ON';
				}
			};
			
		});