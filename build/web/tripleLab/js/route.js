app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'htmlPartials/cat_list.html',
        controller: 'catListController'
    }).when('/home', {
        templateUrl: 'htmlPartials/cat_list.html',
        controller: 'catListController'
    }).when('/list', {
        templateUrl: 'htmlPartials/person_list.html',
        controller: 'personListController'
    }).when('/insertCat', {
        templateUrl: 'htmlPartials/cat_insert_update.html',
        controller: 'catInsertController'
    }).when('/insertPerson', {
        templateUrl: 'htmlPartials/person_insert_update.html',
        controller: 'personInsertController'
    }).when('/update/:cat_id', {
        templateUrl: 'htmlPartials/cat_insert_update.html',
        controller: 'catUpdateController'
    }).when('/show/:cat_id', {
        templateUrl: 'htmlPartials/cat_detail.html',
        controller: 'catDetailController'
    }).when('/delete/:cat_id', {
        templateUrl: 'htmlPartials/cat_list.html',
        controller: 'catListController'
    }).when('/logon', {
        templateUrl: 'htmlPartials/logon.html',
        controller: 'logonController'
    }).when('/logoff', {
        templateUrl: 'htmlPartials/logoff.html',
        controller: 'logoffController'    
    }).otherwise({
        redirectTo: '/'
    });
});
