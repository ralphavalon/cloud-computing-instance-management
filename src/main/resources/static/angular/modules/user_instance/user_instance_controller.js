angular.module('user_instance', ['instance.service', 'operating_system.service', 'user_instance.service']).controller("UserInstanceCtrl",
		
		function($scope, $state, $filter, InstanceService, OperatingSystemService, UserInstanceService) {
		
			$scope.userInstanceId = $state.params.id;
			
			if( $scope.userInstanceId != null && typeof $scope.userInstanceId !== 'undefined') {
				$scope.userInstance = UserInstanceService.getUserInstance($scope.userInstanceId);
//				$scope.userInstance.status = $scope.userInstance.status == 'ON' ? true : false;
				
				$scope.update = function () {
//					$scope.userInstance.status = $scope.userInstance.status == true ? 'ON' : 'OFF'; 
					UserInstanceService.updateUserInstance($scope.userInstanceId, $scope.userInstance.status);
				};
				
			} else {
				newUserInstance();
			}
			
			var newUserInstance = function () {
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
			}
			
		});