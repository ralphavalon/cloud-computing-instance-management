angular.module('user', ['user.service']).controller("UserCtrl",
		
		function($scope, $state, $rootScope, UserService) {
			
			var creditCardRegex = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/;
			
			$scope.isValidCreditCard = function (creditCard) {
				return creditCardRegex.test($scope.user.creditCard);
			};
		
			var newUser = function () {
				$scope.user = {};
		
				$scope.createUser = function () {
					UserService.saveUser($scope.user);
					alert("User created");
					$state.go('login');
				};
			}
	
			$scope.userId = $rootScope.userId;
			
			if( $scope.userId != null && typeof $scope.userId !== 'undefined') {
				$scope.user = UserService.getUser($scope.userId);
				$scope.status_active = $scope.user.status == true ? false : true;
				
				$scope.updateUser = function () {
					
					if($scope.status_active == null || typeof $scope.status_active === 'undefined') {
						$scope.status_active = false;
					}
					if(! $scope.isValidCreditCard($scope.user.creditCard)) {
						alert('Invalid credit card');
						$state.go('dashboard');
					}
					$scope.userToUpdate = {
								username: $scope.user.username, 
								email: $scope.user.email, 
								status: $scope.status_active, 
								creditCard: $scope.user.creditCard
					};
					UserService.updateUser( $scope.user.id, $scope.userToUpdate);
					if($scope.user.status === false) {
						$state.go('logout');
					} else {
						$state.go('dashboard');
					}
				};
				
			} else {
				newUser();
			}
			
			
		});