angular.module('dashboard', []).controller("DashboardCtrl", function($scope, $state) {

    $scope.instances = [{
    	"id":1,
    	"instanceId":1,
    	"instanceName": "small",
    	"userInstanceName":"u-a-b-1",
    	"operatingSystem":"Windows Server 2003",
    	"status":"ON"
    }, 
    {
    	"id":1,
    	"instanceId":1,
    	"instanceName": "small",
    	"userInstanceName":"u-a-b-1",
    	"operatingSystem":"Windows Server 2003",
    	"status":"ON"
    }];
    
});