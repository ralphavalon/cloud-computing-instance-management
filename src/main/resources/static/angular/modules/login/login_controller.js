angular.module('login', []).controller("LoginCtrl", function($scope, $state) {

	$scope.loginData = {};
	
    $scope.login = function() {
    	$state.go('dashboard');
    }
    
});