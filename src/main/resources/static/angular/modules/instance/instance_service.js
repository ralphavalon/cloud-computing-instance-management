angular.module('instance.service', ['ngResource']).service("InstanceService",
		
		function($resource) {
	
			var InstanceResource = $resource('http://localhost:8080/instances/:id');
	
			var getInstances = function () {
				return InstanceResource.query();
			}
			
			var getInstance = function (instanceId) {
				return InstanceResource.get({ id: instanceId});
			}
			
			 return {
				 getInstances: getInstances,
				 getInstance: getInstance
		        }
			
		});