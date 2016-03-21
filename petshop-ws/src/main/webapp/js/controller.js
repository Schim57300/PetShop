		//Controler of my pet list
		myApp.controller('ListDataCtrl', [
		        '$rootScope',                
				'$scope',
				'ListManager',
				'$http',
				'Auth',
				function($rootScope, $scope, ListManager, $http,Auth) {
					ListManager.refreshList($scope, $http);
					//Initialization of filter placeholder
					$scope.somePlaceholder = 'Find a pet by its identifier';
					var speciesList = {"categories":[{"id":1,"name":"Dog"},{"id":2,"name":"Cat"},{"id":3,"name":"Lizard"},{"id":4,"name":"Fish"}]};
					var tagsList = {"tags":[{"id":1,"name":"tag1"},{"id":2,"name":"tag2"},{"id":3,"name":"tag3"},{"id":4,"name":"tag4"}]};
					$scope.categories = speciesList.categories;
					$scope.tags = tagsList.tags;

					$scope.refreshList = function() {
						console.log("Filter=" + $scope.myModel + ".");
						ListManager.refreshList($scope, $http, $scope.myModel);
					}
					
					$scope.deleteSelectedPet = function(idToRemove) {
						console.log("Delete:" + idToRemove + ".");
						ListManager.deletePet($scope, $http, idToRemove);
						$scope.refreshList($scope, $http, $scope.myModel);
					}

					$scope.storeNewPet = function() {
						console.log("New=" + $scope.newPetName + "/"+ $scope.newPetCategory + ".");
						ListManager.storeNewPet($scope, $http, $scope.newPetName, $scope.newPetCategory,$scope.newPetPhoto,$scope.newPetTags);
						$scope.refreshList($scope, $http, $scope.myModel);
						angular.copy({},$scope.storingPetForm);
					}
					
				} ]);

		myApp.controller('LoginCtrl', ['$rootScope','$scope', '$http', 'Auth', 
	         function($rootScope, $scope, $http, Auth) {
	     
				$scope.submitLogin = function() {
					console.log('SubmitLogin');
					Auth.login($scope, $http);
			    };
		
		}]);

		myApp.controller('UserCtrl', ['$rootScope', '$scope', function($rootScope, $scope) {
			$scope.logout = function() {
				console.log('Logout');
				$scope.connectedUser.name = "Visitor";
				$scope.connectedUser.profile = 0;
				$scope.userLogin=null;
				$scope.userPwd=null;
		    };
		} ]);
