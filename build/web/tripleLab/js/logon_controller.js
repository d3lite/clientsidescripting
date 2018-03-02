
app.controller('logonController', function ($scope, $http, $routeParams, SkService2) {
    console.log("logonController");
    
    $scope.person_email = "";
    $scope.person_password = "";
/*
    // blank out the new person in case the ajax get fails to populate newcat
    $scope.newcat = SkService2.emptyPerson();

    // blank out error message object
    $scope.myErrors = SkService2.emptyCat();
*/
    //Find the cat with entered id through separate JSON request to display (gets view)
    
    

    // blank out the new person in case the ajax get fails to populate newcat
    $scope.newperson = SkService2.emptyPerson();
    // blank out error message object
    $scope.myErrors = SkService2.emptyPerson();
 
    //Get LoginInfo
    $scope.loginSubmit = function(){
        
        // empty out all the field level user error messages in case of an ajax error 
        $scope.myErrors = SkService2.emptyPerson();
        $scope.person_email = $scope.newperson.person_email;
        $scope.person_password =  $scope.newperson.person_password;
        console.log($scope.person_email + " " + $scope.person_password);
        /*
        //print out data from input fields (this method could be used but comes down to preference)
        var myData = JSON.stringify($scope.newscientist);
        var url = "webAPIs/logonAPI.jsp?jsonData=" + myData;
         */
        var url = "webAPIs/logonAPI.jsp?email=" + $scope.person_email + "&password=" + $scope.person_password;
        console.log("url: " + url);
        $http.get(url).then(
                function (response) { // this function will run if http.get success
                    console.log("Login GET Success");
                    console.log(response);
                    console.log("");
                    $scope.myErrors = response.data;
                    $scope.status = $scope.myErrors.errorMsg;
                    if ($scope.myErrors.errorMsg.length === 0) {
                        $scope.status = "Login Successful";
                    }
                },
                function (response) { // this function will run if http.get error
                    console.log("Login ajax error");
                    console.log(response);
                    console.log("");
                    $scope.status = "Error: " + response.status + " " + response.statusText;

                } // end of error fn
        ); // closes off "then" function call
    };
});