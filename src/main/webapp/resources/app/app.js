(function() {
    'use strict';
    
    angular.module('app', [ 'filters', 'common-controllers', 'app.usecase' ])
    //
    .constant("apiBasePath",
            angular.element("meta[name='contextPath']").attr('content') + '/api/v1')
    //
    .config([ '$httpProvider', function($httpProvider) {
        var csrfHeaderName = angular.element("meta[name='csrfHeaderName']").attr('content');
        var csrfToken = angular.element("meta[name='csrfToken']").attr('content');
        $httpProvider.defaults.headers.common[csrfHeaderName] = csrfToken;
    } ]);

})();
