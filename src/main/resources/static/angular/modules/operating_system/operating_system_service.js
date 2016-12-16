angular.module('operating_system.service', ['ngResource']).service("OperatingSystemService",
		
		function($resource) {
	
			var OperatingSystemResource = $resource('http://localhost:8080/operatingSystems/:id');
	
			var getOperatingSystems = function () {
				return OperatingSystemResource.query();
			}
			
			var getOperatingSystem = function (OperatingSystemId) {
				return OperatingSystemResource.get({ id: OperatingSystemId});
			}
			
			 return {
				 getOperatingSystems: getOperatingSystems,
				 getOperatingSystem: getOperatingSystem
		        }
			
		});