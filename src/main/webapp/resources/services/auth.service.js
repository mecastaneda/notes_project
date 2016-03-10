angular.module('authSrv', ['ngResource', 'ngCookies', 'noteRest'])
.factory("auth", function($resource, $http, $cookies, $q, $location, $rootScope, Notes) {
	//var rest = $resource('/api/:')
	/**
	 * Authenticate user
	 * @param {Object} user		- {user, password}
	 * @param  {Function} callback - optional
	 * @return {Promise}
	 */
	return {
		login: function(user, callback) {
			var cb = callback || angular.noop;
			var deferred = $q.defer();
			
			$http.post('/notes-project/auth', {
				name: user.name,
				password: user.password
			})
			.success(function(data) {
				console.log('data:',data);
				console.log("Trying to store cookie: ", data.token);
				$cookies.put('token', data.token);
				console.log('Stored: ', $cookies.get("token") );
				deferred.resolve(data);
				return cb(data);
			})
			.error(function(err) {
				console.log('Error with login:', err);
				deferred.reject(err);
				return cb(err);
			}.bind(this));
			
			return deferred.promise;
		},
		
		logout: function() {
			$cookies.remove("token");
			$rootScope.user = {};
			$location.path("login").replace();
		},
		
		checkToken: function(token, callback) {
			
			console.log('Running checkToken, token:', token);
			
			var cb = callback || angular.noop;
			var deferred = $q.defer();
			
			$http.post('/notes-project/checkToken')
			.success(function(data) {
				console.log('data:',data);
				deferred.resolve(data);
				return cb(data);
			})
			.error(function(err) {
				console.log('Error with token:', err);
				deferred.reject(err);
				return cb(err);
			}.bind(this));
			
			return deferred.promise;
		},
		
		isLoggedIn: function() {
			if($cookies.get("token")) 
				return true;
			return false;
		},
		
		addUpdateNote: function(note) {
			return Notes.save(note).$promise;
		},
		
		deleteNote: function(note) {
			return Notes.remove({noteId: note.noteId}).$promise;
		}	
		
	};
});