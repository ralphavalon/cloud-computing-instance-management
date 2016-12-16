angular.module('user_instance', ['instance.service', 'operating_system.service', 'user_instance.service']).controller("UserInstanceCtrl",
		
		function($scope, $state, $filter, InstanceService, OperatingSystemService, UserInstanceService) {
	
			$scope.userInstance = {
				"status": "OFF",
				"userId": 1
			};

			$scope.instances = InstanceService.getInstances();
			
			$scope.operatingSystems = OperatingSystemService.getOperatingSystems();
			
			$scope.create = function () {
				if($scope.userInstance.status === true) {
					$scope.userInstance.status = 'ON';
				}
				UserInstanceService.saveUserInstance($scope.userInstance);
			};
			
			
			
		});