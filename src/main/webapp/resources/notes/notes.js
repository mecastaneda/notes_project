angular.module('notesModule', ['ngMaterial', 'authSrv'])
.config(function($mdIconProvider) {
	  $mdIconProvider
	    .iconSet('notes:more', 'resources/icons/ic_more_horiz_24px.svg', 24)
	    .iconSet('notes:settings', 'resources/icons/ic_settings_24px.svg', 24)
	    .iconSet('notes:info', 'resources/icons/ic_info_outline_24px.svg', 24)
	    .iconSet('notes:logout', 'resources/icons/ic_exit_to_app_24px.svg', 24)
})
.controller('noteCtrl', function($scope, $rootScope, auth) {
	console.log('running note ctrl');
	if(!auth.isLoggedIn())
		return auth.logout();
	
	$scope.$on('openNote', function(evt, note) {
		console.log('I heard open note id', note.noteId);
		$scope.$broadcast('_openNote', note);
	});
	
	$scope.logout = function(evt) {
		console.log('logout clicked');
		auth.logout();
	};
	
	$scope.openMenu = function($mdOpenMenu, ev) {
		originatorEv = ev;
		$mdOpenMenu(ev);
	};
	
	$scope.saveNewNote = function(note) {
		return auth.addUpdateNote(note).then(function(_note) {
			if(typeof note.noteId == 'undefined')
				$rootScope.user.notes.push(_note);
		});
	};
	
	$scope.doDeleteNote = function(note, callback) {
		var cb = callback || angular.noop;
		auth.deleteNote(note).then(function(data) {
			$scope.$broadcast('deleteNote', note);
			cb(true);
		}, function(err) {
			console.log("Som Ting Wong", err);
			cb(false);
		});
	}
	
});