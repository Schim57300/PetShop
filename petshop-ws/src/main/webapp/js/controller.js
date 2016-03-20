		//Controler of my pet list
		myApp.controller('ListDataCtrl', [
		        '$rootScope',                
				'$scope',
				'ListManager',
				'$http',
				'Auth',
				function($rootScope, $scope, ListManager, $http,Auth) {
					ListManager.refreshList($scope, $http);

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
						ListManager.storeNewPet($scope, $http, $scope.newPetName, $scope.newPetCategory);
						$scope.refreshList($scope, $http, $scope.myModel);
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
