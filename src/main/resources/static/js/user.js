let index = {
    init:function(){
        // let _this = this; // if you want to use function uncomment this
        $("#btn-save").on("click",()=>{ // function(){}, ()=>{} for binding this keyword
            this.save();
        });
    },

    save: function(){
//        alert('user function execute');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);
        //call ajax, default is Asynchronous call
        // ajax communication , send 3 data with json format and request insert
        // ajax communication success, server returns json type , auto convert to java object
        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), //http body data
            contentType: "application/json; charset=utf-8", // what kind of body data type(MIME)
            dataType: "json" // get request from the server, basically it's all string type(look like json) => convert to javascript object
        }).done(function(res){
            //console.log(res);
            alert("Sign in completed");
            location.href="/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();