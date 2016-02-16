/*
  author: anurodh agarwal
  description: utility to search data for list/detail view of the application
  version:0.0.1
  Date:06/24/2015
*/

searchApp.controller('ListSearchController', function($scope, $http, ospConstants, $filter, $location, sharedProperties, $anchorScroll) {

    // function to create a date from moment date
    $scope.formatDate = function(date){
          var dateOut = new Date(date);
          return dateOut;
    };

    $scope.opts = {ranges: ospConstants.ranges};
    $scope.searchCriteria = sharedProperties.getGlobalSearchCriteria();
    $scope.availableRecall = [{name: 'Food', code: 'food'}, {name: 'Device', code: 'device'},{name: 'Drug', code: 'drug'}];
    $scope.searchCriteria.startDate = $filter('date')($scope.formatDate($scope.searchCriteria.startDate), 'MM/dd/yyyy');
    $scope.searchCriteria.endDate = $filter('date')($scope.formatDate($scope.searchCriteria.endDate), 'MM/dd/yyyy');
    $scope.availableStates = stateList;
    $scope.products = sharedProperties.getProductsList();
    $scope.stateCount = sharedProperties.getStateCount();
    if($scope.stateCount == null) {
        $scope.stateCount = 0;
    }
    $scope.resetStateCount = function() {
        sharedProperties.setStateCount('');
        $scope.stateCount = '';
    }

    // function to call the search service for selected search criteria
    $scope.searchData = function() {
        var finalStateList = '';
        var keyTerm = $scope.searchCriteria.keyTerm;
        var from_date = $filter('date')($scope.formatDate($scope.searchCriteria.startDate), 'yyyy-MM-dd');
        var to_date = $filter('date')($scope.formatDate($scope.searchCriteria.endDate), 'yyyy-MM-dd');
        for (var i = 0; i <= $scope.searchCriteria.states.length - 1; i++) {
            finalStateList =  finalStateList + '&locations=' + $scope.searchCriteria.states[i].code + '&locations=' + $scope.searchCriteria.states[i].name;
        };
        var recallType = '';
        for (var i = 0; i <= $scope.searchCriteria.selectedRecall.length - 1; i++) {
            if(i!=0) {
                recallType =  recallType + '&';
            }
            recallType =  recallType + 'product_type=' + $scope.searchCriteria.selectedRecall[i].code;
        };
        console.log("/recallInfoMapView?"+ recallType + finalStateList + "&key_term=" + keyTerm + "&daterange=["+from_date+ "+TO+"+to_date+"]");
        if(recallType != '' && finalStateList!='') {
            $http.get("/recallInfoMapView?"+ recallType + finalStateList + "&key_term=" + keyTerm + "&daterange=["+from_date+ "+TO+"+to_date+"]")
            .success(function(response) {
                $scope.products = response;
                sharedProperties.setProductsList($scope.products);
            });
        } else {
            $scope.products = '';
            sharedProperties.setProductsList('');
        }
        sharedProperties.setGlobalSearchCriteria($scope.searchCriteria);
    };

    // function to create a date from format '20150827'
    $scope.createDate = function(dateString) {
        return new Date(dateString.slice(0,4), dateString.slice(4,6)-1, dateString.slice(6,8));
    }

    // function to redirect to recall details page
    $scope.showDetails = function (y) {
        sharedProperties.setRecallDetails(y);
        sharedProperties.setReloadData(false);
        $location.path('/detailsPage');
    };
    $scope.recallDetails = sharedProperties.getRecallDetails();
    $anchorScroll('page2');
    if(sharedProperties.getReloadData() == true) {
        sharedProperties.setReloadData(false);
        $scope.searchData();
    }
});

searchApp.controller('DetailsController', function($scope, sharedProperties, $location, $anchorScroll) {
    $scope.recallDetails = sharedProperties.getRecallDetails();
    $anchorScroll('page2');
});