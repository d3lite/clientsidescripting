/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";

function makeAnimation() {
    var AnimationFW = {};

    AnimationFW.$ = function (element) {
        return document.getElementById(element);
    };

    AnimationFW.makeObject = function (params) {

        // Set default values if nothing is passed

        params.names = params.names || "Farm";
        params.width = params.width || 96;
        params.height = params.height || 100;
        params.top = params.top || 400;
        params.left = params.left || 430;
        params.backgroundImage = params.backgroundImage || "farm.png";

        // Private variables
        var eleRef = AnimationFW.$(params.id);
        var name = params.name;
        var size = params.fontSize;
        var topValue = params.top;
        var leftValue = params.left;
        var backgroundImage = params.backgroundImage;


        function display() {
            eleRef.innerHTML = name;
            eleRef.style.top = topValue + "px";
            eleRef.style.left = leftValue + "px";
            eleRef.style.backgroundImage = "url(" + backgroundImage + ")";
        }


        eleRef.style.height = params.height + "px";
        eleRef.style.width = params.width + "px";
        eleRef.style.position = "absolute";
        eleRef.style.top = params.top + "px";
        eleRef.style.left = params.left + "px";
        eleRef.style.backgroundImage = "url(" + params.backgroundImage + ")";

        display();

        this.changeImage = function (newImage) {
            backgroundImage = newImage;
            display();
        };
    };
    return AnimationFW;

}



