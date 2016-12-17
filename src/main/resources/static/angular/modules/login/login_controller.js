angular.module('login', []).controller("LoginCtrl", function($scope, $rootScope, $state, $http) {

	if($state.is('logout')) {
		delete($http.defaults.headers.common['token']);
        delete($http.defaults.headers.common['usercode']);
        $rootScope.userId = null;
    	$state.go('login');
    }
	
	var successCallback = function (response) {
        var token = response.data['token'];
        var usercode = response.data['externalId'];
        var userId = response.data['userId'];

        $http.defaults.headers.common['token'] = token;
        $http.defaults.headers.common['usercode'] = usercode;
        $rootScope.userId = userId;

        $rootScope.logged = true;
        $state.go('dashboard');
    };
	
	$scope.loginData = {};
	
	var failCallback = function() {
		alert('Login failed. Check your credentials');
	}
	
    $scope.login = function() {
    	$http.post("http://localhost:8080/auth/", $scope.loginData).then(
    	        successCallback, failCallback
        );
    }
    
});