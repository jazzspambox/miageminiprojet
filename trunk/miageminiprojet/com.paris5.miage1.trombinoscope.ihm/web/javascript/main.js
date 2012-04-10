/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 */
function autoSelect(elem, elements)
{
    var value;
    var matched  = false;
    var text     = elem.value;
    var choice   = {
        mobile: "search_mobile",
        fixe: "search_fixe",
        email: "search_email"
        
    };
    var elements = document.getElementById(elements).getElementsByTagName('input');
	
    if(text.match(/@/))
    {
        matched = true;
        value = choice.email;
    }
    else if(text.match(/^06([-. ]?[0-9]{2}){4}$/))
    {
        matched = true;
        value = choice.mobile;
    }
    else if(text.match(/^01([-. ]?[0-9]{2}){4}$/))
    {
        matched = true;
        value = choice.fixe;
    }
	
    if(matched)
    {	
        for(var i = 0; i < elements.length; i++)
        {	
            if(elements[i].value == value)
            {	
                elements[i].checked = true;
            }	
        }
    }
    else{
        elements[1].checked = true;
    }
}

var oldString = new Array;
function inputBehaviour(id, txt){
    var input = document.getElementById(id);
    if(input.value == txt){
        oldString[id] = txt;
        input.value = '';
    }
    else if(input.value==''){
       input.value = oldString[id];
    }
}

function checkSubmit(id, defaut){
    var input = document.getElementById(id);
    if(oldString[id]==input.value || input.value == defaut ){
        input.value = '';
    }
}