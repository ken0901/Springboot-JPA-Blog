let index = {
    init:function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
    },

    save: function(){
//        alert('user function execute');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

//        console.log(data);
        $.ajax().done().fail(); // ajax communication , send 3 data with json format and request insert
    }
}

index.init();