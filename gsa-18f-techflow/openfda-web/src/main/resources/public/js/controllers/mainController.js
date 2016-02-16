dariApp.controller('mainCtrl', ['$scope', '$q', 'drugsService', 'smoothScroll', '$timeout', function($scope, $q, drugsService, smoothScroll, $timeout) {
    $scope.events = [];
    $scope.totalEvents;
    $scope.query = '';
    $scope.searchStr = '';
    $scope.result = '';
    $scope.infoVisibility = false;
    $scope.loadingVisibility = false;
    $scope.readMore = false;
    $scope.searchBarVisibility = true;
    $scope.scroll = false;
    $scope.didInitialLoad = false;
    $scope.headers = {
        indicationsAndUsage: "Indication and Usage",
        brandName: "Brand Name",
        genericName: "Generic Name",
        purpose: "Purpose",
        active: "Active Ingredients",
        adverseReactions: "Adverse Reactions",
        askDoctor: "Ask Doctor",
        doNotUse: "Do Not Use",
        dosage: "Dosage",
        inactive: "Inactive Ingredients",
        warnings: "Warnings",
        askDoctorOrPharmacist: "Ask Doctor or Pharmacist",
        stopUse: "Stop Use",
        manufacturerName: "Manufacturer",
        events: "Adverse Reaction",
        warningsAndCautions: "Warnings and Cautions"
    };

    $scope.eventLabels = {
        'hospitalization': 'Hospitalizations',
        'congenitalAnomali': 'Congenital Anomalies',
        'disabling': 'Disabling',
        'lifeThreatening': 'Life Threatening',
        'death': 'Deaths',
        'other': 'Other'
    };

    $scope.labelReadMore = {
        indicationsAndUsage: false,
        brandName: false,
        genericName: false,
        purpose: false,
        active: false,
        adverseReactions: false,
        askDoctor: false,
        doNotUse: false,
        dosage: false,
        inactive: false,
        warnings: false,
        askDoctorOrPharmacist: false,
        stopUse: false,
        manufacturerName: false,
        events: false,
        warningsAndCautions: false
    };

    function enableReadMore() {
        for (var elem in $scope.labelReadMore) {
            var height = $("#labeling-" + elem + "-label").height();
            //height within the div elements
            var availableHeight = 90;
            $scope.labelReadMore[elem] = height > availableHeight;
        }
    }

    $scope.searchDrug = function() {
        $scope.queryFailedMsg = '';
        $scope.searchBarVisibility = false;
        if ($scope.didInitialLoad) {
            $("#bs-example-navbar-collapse-1").collapse('hide');
            $scope.loadOverlay = true;
        } else {
            $scope.loading = true;
        }
        drugsService.getDrugInfo($scope.query)
            .then(
                function success1(response) {
                    transformResponse(response.data);
                    return drugsService.getDrugAdverseEvents(response.data['productNdc']);
                },
                function error1(response) {
                    return $q.reject(response);
                }
            )
            .then(
                function success2(response) {
                    $scope.totalEvents = response.data.total;
                    $scope.events = transformTo2DArray(response.data);
                    $scope.infoVisibility = true;
                    $scope.scroll = true;
                    $scope.loading = false;
                    $scope.searchBarVisibility = true;
                    $timeout(function() {
                        plotAdverse('adversePlot', $scope.events);
                        enableReadMore();
                    });
                    $scope.loadOverlay = false;
                },
                function error2(response) {
                    requestErrorHandler(response.status);
                }
            );
        $scope.scroll = false;
        $scope.didInitialLoad = true;
    };


    function requestErrorHandler(status) {
        $scope.infoVisibility = false;
        $scope.loading = false;
        $scope.loadOverlay = false;
        $scope.searchBarVisibility = true;
        switch (status) {
            case 404:
                $scope.queryFailedMsg = "Drug not found";
                break;
            default:
                $scope.queryFailedMsg = "There was an unknown error. Please try again later.";
                break;
        }
    }

    function transformResponse(response) {
        var result = {};
        var labelInfo = {};
        var warnings = {};
        for (var key in response) {
            if ($scope.headers[key]) {
                if (key == 'brandName')
                    result['name'] = response[key];
                else if (key == 'purpose')
                    result['purpose'] = response[key];
                else if (key === 'warnings' || key === 'doNotUse' || key === 'askDoctor' || key == 'askDoctorOrPharmacist' || key == 'warningsAndCautions') {
                    if (response[key] !== null) {
                        warnings[key] = response[key];
                    }
                } else if (response[key] !== null && key !== 'notFound')
                    labelInfo[key] = response[key];
            }
        }
        result['labelInfo'] = labelInfo;
        result['warnings'] = warnings;
        $scope.result = result;
    }

    function transformTo2DArray(obj) {
        var array = [];
        for (var key in $scope.eventLabels) {
            var data = [];
            data.push(key);
            data.push(obj[key]);
            array.push(data);
        }
        return array;
    }

    $scope.formatSuggestionResponse = function(responseData) {
        var formattedData = {
            suggestions: []
        };
        for (var i = 0; i < responseData.suggestions.length; i++) {
            formattedData.suggestions.push({
                "name": responseData.suggestions[i]
            });
        }
        return formattedData;
    };
}]);
