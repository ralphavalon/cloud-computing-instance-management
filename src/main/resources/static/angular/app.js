var app = app || {};

app = angular.module('cloudComputing', [ 'login', 'dashboard', 'datatables', 'ui.router', 'jcs-autoValidate' ])

.config(function($stateProvider, $urlRouterProvider, $httpProvider) {

	$httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
	$httpProvider.defaults.headers.common['Accept'] = 'application/json';

	$stateProvider.state('login', {
		url : '/',
		templateUrl : '/pages/login.html',
		controller : 'LoginCtrl'
	})
	
	.state('dashboard', {
		url : '/dashboard',
		templateUrl : '/pages/index.html',
		controller : 'DashboardCtrl'
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