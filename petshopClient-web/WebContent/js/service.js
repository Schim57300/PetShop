		//Call my webservice with a parameter
		myApp.service('ListManager', function($http) {
			this.refreshList = function($scope, $http, petId) {
				//In case there is no index, search all pets
				if (angular.isUndefined(petId) || petId == "") {
					petId = '*';
				}
				$http({
					method : 'GET',
					url : '//localhost:9000/pet/' + petId
				}).success(function(data, status, headers, config) {
					console.log(data.list);
					$scope.pets = data.list;

				}).error(function(data, status, headers, config) {
					// Error raised
				});
			};

			this.deletePet = function($scope, $http, petId) {
				console.log("About to delete pet #" + petId + ".");
				//In case there is no index, search all pets
				if (angular.isUndefined(petId) || petId == "") {
					console.log('No petId');
					return;
				}
				$http({
					method : 'DELETE',
					url : '//localhost:9000/pet/' + petId
				}).success(function(data, status, headers, config) {
					console.log('Deletion done for #' + petId);
					//TODO : set a delay between DELETE and REFRESH_LIST
				}).error(function(data, status, headers, config) {
					// Error raised
				});
			};
			this.storeNewPet = function($scope, $http, petName, petCategory) {
				console.log("About to add pet #" + petName+ "/"+petCategory+".");
				//In case there is no index, search all pets
				if (angular.isUndefined(petName) || angular.isUndefined(petCategory)) {
					console.log('Invalid data');
					return;
				}
				$http.defaults.headers.post["Content-Type"] = "x-requested-with";
				
				$http({
					method : 'POST',
					url : '//localhost:9000/pet/',
					data: { 'name' : petName, "category" : petCategory }
				}).success(function(data, status, headers, config) {
					console.log('Insert done ');
					//TODO : set a delay between DELETE and REFRESH_LIST
				}).error(function(data, status, headers, config) {
					// Error raised
				});
			};
		});
