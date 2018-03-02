
app.controller('catDetailController', function ($scope, $http, $routeParams) {
    console.log("catDetailController");
    console.log($routeParams);
    $scope.cat_id = $routeParams.cat_id;
    var url = "webAPIs/getOtherAPI.jsp?id=" + $routeParams.cat_id;

    $http.get(url).then(
            function (response) { // this function will run if http.get success
                console.log("Cat Detail ajax success");
                console.log(response);
                console.log("");
                $scope.cat = response.data;
                $scope.errorMsg = "";
            },
            function (response) { // this function will run if http.get error
                console.log("Cat Detail ajax error");
                console.log(response);
                console.log("");
                $scope.errorMsg = "Error: " + response.status + " " + response.statusText;

            } // end of error fn
    ); // closes off "then" function call

});