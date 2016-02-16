/**
*   Javascript module used to hold the javascript modules for our application
*
*   The startup method runs before the rest of the application loads, it is used to load config information
*
*   @author Acumen Solutions, Inc.
*/


(function() {
    'use strict';
    angular
        .module('app', [
            'app.core',
            'app.filters'
        ])
        .run(startup);

    startup.$inject = ['envConfig'];

    function startup (envConfig) {
    	envConfig.setupConfig();
    }
})();