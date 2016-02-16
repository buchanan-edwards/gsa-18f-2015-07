dariApp.service('drugsService', ['$http', function($http) {
    var drugsBaseURL = '/api/drugs?name=';
    var eventsBaseURL = '/api/events?productNdc=';

    this.getDrugInfo = function(name) {
        return $http.get(drugsBaseURL + name);
    };

    this.getDrugAdverseEvents = function(productNdc) {
        return $http.get(eventsBaseURL + productNdc);
    };

}]);