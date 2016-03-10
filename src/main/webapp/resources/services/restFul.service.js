angular.module('noteRest', ['ngResource']).
config(['$resourceProvider', function($resourceProvider) {
	$resourceProvider.defaults.stripTrailingSlashes = false;
}]).
factory('Notes', function($resource, baseUrl) {
	return $resource(baseUrl + 'api/notes');
})