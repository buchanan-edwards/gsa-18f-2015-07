describe('truncateText.filter', function() {
	var filter;

	beforeEach(function() {
		module('app', function($provide) {
			specHelper.fakeRouteProvider($provide);
			specHelper.fakeConfig($provide);
		});
		specHelper.injector(function($filter){});
	});

	beforeEach(function() {
		filter = $filter('truncate');
	});

	describe('truncate', function() {
		describe('given text length less than allowed length', function() {
			it('should return the full text', function() {
				expect(filter('some text', 10)).to.equal('some text');
			});
		});

		describe('given text length greater than allowed length', function() {
			it('should return truncated text', function() {
				expect(filter('some text', 5)).to.equal('so...');
			});
		});

		describe('given text length greater than allowed length and given optional end', function() {
			it('should return truncated text with optional ending character', function() {
				expect(filter('some text', 5, '!')).to.equal('some!');
			});
		});
	});
});