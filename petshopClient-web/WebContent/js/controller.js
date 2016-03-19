		//Controler of my pet list
		myApp.controller('ListDataCtrl', [
				'$scope',
				'ListManager',
				'$http',
				function($scope, ListManager, $http) {
					ListManager.refreshList($scope, $http);

					$scope.deleteSelectedPet = function(idToRemove) {
						console.log("Delete:" + idToRemove + ".");
						ListManager.deletePet($scope, $http, idToRemove);
						console.log("Refreshing list with filter set to #"
								+ $scope.myModel + ".");
						$scope.refreshList($scope, $http, $scope.myModel);
					}

					$scope.refreshList = function() {
						console.log("Filter=" + $scope.myModel + ".");
						ListManager.refreshList($scope, $http, $scope.myModel);
					}
					
					$scope.storeNewPet = function() {
						console.log("new=" + $scope.newPetName + "/"+ $scope.newPetCategory + ".");
						ListManager.storeNewPet($scope, $http, $scope.newPetName, $scope.newPetCategory);
					}
					
				} ]);

		//FOR TEST
		myApp.controller('UserCtrl', [ '$scope', function($scope) {

			$scope.user = {};
			$scope.user.details = {
				"username" : "Eric Kam",
				"id" : "89101112"
			};

		} ]);

