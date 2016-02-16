
//Developers : Anush Varma
//File : Protractor Spec File
//Version : 1.0.0
//Date : 06/24/2015


/*global
 beforeEach: false,
 browser: false,
 by: false,
 describe: false,
 expect: false,
 it: false,
 protractor: false
 */

describe('FDA Demo Integration Tests: Test Suite 1 - Home Page', function () {
    beforeEach(function () {
	return browser.ignoreSynchronization = true;
    });

    it('Verify navigation to Home Page', function () {
	browser.get('');
	browser.driver.sleep(3000);
	var agreeButton = browser.findElement(by.css('.form-group a'));
	expect(agreeButton.isDisplayed()).toBe(true);
	agreeButton.click();
    });
});


describe('FDA Demo Integration Tests: Test Suite 2 - Map View', function () {
   
    beforeEach(function () {
	return browser.ignoreSynchronization = false;	
    });

   it('Verify navigation to Map View', function () {
        browser.get('search/#/');
	var natFoodNo = browser.findElement(by.binding('nationalFoodNumbers'));
	natFoodNo.getText().then(function (text) {
    		console.log('nationalFoodNumbers = '+ text);
		expect(text).toNotBe(null);
	});

	var natDrugNo = browser.findElement(by.binding('nationalDrugNumbers'));
	natDrugNo.getText().then(function (text) {
    		console.log('nationalDrugNumbers = '+ text);
		expect(text).toNotBe(null);
	});

	var natDeviceNo = browser.findElement(by.binding('nationalDeviceNumbers'));
	natDeviceNo.getText().then(function (text) {
    		console.log('nationalDeviceNumbers = '+ text);
		expect(text).toNotBe(null);
	});
    });


});

describe('FDA Demo Integration Tests: Test Suite 3 - List View', function () {
   
    beforeEach(function () {
        browser.get('search/#/listSearch');
	browser.waitForAngular();
    });


    it('Verify navigation to List View', function () {
        var element = browser.findElement(by.model('searchCriteria.keyTerm'));
        expect(element.getText()).toBe('');

        browser.findElements(by.repeater('y in searchCriteria.states')).then(function(states) {
	    var noOfStates = states.length;
	    console.log('Number of States=' + noOfStates);
	    expect(noOfStates).toBeGreaterThan(0);
   
	});
        
    });

    it('Verify Required Search Criteria', function () {
        var startDate = browser.findElement(by.model('searchCriteria.startDate'));
        expect(startDate.getText()).toNotBe(null);
	var endDate = browser.findElement(by.model('searchCriteria.endDate'));
        expect(endDate.getText()).toNotBe(null);

        browser.findElements(by.repeater('y in searchCriteria.states')).then(function(states) {
	    var noOfStates = states.length;
	    console.log('Number of States=' + noOfStates);
	    expect(noOfStates).toBeGreaterThan(0);
	});
        
    });

    it('Verify Default Search Results', function () {
      	browser.findElements(by.repeater('y in products.results')).then(function(results) {
	    var noOfResults = results.length;
	    console.log('Number of Results=' + noOfResults);
	    expect(noOfResults).toBeGreaterThan(0);
	});
    });
});