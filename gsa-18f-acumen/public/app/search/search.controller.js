/**
*   Controller for the search view
*
*   @author Acumen Solutions, Inc.
*/

(function() {
    'use strict';
    angular
        .module('app')
        .controller('SearchCtrl', SearchCtrl);
    SearchCtrl.$inject = ['dataservice', 'envConfig', 'resultDataStoreService'];
    /* @ngInject */
    function SearchCtrl(ds, envConfig, resultDataStoreService) {
        var vm = this;
        var lastSearchParams;
        vm.title = 'SearchCtrl';

        // props
        vm.pageLoading = false;
        vm.isCollapsed = false;
        vm.showCategoryHelp = false;

        vm.searchParams = resultDataStoreService.getSearchParams();

        if (vm.searchParams) {
            lastSearchParams = angular.copy(vm.searchParams);
        } else {
            vm.searchParams = {
                searchTerm: '',
                status: '',
                classification: '',
                state: ''
            };
        }

        vm.stateList = envConfig.recallLookups.stateLookups;
        vm.statusList = envConfig.recallLookups.statusLookups;
        vm.classificationList = envConfig.recallLookups.classificationLookups;

        vm.pagination = {
            currentPage: resultDataStoreService.getLastViewedPage() || 1,
            maxPageDisplay: 5,
            totalPages: 0,
            totalItems: 0
        };

        vm.searchResults = resultDataStoreService.getResultSet();
        if (!vm.searchResults) {
            vm.searchResults= null;
        } else {
            setPaging();
        }

        // functions
        vm.search = search;
        vm.searchClicked = searchClicked;
        vm.setPaging = setPaging;
        vm.setSelectedFoodItem = resultDataStoreService.storeSelectedItem;
        vm.pageChanged = pageChanged;

        activate();
        ////////////////
        function activate() {
    		// console.log('in search controller!  doing any necessary statup logic...');
        }

        function search () {
            vm.pageLoading = true;
            return ds.searchForRecalls(lastSearchParams, vm.pagination.currentPage)
                .then(function(data) {
                    // console.log(data);
                    if (data.data.hasOwnProperty("error")) { // assume this just means no results found for now
                        handleErrors(data.data);
                    } else {
                        vm.searchResults = data.data;
                        vm.setPaging();
                        vm.isCollapsed = true;
                    }
                    resultDataStoreService.storeResultSet(vm.searchResults);
                    vm.pageLoading = false;
                    return vm.searchResults;
                });

                function handleErrors(data) {
                    if(data.error.code && envConfig.knownApiErrorCodes[data.error.code]) {
                        vm.searchResults = {
                            results: []
                        };
                    } else {
                        console.log('AHHHH!  ERROR!:  ' + data.error);
                    }
                }
        }

        function searchClicked() {
            // clone so page change searches on last params instead of updates to fields
            // (made without clicking search button)
            resultDataStoreService.storeSearchParams(vm.searchParams);
            lastSearchParams = angular.copy(vm.searchParams);
            vm.pagination.currentPage = 1;
            vm.search();
        }

        function setPaging() {
            var pagingInfo = vm.searchResults.meta.results;
            vm.pagination.totalPages = Math.ceil(pagingInfo.total / pagingInfo.limit);
            vm.pagination.totalItems = pagingInfo.total;
        }

        function pageChanged() {
            resultDataStoreService.storeLastViewedPage(vm.pagination.currentPage);
            vm.search();
        }
    }
})();