/**
*   Service for communicating with the OpenFDA API
*   Provides methods for getting data from the API and returning nicely formatted data.
*
*   @author Acumen Solutions, Inc.
*/
var request = require("request");
var url = require("url");

var openFdaService = {};

var makeRequest = function(endpoint, resultCallback) {
	var path = url.format(endpoint).replace(/%2B/g, '+');

	request.get(path, function(error, response, body) {
		if (error) {
			resultCallback(error, null);
			return;
		}

		resultCallback(null, JSON.parse(body));
	});
};

openFdaService.options = {
	pageSize: 10,
	makeRequest: makeRequest
};

var buildEndpoint = function(pathName, query) {
	var endpoint = new Object();
	endpoint.protocol = "https:";
	endpoint.host = "api.fda.gov";
	endpoint.pathname = pathName;
	endpoint.query = query;

	if (!endpoint.query.hasOwnProperty("api_key") && openFdaService.apiKey) {
		endpoint.query.api_key = openFdaService.apiKey;
	}

	return endpoint;
};

var formatFieldValue = function(fieldValue) {
	return fieldValue.trim().replace(/ /g, "+");
};

var buildSearchQuery = function(searchParameters) {
	var searchQueryParts = [];
	for (var field in searchParameters) {

		if (!searchParameters[field]) {
			continue;
		}

		var rawFieldValue = searchParameters[field];

		if (typeof rawFieldValue === "string") {

			var fieldValue = formatFieldValue(rawFieldValue);
			if (field === "searchTerm") {
				searchQueryParts.push(fieldValue);
			} else {
				searchQueryParts.push( field + ":" + fieldValue );
			}

		} else {	// assume array
			var fieldQueryParts = [];
			for (var i = 0, len = rawFieldValue.length; i < len; i++) {
				if (!rawFieldValue[i]) {
					continue;
				}
				fieldQueryParts.push( field + ":" + formatFieldValue(rawFieldValue[i]) );
			}
			searchQueryParts.push( '(' + fieldQueryParts.join('+') + ')' );
		}

		searchQueryParts.push("AND");
	}

	if (searchQueryParts.length > 1) {
		searchQueryParts.splice(searchQueryParts.length - 1, 1);
	}

	return searchQueryParts.join("+");
};

openFdaService.searchFoodEnforcement = function(searchParameters, page, resultCallback) {
	var endpoint = buildEndpoint("/food/enforcement.json", {
		"search": buildSearchQuery(searchParameters),
		"limit": openFdaService.options.pageSize,
		"skip": (page - 1) * openFdaService.options.pageSize
		});

	openFdaService.options.makeRequest(endpoint, resultCallback);
};

openFdaService.getDrugLabel = function(drugId, resultCallback) {
	var endpoint = buildEndpoint("/drug/label.json", {
		"search": "id:" + drugId
		});

	openFdaService.options.makeRequest(endpoint, resultCallback);
};

openFdaService.getDrugEvents = function(drugId, resultCallback) {
	var endpoint = buildEndpoint("/drug/event.json", {
		"search": "spl_id:" + drugId
	});

	openFdaService.options.makeRequest(endpoint, resultCallback);
};

openFdaService.searchLabels = function(searchTerm, page, resultCallback) {
	var endpoint = buildEndpoint("/drug/label.json", {
		"search": '"' + searchTerm + '"',
		"limit": openFdaService.options.pageSize,
		"skip": (page - 1) * openFdaService.options.pageSize
	});

	openFdaService.options.makeRequest(endpoint, resultCallback);
};

openFdaService.setApiKey = function(key){
  openFdaService.apiKey = key;
};

openFdaService.apiKey = null;

module.exports = openFdaService;