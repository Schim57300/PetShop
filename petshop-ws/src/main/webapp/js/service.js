myApp.service('Auth', function() {
    var user = window.user;
    
    this.login = function($scope, $http) {
		console.log("About to connect user for " + $scope.userLogin + "/" + $scope.userPwd+".");
		$http({
			method : 'POST',
			url : '//192.168.0.27:9000/login/',
			data: JSON.stringify({"userLogin" : $scope.userLogin, "userPassword" : $scope.userPwd}),
			headers: {'Content-Type': 'application/json'}
		}).success(function(receivedUser) {
			$scope.connectedUser.name = receivedUser.userName;
			$scope.connectedUser.profile = receivedUser.profile;
            $scope.adminAuth=true;
            console.log('after : connectedUser.name=='+$scope.connectedUser.name);
            console.log('after : connectedUser.profile" =='+$scope.connectedUser.profile);
        })
        .error(function(data) {
            $scope.loginError = data.loginError;
        });
	};
    
});

//Call my web service with a parameter
myApp.service('ListManager', function($http) {
	this.refreshList = function($scope, $http, petId) {
		//In case there is no index, search all pets
		console.log("petId=="+petId+".");
		if (angular.isUndefined(petId) || petId == null || petId == "") {
			petId = '*';
		}
		$http({
			method : 'GET',
			url : '//192.168.0.27:9000/pet/' + petId
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
			url : '//192.168.0.27:9000/pet/' + petId
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
		//$http.defaults.headers.post["Content-Type"] = "X-Requested-With, Content-Type";
		
		$http({
			method : 'POST',
			url : '//192.168.0.27:9000/pet/',
			//data: { 'name' : petName, "category" : petCategory },
			data: JSON.stringify({"list":[{ 'id' : 0, 'name' : petName, "category" : petCategory, "status" : "AVAILABLE" }]}),
			headers: {'Content-Type': 'application/json'}
		}).success(function(data, status, headers, config) {
			console.log('Insert done ');
			//TODO : set a delay between DELETE and REFRESH_LIST
		}).error(function(data, status, headers, config) {
			// Error raised
		});
	};
});
