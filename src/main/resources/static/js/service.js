
(function($){

    var service = {};
    $.service = service;

    service.post = function(url, jsonData, callFn){

        var _callFn = callFn || function(){};

        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify (jsonData),
            success: function (res) {
                console.log(res);

                if(res.code == 'GUEST') {
                    sessionStorage.setItem("page", window.location.href);
                    window.location.href = '/login';
                } else {
                    if(_callFn) {
                        _callFn.call(null, res);
                    }
                }

            }
        });
    }


})(jQuery);