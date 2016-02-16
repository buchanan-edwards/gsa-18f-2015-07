/**
*   Router for the food based endpoints.
*
*   @author Acumen Solutions, Inc.
*/

var express = require('express');
var router = express.Router();

var openFdaService = require('../services/open-fda-service');
openFdaService.setApiKey(process.env.OpenFdaApiKey);

var stateAbbreviationMap = {
	"AL" : "Alabama",
	"AK" : "Alaska",
	"AZ" : "Arizona",
	"AR" : "Arkansas",
	"CA" : "California",
	"CO" : "Colorado",
	"CT" : "Connecticut",
	"DE" : "Delaware",
	"FL" : "Florida",
	"GA" : "Georgia",
	"HI" : "Hawaii",
	"ID" : "Idaho",
	"IL" : "Illinois",
	"IN" : "Indiana",
	"IA" : "Iowa",
	"KS" : "Kansas",
	"KY" : "Kentucky",
	"LA" : "Louisiana",
	"ME" : "Maine",
	"MD" : "Maryland",
	"MA" : "Massachusetts",
	"MI" : "Michigan",
	"MN" : "Minnesota",
	"MS" : "Mississippi",
	"MO" : "Missouri",
	"MT" : "Montana",
	"NE" : "Nebraska",
	"NV" : "Nevada",
	"NH" : "New Hampshire",
	"NJ" : "New Jersey",
	"NM" : "New Mexico",
	"NY" : "New York",
	"NC" : "North Carolina",
	"ND" : "North Dakota",
	"OH" : "Ohio",
	"OK" : "Oklahoma",
	"OR" : "Oregon",
	"PA" : "Pennsylvania",
	"RI" : "Rhode Island",
	"SC" : "South Carolina",
	"SD" : "South Dakota",
	"TN" : "Tennessee",
	"TX" : "Texas",
	"UT" : "Utah",
	"VT" : "Vermont",
	"VA" : "Virginia",
	"WA" : "Washington",
	"WV" : "West Virginia",
	"WI" : "Wisconsin",
	"WY" : "Wyoming",
	"AS" : "American Samoa",
	"DC" : "District of Columbia",
	"FM" : "Federated States of Micronesia",
	"GU" : "Guam",
	"MH" : "Marshall Islands",
	"MP" : "Northern Mariana Islands",
	"PW" : "Palau",
	"PR" : "Puerto Rico",
	"VI" : "Virgin Islands"
};

router.get('/search', function(req, res, next) {
	var searchParameters = {
		searchTerm: req.query["searchTerm"],
		status: req.query["status"] || "Ongoing",
		classification: req.query["classification"]
	};

	var state = req.query["state"];

	if (!state) {

		return res.status(400).json({
			"error" : {
				"code" : "BAD_REQUEST",
				"message" : "State must be selected to perform a search"
			}
		});

	} else {

		searchParameters.distribution_pattern = [
			'"' + state + '"',
			'"' + stateAbbreviationMap[state] + '"'
			];

	}

	var page  = req.query["page"] || 1;
	var pageSize = req.query["pagesize"];

	if (pageSize) {
		openFdaService.options.pageSize = pageSize;
	}

	openFdaService.searchFoodEnforcement(searchParameters, page, function(error, results) {
		if (error) {
			// TODO : CAN PROBABLY GET A BETTER STATUS CODE BY MODIFYING THE FOOD SERVICE
			return res.status(500).json({
				"error" : {
					"message" : error
				}
			});
		}

		return res.status(200).json(results);
	});
});

module.exports = router;