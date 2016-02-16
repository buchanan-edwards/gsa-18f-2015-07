describe('dataservice', function() {
	beforeEach(function() {
		module('app', function($provide) {
			specHelper.fakeRouteProvider($provide);
			specHelper.fakeConfig($provide);
		});
		specHelper.injector(function($httpBackend, dataservice, envConfig){});
	});

	describe('dataservice', function() {
		it('should be defined', function() {
			expect(dataservice).to.be.defined;
		});

		describe('searchForRecalls', function() {
			it('should exist', function() {
				expect(dataservice.searchForRecalls).to.be.defined;
			});
		});
	});
});