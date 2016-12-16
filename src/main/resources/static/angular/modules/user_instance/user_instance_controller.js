angular.module('user_instance', ['instance.service']).controller("UserInstanceCtrl",
		
		function($scope, $state, $filter, InstanceService) {
	
			$scope.userInstance = {
				"status": "OFF"
			};

			$scope.instances = InstanceService.getInstances();
			
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