/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(
        function(){
            if ($("#main_info").length && $("#main_info").text() !== ""){
                var link = $("#main_info").text();
                var request_text = $.get(link,function(data){$("#main_info").text(data);});
                
            }
        }
        );
