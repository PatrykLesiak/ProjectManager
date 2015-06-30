/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function changeOnApply(buton){
    $(buton).value="Applied!";
    $(buton).addClass('btn-warning').removeClass('btn-success');
    $(buton).attr("disabled", true);
};
