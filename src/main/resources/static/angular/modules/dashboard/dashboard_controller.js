angular.module('dashboard', []).controller("DashboardCtrl",
		
		function($scope, $state, $filter) {

			$scope.instances = [ {
				"id" : 1,
				"instanceId" : 1,
				"instanceName" : "small",
				"userInstanceName" : "u-a-b-1",
				"operatingSystem" : "Windows Server 2003",
				"status" : "ON"
			}, {
				"id" : 1,
				"instanceId" : 1,
				"instanceName" : "small",
				"userInstanceName" : "u-a-b-1",
				"operatingSystem" : "Windows Server 2003",
				"status" : "OFF"
			} ];

			$scope.statusIs = function(status) {
				return function(instance) {
					console.log('ITEM: ' + status);
					console.log('instance: ' + instance);
					return instance.status === status;
				};
			};
			
			$scope.on = $scope.instances.filter($scope.statusIs('ON'));
			$scope.off = $scope.instances.filter($scope.statusIs('OFF'));

		});