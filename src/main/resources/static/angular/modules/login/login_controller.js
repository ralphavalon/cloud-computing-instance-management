angular.module('login', []).controller("LoginCtrl", function($scope) {

	$scope.loginData = {};
	
    $scope.login = function() {
    	console.log($scope.loginData);
    }
    
});