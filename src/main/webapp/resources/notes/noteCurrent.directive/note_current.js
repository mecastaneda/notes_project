angular.module('notesModule')
	.controller('note_current.ctrl', ['$scope', '$mdDialog', function($scope, $mdDialog) {
		
		$scope.currentNote = {title: ""};
		
		$scope.$on('_openNote', function(evt, note) {
			//TODO alert before saving
			$scope.currentNote = note;
		});
		
		$scope.updateSaveNote = function(ev, note) {
			var saveNote = $scope.saveNewNote;
			$mdDialog.show({
				controller: function($scope) {
					$scope.saved = false;
					saveNote(note)
						.then(function() {
						}, function() {
							$scope.error = true;
						}).finally( function() {
							$scope.saved = true;
							setTimeout(function() {
								$mdDialog.hide();
							}, 1666);
						});
				},
				template: '<p ng-hide="!saved || error">The Note was Saved</p>' + 
					'<p ng-show="error" style="color:red">Could Not Save Note</p>' +
					'<md-progress-circular ng-hide="saved" style="margin-left:70px;" md-mode="indeterminate"></md-progress-circular>',
				parent: angular.element(document.body),
				targetEvent: ev,
				clickOutsideToClose: false,
		    })
		    .then(function(answer) {
		      $scope.status = 'You said the information was "' + answer + '".';
		    }, function() {
		      $scope.status = 'You cancelled the dialog.';
		    });
			
		};
		
		$scope.deleteNote = function(ev, note) {
			console.log('delteing');
			var confirm = $mdDialog.confirm()
				.title('Are you sure you want to detele the note: ' + note.title)
				//.textContent('All of the banks have agreed to forgive you your debts.')
				.ariaLabel('Lucky day')
				.targetEvent(ev)
				.ok('Delete')
				.cancel('Cancel');
			$mdDialog.show(confirm).then(function() {
				$scope.doDeleteNote(note, function(success) {
					if(success)
						$scope.currentNote = {title: ""};
				});
			}, function() {
				
			});
		};
		
		$scope.newNote = function() {
			//TODO alert before saving
			$scope.currentNote = {title: ""};
		};
		
		$scope.getInfo = function(ev, note) {
			
			var content1 = "";
			var content2 = "";

			if(typeof note.noteId == 'undefined') {
				content1 = "This note has not been saved.";
			} else {
				content1 = "Created date: " + new Date(note.created).toDateString();
				content2 = "Modified date: " + new Date(note.modified).toDateString();
			}
			$mdDialog.show(
				$mdDialog.alert()
				    .parent(angular.element(document.querySelector('#detailBtn')))
					.clickOutsideToClose(true)
					.title('Details of:' + note.title)
					.content(content1 + "<br /><br />" + content2)
					.ariaLabel('Note details.')
					.ok('Close').targetEvent(ev)
			);
		};
		
		
	}])
	.directive('noteCurrent', function() {
		return {
			restrict: 'AE',
			templateUrl: 'resources/notes/noteCurrent.directive/note_current.html'
		}
	});