var assert = require("assert"),
	openFdaService = require("../../services/open-fda-service"),
	makeRequestMock = require("./makeRequestMock");

describe('open-fda-service', function(){
	beforeEach(function() {
		openFdaService.options.makeRequest = makeRequestMock;
	});
	describe('searchFoodEnforcement', function(){

		it('should build endpoint correctly', function(){

			var searchParameters = {
				searchTerm: "Corn Flakes",
				status: "Ongoing",
				classification: "Class I",
				state: ['"TX"', '"Texas"']
			};

			openFdaService.searchFoodEnforcement(searchParameters, 1, function(error, results) {
				if (error) {
					assert(false);
				}

				var endpoint = results.testResult;

				assert.equal('/food/enforcement.json', endpoint.pathname);
				assert.equal("https:", endpoint.protocol);
				assert.equal("api.fda.gov", endpoint.host);
				assert.equal('Corn+Flakes+AND+status:Ongoing+AND+classification:Class+I+AND+(state:"TX"+state:"Texas")', endpoint.query.search)
			});
		})

		it('should convert pages to skip and limit', function(){

			var searchParameters = {
				searchTerm: "Corn Flakes",
				status: "Ongoing",
				classification: "Class I",
				state: ['"TX"', '"Texas"']
			};

			openFdaService.options.pageSize = 20;

			openFdaService.searchFoodEnforcement(searchParameters, 3, function(error, results) {
				if (error) {
					assert(false);
				}

				var endpoint = results.testResult;

				assert.equal(20, endpoint.query.limit);
				assert.equal(40, endpoint.query.skip);
			});
		})
	});

});