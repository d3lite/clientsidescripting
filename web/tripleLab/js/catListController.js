
app.controller('catListController', function ($scope, $http,$routeParams) {

        function deleteCat(id) {
            var url = "webAPIs/deleteOtherAPI.jsp?id=" + id;
            console.log("url to invoke: " + url);
            $scope.deleteMsg = "";

            $http.get(url).then(
                    function (response) { // this function will run if http.get success
                        console.log("Cat Delete ajax success");
                        console.log(response);
                        console.log("");
                        $scope.deleteMsg = response.data.errorMsg;
                        if ($scope.deleteMsg.length === 0) {
                            $scope.deleteMsg = "Sucessfully deleted cat " + id;
                        } else {
                            $scope.deleteMsg = "Delete Error: " + $scope.deleteMsg;
                        }
                    },
                    function (response) { // this function will run if http.get error
                        console.log("Cat Delete ajax error");
                        console.log(response);
                        console.log("");
                        $scope.deleteMsg = "Delete Error: " + response.status + " " + response.statusText;

                    } // end of error fn
            ); // closes off "then" function call
        } // deleteCat
    
        $scope.errorMsg = "";
        $http.get("webAPIs/getOtherListAPI.jsp").then(
                function (response) { // this function will run if http.get success
                    console.log("Get Cat ajax success");
                    console.log(response);
                    console.log("");
                    $scope.cats = response.data.recordList;
                    for (var i = 0; i<$scope.cats.length; i++){
                        $scope.cats[i].catIdNum = Number($scope.cats[i].cat_id);
                        
                        var catAgeNum = $scope.cats[i].cat_age;
                        console.log("type: " + typeof($scope.cats[i].cat_age));
                        $scope.cats[i].catAgeNum = Number(catAgeNum.replace(/,/g, ""));
                        console.log("CatAge: " + Number(catAgeNum) + " " + $scope.cats[i].cat_age);
                       
                    }
                    $scope.errorMsg = response.data.dbError;
                    //Check login status
                    if(response.data.loggedOn === "true"){
                        $scope.isLoggedOn = true;
                    } else {
                        $scope.isLoggedOn = false;
                    }
                },
                function (response) { // this function will run if http.get error
                    console.log("Get Cat ajax error");
                    console.log(response);
                    console.log("");
                    $scope.errorMsg = "Error: " + response.status + " " + response.statusText;
                } // end of error fn
        ); // closes off "then" function call

    // main code for this controller
    console.log("catListController");
    console.log($routeParams);
    //get current set of routeParams containing cat_id in url w/o value
    if ($routeParams.cat_id) {
        console.log("First I will delete cat " + $routeParams.cat_id);
        deletePlanet($routeParams.cat_id);
    } else {
        console.log("Listing cat without deleting first.");
    }

}); // end of def'n of the controller function 