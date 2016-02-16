describe('search.controller', function() {
	var controller;

	beforeEach(function() {
		module('app', function($provide) {
			specHelper.fakeRouteProvider($provide);
			specHelper.fakeConfig($provide);
		});
		specHelper.injector(function($controller, $q, $rootScope, dataservice, envConfig, resultDataStoreService){});
	});

	beforeEach(function() {

		sinon.stub(resultDataStoreService, 'getResultSet', function() {
			return mockData.getMockRecalls();
		});

		sinon.stub(resultDataStoreService, 'storeResultSet', function() {
			// console.log('in store');
		});

		controller = $controller('SearchCtrl');
		$rootScope.$apply();
	});

	describe('search controller', function() {
		it('should be created successfully', function() {
			expect(controller).to.be.defined;
		});

		describe('on search', function() {
			describe('good data returned', function() {
				beforeEach(function() {
					sinon.stub(dataservice, 'searchForRecalls', function() {
						var deferred = $q.defer();
						deferred.resolve(mockData.getMockSearchServiceCall());
						return deferred.promise;
					});

					sinon.stub(controller, 'setPaging', function() {});

					controller.search();
				});

				it('should call searchForRecalls with correct params', function() {
					expect(dataservice.searchForRecalls).to.have.been.calledWith(controller.lastSearchParams, controller.pagination.currentPage);
				});

				it('should set searchResults', function() {
					expect(controller.searchResults.meta.results.total).to.equal(1173);
				});

				// FAILING
				it('should store results in data store', function() {
					$rootScope.$digest();
					expect(resultDataStoreService.storeResultSet).to.have.been.called;
				});

				// FAILING
				it('should set paging', function() {
					$rootScope.$digest();
					expect(controller.setPaging).to.have.been.called;
				});
			});

			describe('on error', function() {
				describe('if known error', function() {
					beforeEach(function() {
						sinon.stub(dataservice, 'searchForRecalls', function() {
							var deferred = $q.defer();
							deferred.resolve(mockData.getMockSearchServiceCallError());
							return deferred.promise;
						});

						controller.search();
					});

					it('should set search results to empty list', function() {
						$rootScope.$digest();
						expect(controller.searchResults.results).to.be.empty;
					});
				});
			});
		});

		describe('on searchClicked', function() {
			beforeEach(function() {
				sinon.stub(resultDataStoreService, 'storeSearchParams', function(params) {});

				sinon.stub(controller, 'search', function() {});

				controller.searchClicked();
			});

			it('should store search params in data store', function() {
				expect(resultDataStoreService.storeSearchParams).to.have.been.calledWith(controller.searchParams);
			});

			it('should call search()', function() {
				expect(controller.search).to.have.been.called;
			});

			it('should reset current page to page 1', function(){
				expect(controller.pagination.currentPage).to.equal(1);
			});
		});

		describe('on setPaging', function() {
			it('should set the total pages correctly', function() {
				controller.setPaging();

				expect(controller.pagination.totalPages).to.equal(118);
			});
		});

		describe('on pageChange', function() {
			beforeEach(function() {
				sinon.stub(resultDataStoreService, 'storeLastViewedPage', function(params) {});

				sinon.stub(controller, 'search', function() {});

				controller.pageChanged();
			});

			it('should store the current page in the data store', function() {
				expect(resultDataStoreService.storeLastViewedPage).to.have.been.calledWith(controller.pagination.currentPage);
			});

			it('should call search()', function() {
				expect(controller.search).to.have.been.called;
			});
		});
	});

	specHelper.verifyNoOutstandingHttpRequests();
});