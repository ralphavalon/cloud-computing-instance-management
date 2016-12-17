var app = app || {};

app = angular.module('cloudComputing', [ 'login', 'dashboard', 'user_instance', 'user', 'instance.service', 'ngResource', 'datatables', 'ui.router', 'jcs-autoValidate' ])

.config(function($stateProvider, $urlRouterProvider, $httpProvider, $resourceProvider) {

	$resourceProvider.defaults.stripTrailingSlashes = false;
	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
	$httpProvider.defaults.headers.common['Accept'] = 'application/json';
	$httpProvider.defaults.headers.common['token'] = 'nyFxZblv-2aX-JiDFGyskDdWJolcgJUez6an7B2BSQitQOzwCPm4gMlnO0Hkjho1';
	$httpProvider.defaults.headers.common['usercode'] = 'c86b4192-ad28-4d2d-8690-7ad109eaaaf5';

	$stateProvider.state('login', {
		url : '/',
		templateUrl : '/pages/login.html',
		controller : 'LoginCtrl'
	})
	
	.state('dashboard', {
		url : '/dashboard',
		templateUrl : '/pages/dashboard.html',
		controller : 'DashboardCtrl'
	})
	
	.state('user_instance_new', {
		url : '/user_instance_new',
		templateUrl : '/pages/user_instance_new.html',
		controller : 'UserInstanceCtrl'
	})
	
	.state('user_instance_get', {
		url : '/user_instance/:id',
		templateUrl : '/pages/user_instance_get.html',
		controller : 'UserInstanceCtrl'
	})
	
	.state('user_profile', {
		url : '/user_profile',
		templateUrl : '/pages/user_profile.html',
		controller : 'UserCtrl'
	})
	
	.state('sign_up', {
		url : '/sign_up',
		templateUrl : '/pages/user_new.html',
		controller : 'UserCtrl'
	})
	
	.state('logout', {
		url : '/logout',
		templateUrl : '/pages/login.html',
		controller : 'LoginCtrl'
	})
	

	$urlRouterProvider.otherwise('/');

});

app.run([ '$rootScope', '$state', '$stateParams',
		function($rootScope, $state, $stateParams) {
			$rootScope.$state = $state;
			$rootScope.$stateParams = $stateParams;
			$rootScope.isUnloggedState = function() {
				var unloggedStates = [ 'login', 'user_create' ];
				return unloggedStates.indexOf($state.current.name) >= 0;
			};
		} ]);