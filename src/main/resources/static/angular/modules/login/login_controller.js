angular.module('login', []).controller("LoginCtrl", function($scope, $state) {

	if($state.is('logout')) {
    	$state.go('login');
    }
	
	$scope.loginData = {};
	
    $scope.login = function() {
    	$state.go('dashboard');
    }
    
});