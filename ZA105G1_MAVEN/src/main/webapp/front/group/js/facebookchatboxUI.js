
 	   
 	   




//this function can remove a array element.
            Array.remove = function(array, from, to) {
                var rest = array.slice((to || from) + 1 || array.length);
                array.length = from < 0 ? array.length + from : from;
                return array.push.apply(array, rest);
            };
        
            //this variable represents the total number of popups can be displayed according to the viewport width
            var total_popups = 0;
            
            //arrays of popups ids
            var popups = [];
            
            var map = new Map();
        
            //this is used to close a popup
            function close_popup(id)
            {
                for(var iii = 0; iii < popups.length; iii++)
                {
                    if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                        
                        document.getElementById(id).style.display = "none";
                        
                        calculate_popups();
                        
                        map.set(id,0);
                        
                        return;
                    }
                }   
            }
        
            //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
            function display_popups()
            {
                var right = 220;
                
                var iii = 0;
                for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + 320;
                        element.style.display = "block";
                    }
                }
     
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                }
            }
            
            //creates markup for a new popup. Adds the id to popups array.
            function register_popup(id, name, sender_no)
            {
            	
            	if(map.get(name)!=1){
                
                for(var iii = 0; iii < popups.length; iii++)
                {   
                    //already registered. Bring it to front.
                    if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                    
                        popups.unshift(id);
                        
                        calculate_popups();
                        
                        
                        return;
                    }
                }               
                
                var element = '<div class="popup-box chat-popup" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div id="statusOutput" class="statusOutput"></div>';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="popup-head-right" onclick="disconnect(\''+ id +'\');"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div><textarea id="popup-messages'+id+'" class="popup-messages" readonly ></textarea></div>';
         
                
                element = element + '<div class="popup-input"><input id="message'+id+'"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage(\''+ id +'\',\''+ sender_no +'\');"/></div>';
                //element = element + '<div><input type="button" id="message2'+id+'" value="確認"onclick="sendMessage(\''+ id +'\');"/></div>';

                
                document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;  
        
                popups.unshift(id);
                
                
                        
                calculate_popups();
               
                map.set(id,1);
                
                
            	}
            	
            	
            	
            	
            	$('#message'+id).keypress(function (e) {
            		
            		//alert(msg);
            		var key = e.which;
            		 var host = window.location.host;
        		    var path = window.location.pathname;
        		    var webCtx = path.substring(0, path.indexOf('/', 1));
        		    var endPointURL = "http://" + window.location.host + webCtx;
        		    $(this).keyup(function(){
        		    	str = $(this).val();
        		    	//alert(str);
        		    });
            		 if(key == 13 && str!="")  // the enter key code
            		  { 
            			 //var msg =$(this).attr("value"); 
            			 //alert(msg);
            			 $.ajax({
             				 type:"post",
             				 url:endPointURL+"/personal/personal.do",
             				 data:{action:"insert_message", mem_no:sender_no, friend_no:id, message:str},
             				 dataType:"json",      
                          })

            		  }
            	}); 
            	
                
                
            }
            
            //calculate the total number of popups suitable and then populate the toatal_popups variable.
            function calculate_popups()
            {
                var width = window.innerWidth;
                if(width < 540)
                {
                    total_popups = 0;
                }
                else
                {
                    width = width - 200;
                    //320 is width of a single popup box
                    total_popups = parseInt(width/320);
                }
                
                display_popups();
                
            }
            
            //recalculate when window is loaded and also when window is resized.
            window.addEventListener("resize", calculate_popups);
            window.addEventListener("load", calculate_popups);