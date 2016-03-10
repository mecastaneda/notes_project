angular.module("loginModule", ['ngMaterial', 'ngMessages', 'authSrv'])
.controller("loginCtrl", function($scope, $mdDialog, $location, baseUrl, auth, $rootScope) {
	
	$scope.showProgress = false;
	
	$scope.resetPassword = function(ev) {
		console.log("reset");
		$mdDialog.show({
			controller: resetCtrl,
			templateUrl: "resources/login/partialViews/resetPassword.html",
			parent: angular.element(document.body),
			targetEvent: ev,
			clickOutsideToClose:true
		});
	};
	
	$scope.newUser = function() {
		console.log("new user");
	};
	
	$scope.authenticate = function() {
		$scope.showProgress = true;
		console.log("name", $scope.user.name);
		console.log("password", $scope.user.password);
		
		auth.login($scope.user)
			.then(function(data) {
				console.log("Success!");
				$rootScope.user = data;
				$location.path("notes").replace();
			}, function() {				
				$scope.loginForm.password.$setValidity();
				$scope.loginForm.password.$setPristine();
				$scope.user.password = "";
				$scope.badAuth = true;
				$scope.showProgress = false;
			});
	};
	
	function resetCtrl($scope) {
		console.log("reset ctroller");
	}
	
});