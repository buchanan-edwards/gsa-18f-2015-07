/**
*   Filter used to format dates so that they appear user friendly
*   Ex: Given 20130717, Returns 2013-07-17
*
*   @author Acumen Solutions, Inc.
*/

(function() {
    'use strict';
    angular
        .module('app.filters')
        .filter('formatResultDate', formatResultDate);

    function formatResultDate() {
        return formatResultDateFilter;
        ////////////////
        function formatResultDateFilter(resultDate) {

            if (!resultDate) {
                return '';
            }

            var dateNums = resultDate.split('');
            dateNums.splice(4, 0, '-');
            dateNums.splice(7, 0, '-');
            return dateNums.join('');
        }
    }
})();