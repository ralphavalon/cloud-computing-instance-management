angular.module('user_instance', ['instance.service', 'operating_system.service', 'user_instance.service']).controller("UserInstanceCtrl",
		
		function($scope, $state, $filter, $rootScope, InstanceService, OperatingSystemService, UserInstanceService) {
		
			var newUserInstance = function () {
				$scope.userInstance = {
					"status": "OFF",
					"userId": $rootScope.userId
				};
		
				$scope.instances = InstanceService.getInstances();
				
				$scope.operatingSystems = OperatingSystemService.getOperatingSystems();
				
				$scope.create = function () {
					if($scope.userInstance.status === true) {
						$scope.userInstance.status = 'ON';
					}
					UserInstanceService.saveUserInstance($scope.userInstance);
					$state.go('dashboard');
				};
			}
	
			$scope.userInstanceId = $state.params.id;
			
			if( $scope.userInstanceId != null && typeof $scope.userInstanceId !== 'undefined') {
				$scope.userInstance = UserInstanceService.getUserInstance($scope.userInstanceId);
				
				$scope.update = function () {
					UserInstanceService.updateUserInstance($scope.userInstanceId, $scope.userInstance.status);
					$state.go('dashboard');
				};
				
			} else {
				newUserInstance();
			}
			
			
		});