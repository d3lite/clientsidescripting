
app.controller('personListController', function ($scope, $http) {

    function getPersonList() {
        // the code to list all persons.
        $scope.listMsg = "";
        $http.get("webAPIs/getUserListAPI.jsp").then(
                function (response) { // this function will run if http.get success
                    console.log("Get Person ajax success");
                    console.log(response);
                    console.log("");
                    $scope.persons = response.data.recordList;
                    $scope.listMsg = response.data.dbError;
                    
                    for (var i = 0; i<$scope.persons.length; i++){
                        $scope.persons[i].personIdNum = Number($scope.persons[i].person_id);
                    }
                    
                    if ($scope.listMsg.length > 0) {
                        $scope.listMsg = "List Error: " + $scope.listMsg;
                    }
                    //check if user is logged in
                    if(response.data.loggedOn === "true"){
                        $scope.isLoggedOn = true;
                    } else {
                        $scope.isLoggedOn = false;
                    }
                    
                },
                function (response) { // this function will run if http.get error
                    console.log("Get Person ajax error");
                    console.log(response);
                    console.log("");
                    $scope.listMsg = "List Error: " + response.status + " " + response.statusText;
                } // end of error fn
        ); // closes off "then" function call
    } // list
    
    getPersonList();

}); // end of def'n of the controller function 