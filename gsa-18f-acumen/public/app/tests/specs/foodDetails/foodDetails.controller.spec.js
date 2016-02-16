describe('foodDetails.controller', function() {
	var controller;

	beforeEach(function() {
		module('app', function($provide) {
			specHelper.fakeRouteProvider($provide);
			specHelper.fakeConfig($provide);
		});
		specHelper.injector(function($controller, $rootScope, resultDataStoreService){});
	});

	beforeEach(function() {
		controller = $controller('FoodDetailsCtrl');
		$rootScope.$apply();
	});

	describe('food details controller', function() {
		it('should be created successfully', function() {
			expect(controller).to.be.defined;
		});

		describe('getClassName', function() {
			describe('given valid classification', function() {
				it('should return Dangerous or Defective', function() {
					expect(controller.getClassName('Class I')).to.equal('Dangerous or Defective');
					expect(controller.getClassName('Class II')).to.equal('Threat or Sickness');
					expect(controller.getClassName('Class III')).to.equal('Labeling or Legal');
				});
			});

			describe('given anything else', function() {
				it('should return undefined', function() {
					expect(controller.getClassName('something')).to.be.undefined;
				});
			});
		});

		describe('getClassDescription', function() {
			describe('given valid classification', function() {
				it('should return proper description', function() {
					expect(controller.getClassDescription('Class I')).to.equal('A dangerous or defective product that predictably could cause serious health problems or death.');
					expect(controller.getClassDescription('Class II')).to.equal('This product might cause a temporary health problem, or pose only a slight threat of a serious nature.');
					expect(controller.getClassDescription('Class III')).to.equal('This product is unlikely to cause any adverse health reaction, but violates FDA labeling or manufacturing laws.');
				});
			});

			describe('given anything else', function() {
				it('should return undefined', function() {
					expect(controller.getClassDescription('something')).to.be.undefined;
				});
			});
		});

		describe('getClassStyle', function() {
			describe('given valid classification', function() {
				it('should return proper styling', function() {
					expect(controller.getClassStyle('Class I')).to.equal('bk-clr-one');
					expect(controller.getClassStyle('Class II')).to.equal('bk-clr-two');
					expect(controller.getClassStyle('Class III')).to.equal('bk-clr-three');
				});
			});

			describe('given anything else', function() {
				it('should return undefined', function() {
					expect(controller.getClassStyle('something')).to.be.undefined;
				});
			});
		});
	});
});