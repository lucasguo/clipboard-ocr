G = {};
G.uploadpicCallback = function(type, data){

    type = type || 'before';
    var $the = $('#container');
    switch(type){
        case 'before':
            $the.attr('disabled', 'disabled');
            $the.val("Analyzing...");
            break;
        case 'error':
            $the.attr('disabled', false);
            alert('Upload image failed');
            break;
        case 'success':
            $the.attr('disabled', false);
            $the.val(data);
            break;
    }
}
$("#container").focus();
document.getElementsByTagName("textarea")[0].addEventListener('paste', function(e) {

    var clipboard = e.clipboardData;

    for(var i=0,len=clipboard.items.length; i<len; i++) {

        if(clipboard.items[i].kind == 'file'|| clipboard.items[i].type.indexOf('image') > -1) {
            var imageFile = clipboard.items[i].getAsFile();
            var form = new FormData;
            form.append('t', 'ajax-uploadpic');
            form.append('avatar', imageFile);
            var callback = G.uploadpicCallback || function(type, data){};

            $.ajax({
                url: 'paste',
                type: "POST",
                data: form,
                processData: false,
                contentType: false,
                beforeSend: function() {
                    callback('before');
                },
                error: function() {
                    callback('error');
                },
                success: function(data) {
                    callback('success', data);
                }
            })
            e.preventDefault();
        }
    }
});