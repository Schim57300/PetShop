var myApp = angular.module('myApp', [])
.run(function($rootScope) {
    $rootScope.connectedUser =  {'name': 'visitor', 'profile': 0}; //Default profile is VISITOR
});
