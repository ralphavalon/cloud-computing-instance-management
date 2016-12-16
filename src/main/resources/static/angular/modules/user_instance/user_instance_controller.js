angular.module('user_instance', ['instance.service', 'operating_system.service']).controller("UserInstanceCtrl",
		
		function($scope, $state, $filter, InstanceService, OperatingSystemService) {
	
			$scope.userInstance = {
				"status": "OFF"
			};

			$scope.instances = InstanceService.getInstances();
			
			$scope.operatingSystems = OperatingSystemService.getOperatingSystems();
			
			$scope.create = function () {
				if($scope.userInstance.status === true) {
					$scope.userInstance.status = 'ON';
				}
			};
			
		});