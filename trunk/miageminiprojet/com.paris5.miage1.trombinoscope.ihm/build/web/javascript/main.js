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
        prenom: 2,
        mobile: 3,
        fixe: 4,
        email: 5
        
    };
    var elements = document.getElementById('search_filter').getElementsByTagName('input');
	
    if(text.match(/@/))
    {
        matched = true;
        value = choice.email;
    }
    else if(text.match(/^[A-Z]{3}_[0-9]+\.[0-9]+/))
    {
        matched = true;
        value = choice.payment;
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

var oldString ={};
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