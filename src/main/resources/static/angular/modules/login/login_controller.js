angular.module('login', []).controller("LoginCtrl", function($scope) {

    console.log('to aqui');
    $scope.print = function() {
    	alert('oi');
    	console.log('oi');
    }
    
});