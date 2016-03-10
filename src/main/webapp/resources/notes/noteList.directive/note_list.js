angular.module('notesModule')
	.controller('note_list.ctrl', ['$scope', '$rootScope', function($scope, $rootScope) {
		
		$scope.notes = $rootScope.user.notes;
		
		$scope.viewNote = function(event, note) {
			$scope.$emit('openNote', note);
		};
		
		$scope.$on('deleteNote', function(evt, note) {
			for(var i = $scope.notes.length-1; i >= 0; i--) {
				if($scope.notes[i].noteId == note.noteId) {
					$scope.notes.splice(i, 1);
					break;
				}
			}
		});
		
	}])
	.directive('noteList', function() {
		return {
			restrict: 'AE',
			templateUrl: 'resources/notes/noteList.directive/note_list.html'
		}
	});