angular.module('user_instance.service', ['ngResource']).service("UserInstanceService",
		
		function($resource) {
	
			var UserInstanceResource = $resource('http://localhost:8080/userInstances/:id', null, {
	            'getByUser': {
	            	method: 'GET', 
	            	params: { id: '@id' }, 
	            	url: 'http://localhost:8080/userInstances/user/:id', 
	            	isArray: true
	            	},
	            'save': {
	            	method:'POST',
	            	isArray: true
	            },
	            'update': {
	            	method: 'PATCH',
	            	params: { id: '@id' }
	            }
	        });
	
			var getUserInstances = function () {
				return UserInstanceResource.query();
			}
			
			var getUserInstance = function (userInstanceId) {
				return UserInstanceResource.get({ id: userInstanceId});
			}
			
			var getUserInstanceByUser = function (userId) {
				return UserInstanceResource.getByUser({ id: userId});
			}
			
			var saveUserInstance = function (userInstance) {
				return UserInstanceResource.save(userInstance);
			}
			
			var updateUserInstance = function (userInstanceId, status) {
				return UserInstanceResource.update({ id: userInstanceId } , { status: status });
			}
			
			 return {
				 getUserInstances: getUserInstances,
				 getUserInstance: getUserInstance,
				 getUserInstanceByUser: getUserInstanceByUser,
				 saveUserInstance: saveUserInstance,
				 updateUserInstance: updateUserInstance 
		        }
			
		});