angular.module('dashboard', ['user_instance.service']).controller("DashboardCtrl",
		
		function($scope, $state, $filter, UserInstanceService, $rootScope) {

			$scope.userInstances = UserInstanceService.getUserInstanceByUser($rootScope.userId);
			
			$scope.statusIs = function(status) {
				return function(instance) {
					return instance.status === status;
				};
			};
			
			$scope.instances = [];
			
			$scope.userInstances.$promise.then(function (result) {
	                forEachPedido(result, [ function(userInstance) { 
	                	$scope.instances.push(userInstance);
	                	}]);
	                filterStatus();
	            });
			
			var forEachPedido = function (result, callbacks) {
	            angular.forEach(result, function (object) {
	                for (var i = 0; i < callbacks.length; i++) {
	                    var callback = callbacks[i];
	                    callback(object);
	                }
	            });
	        }
			
			var filterStatus = function () {
				$scope.on = $scope.instances.filter($scope.statusIs('ON'));
				$scope.off = $scope.instances.filter($scope.statusIs('OFF'));
			}
			

		});