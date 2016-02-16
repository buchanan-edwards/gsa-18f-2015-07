
var makeRequestMock = function(endpoint, resultCallback) {

	return resultCallback(null, {
		testResult : endpoint
	});
};

module.exports = makeRequestMock;