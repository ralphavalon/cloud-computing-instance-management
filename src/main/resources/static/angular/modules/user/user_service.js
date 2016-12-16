angular.module('user.service', ['ngResource']).service("UserService",
		
		function($resource) {
	
			var UserResource = $resource('http://localhost:8080/users/:id', null, {
	            'update': {
	            	method: 'PUT',
	            	params: { id: '@id' }
	            }
	        });
	
			var getUser = function (userId) {
				return UserResource.get({ id: userId});
			}
			
			var saveUser = function (user) {
				return UserResource.save(user);
			}
			
			var updateUser = function (userId, user) {
				return UserResource.update({ id: userId } , user);
			}
			
			 return {
				 getUser: getUser,
				 saveUser: saveUser,
				 updateUser: updateUser 
		        }
			
		});