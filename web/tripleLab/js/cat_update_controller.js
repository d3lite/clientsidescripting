app.controller('catUpdateController', function ($scope, $http, $routeParams, SkService) {

    // these booleans control which Save button the user will see in the 
    // cat_insert_update.html (partial html file). 
    $scope.isUpdateMode = true;
    $scope.isInsertMode = false;

    // this will be used to label the heading on the cat_insert_update.html (partial html file).
    $scope.insertUpdate = "Update";

    console.log("catUpdateController");
    console.log($routeParams);
    $scope.cat_id = $routeParams.cat_id;

    // blank out the new planet in case the ajax get fails to populate newcat
    $scope.newcat = SkService.emptyCat();

    // blank out error message object
    $scope.myErrors = SkService.emptyCat();

    //Find the cat with entered id through separate JSON request to display (gets view)
    var url = "webAPIs/getOtherAPI.jsp?id=" + $routeParams.cat_id;
    $http.get(url).then(
            function (response) { // this function will run if http.get success
                console.log("Cat Update (get) ajax success");
                console.log(response);
                console.log("");
                $scope.newcat = response.data;
                $scope.errorMsg = "";
            },
            function (response) { // this function will run if http.get error
                console.log("Cat Update (get) ajax error");
                console.log(response);
                console.log("");
                $scope.errorMsg = "Error: " + response.status + " " + response.statusText;

            } // end of error fn
    ); // closes off "then" function call

    //Update cat
    $scope.updateSave = function () {

        // empty out all the field level user error messages in case of an ajax error 
        $scope.myErrors = SkService.emptyCat();

        var myData = JSON.stringify($scope.newcat);
        var url = "webAPIs/updateOtherAPI.jsp?jsonData=" + myData;

        $http.get(url).then(
                function (response) { // this function will run if http.get success
                    console.log("Cat Update/Save ajax success");
                    console.log(response);
                    console.log("");
                    $scope.myErrors = response.data;
                    $scope.status = $scope.myErrors.errorMsg;
                    if ($scope.myErrors.errorMsg.length === 0) {
                        $scope.status = "Cat Sucessfully Updated";
                    }
                },
                function (response) { // this function will run if http.get error
                    console.log("Cat Update/Save ajax error");
                    console.log(response);
                    console.log("");
                    $scope.status = "Error: " + response.status + " " + response.statusText;

                } // end of error fn
        ); // closes off "then" function call
    };

});