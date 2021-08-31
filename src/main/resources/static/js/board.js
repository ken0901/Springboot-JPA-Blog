let index = {
    init:function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
    },

    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), //http body data
            contentType: "application/json; charset=utf-8", // what kind of body data type(MIME)
            dataType: "json" // get request from the server, basically it's all string type(look like json) => convert to javascript object
        }).done(function(res){
            alert("Writing completed");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
}
index.init();