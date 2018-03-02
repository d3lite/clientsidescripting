var app =  angular.module('app', ['ngRoute']);

// I am creating a "service" (which is just really my class full of methods). In this service,
// I will put anything that needs to be used by more than one controller - to avoid copy/paste.
app.factory("SkService", function () {

    //for cats
    var emptyCat = function () {
        return {
            cat_id: "",
            cat_type: "",
            cat_age: "",
           
            cat_mood: "",
            catimg_url: ""
        };
    };

    // expose methods (make them accessible)
    return {
        emptyCat: emptyCat
    };
});

app.factory("SkService2", function(){
    //for people
    var emptyPerson = function () {
        return {
            person_id: "",
            person_email: "",
            person_password: "",
            person_nickname: "",
            person_rolename: ""
        };
    };

    // expose methods (make them accessible)
    return {
        emptyPerson: emptyPerson
    };
});