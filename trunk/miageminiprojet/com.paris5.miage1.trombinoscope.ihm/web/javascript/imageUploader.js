/* 
 * mourad
 */
var jcrop_api; // Holder for the API

function croper ($){
    
    // Create variables (in this scope) to hold the API and image size
    var boundx, boundy;
    
    $('#profilPhoto').Jcrop({
        onChange: updatePreview,
        onSelect: updatePreview,
        onRelease : hidePreview,
        aspectRatio: 3/4,
        bgOpacity: .3
    },function(){
        // Use the API to get the real image size
        var bounds = this.getBounds();
        boundx = bounds[0];
        boundy = bounds[1];
        // Store the API in the jcrop_api variable
        jcrop_api = this;
    });

    function updatePreview(c)
    {
        if (parseInt(c.w) > 0)
        {
            var rx = 100 / c.w;
            var ry = 130 / c.h;

            $('#preview').css({
                width: Math.round(rx * boundx) + 'px',
                height: Math.round(ry * boundy) + 'px',
                marginLeft: '-' + Math.round(rx * c.x) + 'px',
                marginTop: '-' + Math.round(ry * c.y) + 'px'
            }).show();
            showCoords(c);
        }
    };
    
    function hidePreview()
    {
        $('#preview').stop().fadeOut('fast');
        var c=[x=0,y=0];
        showCoords(c);
    }
    
    function showCoords(c)
    {
            $('#x').val(c.x);
            $('#y').val(c.y);
            $('#x2').val(c.x2);
            $('#y2').val((c.y2*130)/100);
            $('#w').val(c.w);
            $('#h').val(c.h);
    };
}
            
(function( $ ){
    
    //
    var result=null;
    
    //default
    var settings = {
        "action"        : "send_photo",
        "asynchronious" : "true",
        target          : ["preview", "profilPhoto","photo_url"],
        url             : "trombinoscope",
        file_elem       : "file",
        loading         : "images/loading.gif"
    }
    
    var methods = {
        
        /**
         *
         */
        init:      function(opt){
            if(opt) 
                $.extend(settings, opt);
        },
        
        /**
         *
         */
        progress: function(){
            for(i=0; i<settings.target.length; i++){
                $("#"+settings.target[i]).attr("src",settings.loading); 
            }
        },
        
        /**
         *
         */
        completed: function(){
            if(result!=null){
                for(i=0; i<settings.target.length; i++){
                    if($("#"+settings.target[i]).is("img"))
                        $("#"+settings.target[i]).attr("src",result);
                    else
                       $("#"+settings.target[i]).attr("value", result); 
                }
            }
            if(jcrop_api!=null)
                jcrop_api.destroy();
            
            croper($);
            //jcrop_api.setImage(result);
        },
        
        /**
         *
         */
        upload: function(files){
    
            var xhr; 
    
            try {  
                xhr = new ActiveXObject('Msxml2.XMLHTTP');   
            }
            catch (e) 
            {
                try {
                    xhr = new ActiveXObject('Microsoft.XMLHTTP'); 
                }
                catch (e2) 
                {
                    try {  
                        xhr = new XMLHttpRequest();  
                    }
                    catch (e3) {  
                        xhr = false;   
                    }
                }
            }
 
            xhr.onreadystatechange = function(){
                if(xhr.readyState  == 4)
                {
                    if(xhr.status  == 200) {
                        result = xhr.responseText;
                        var func = methods.completed;
                        setTimeout(func,3000);
                    }
                    else 
                        methods.error("Error code " + xhr.status);
                }
            }
            
            methods.progress();
            
            xhr.open("POST", settings.url, true);
            var nef = new FormData();
            nef.append("action","send_photo");
            nef.append("asynchronious","true");
            nef.append(settings.file_elem, files[0]);
            xhr.send(nef);
        } 
    }
    
    
    /**
     * 
     */
    $.fn.postImage = function(opt) {
        
        settings.file_elem = ""+$(this).attr("id");
        
        methods.init();
        
        $(this).find('input[type=file]').bind('change', function(e){
            e.preventDefault();
            if(e.target.files){
                methods.upload(e.target.files);
            }
        });
    }
})( jQuery );

