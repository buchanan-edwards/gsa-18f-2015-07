describe('formatResultDate.filter', function() {
	var filter;

	beforeEach(function() {
		module('app', function($provide) {
			specHelper.fakeRouteProvider($provide);
			specHelper.fakeConfig($provide);
		});
		specHelper.injector(function($filter){});
	});

	beforeEach(function() {
		filter = $filter('formatResultDate');
	});

	describe('formatResultDate', function() {
		it('should format the given date into something displayable', function() {
			expect(filter('20130717')).to.equal('2013-07-17');
		});
	});
});