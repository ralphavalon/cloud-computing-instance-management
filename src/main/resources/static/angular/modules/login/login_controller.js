angular.module('login', []).controller("LoginCtrl", function($scope, $state) {

	$scope.loginData = {};
	
    $scope.login = function() {
    	console.log($scope.loginData);
    	$state.go('dashboard');
    }
    
    $scope.$on('$viewContentLoaded', function() {
    	$('#instances').dataTable();
    });
    
});