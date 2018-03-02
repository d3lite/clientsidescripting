/* 
 * this framework will create a thumbnail of the original mighty morphin power ranger
 * it will also display their bio, and basic informations. 
 * 
 */

"use strict";

function MakePRFW() {

    var fw = {};


    function $(ele) {
        return document.getElementById(ele);
    }
    fw.MakePR = function (params) {
        if (!params || !params.id) {
            alert("must be passed with an id property");
            return;
        }

        console.log("params.id is " + params.id);
        var prObj = $(params.id);
        if (!prObj)
        {
            alert("must pass an object with id property");
            return;
        }

        console.log(prObj);

        //default values
        params.imgUrl = params.imgUrl || "pics/redranger.jpg";
        params.imgWidth = params.imgWidth || 100;
        params.imgHeight = params.imgHeight || 200;
        params.backgroundColor = params.backgroundColor || "red";
        params.padding = params.padding || "10px";
        params.personInfo = params.personInfo || "http://powerrangers.wikia.com/wiki/Jason_Lee_Scott";


        prObj.style.backgroundColor = params.backgroundColor;
        prObj.style.padding = params.padding;
        prObj.style.borderRadius = "5px"; // hard code
        prObj.style.textAlign = "centered"; // hard code
        

        var imgWidth = params.width;
        var imgHeight = params.height;
        var imgUrl = params.imgUrl;
        var personInfo = params.personInfo;
        
        


        function display() {
            var str = "<a target= '_blank' href='" + personInfo + "'/>";

            str += "<img src='" + imgUrl + "' width:'" + imgWidth + ";' height: '" + imgHeight + ";'/>";

            +"</a>";

            prObj.innerHTML = str;


        }
        display();
        
        prObj.setImage = function(newImage){
            
            imgUrl = newImage;
            display();
        };
    
        prObj.changeURL= function(newURL){
            
    
            personInfo = newURL;
            display();
        };   
        
        prObj.onclick = function(){
            
           
            
            
        }
       
        





        return prObj;
    };

    return fw;

}


