(function(){
    var apis = [];
    loadApis();

    function loadApis(){
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/api/v1/apis',
            success: function(data){
                if(data && data.length > 0){
                    apis = data;
                }
                draw();
            },
            error: function(data){
                draw();
            }
        });
    }

    function draw(){
        for(var i in apis){
            var api = apis[i],
                divId = 'swagger-ui' + i;

            $div = $("<div>");
            $div.attr('id', divId)
            $("body").append($div);

            window["ui" + i] = SwaggerUIBundle({
                url: api.url,
                dom_id: "#" + divId,
                presets: [
                  SwaggerUIBundle.presets.apis,
                  SwaggerUIStandalonePreset
                ],
                plugins: [
                  SwaggerUIBundle.plugins.DownloadUrl
                ],
                layout: "StandaloneLayout"
            })
        }
    }
})();