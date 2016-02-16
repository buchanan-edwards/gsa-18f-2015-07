/**
*   This service is used to store search terms and result sets in memory to preserve application state
*
*   @author Acumen Solutions, Inc.
*/


(function() {

	angular.module('app')
		.service('resultDataStoreService', resultDataStoreService);

	function resultDataStoreService() {

		var selectedItem;
		var resultSet;
		var lastViewedPage;
		var searchParams;

		this.storeResultSet = function(_resultSet) {
			resultSet = _resultSet;
		};

		this.storeSelectedItem = function(_selectedItem) {
			selectedItem = _selectedItem;
		};

		this.storeLastViewedPage = function(_lastViewedPage) {
			lastViewedPage = _lastViewedPage;
		};

		this.storeSearchParams = function(_searchParams) {
			searchParams = _searchParams;
		};

		this.getResultSet = function() {
			return resultSet;
		};

		this.getSelectedItem = function() {
			return selectedItem;
		};

		this.getLastViewedPage = function() {
			return lastViewedPage;
		};

		this.getSearchParams = function() {
			return searchParams;
		};
	}

})();