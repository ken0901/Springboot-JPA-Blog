let index = {
    init:function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });

        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });

        $("#btn-update").on("click",()=>{
            this.update();
        });

        $("#btn-reply-save").on("click",()=>{
            this.replySave();
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

    deleteById: function(){
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            contentType: "application/json; charset=utf-8",
            dataType: "json" // get request from the server, basically it's all string type(look like json) => convert to javascript object
        }).done(function(res){
            alert("Deleting complete");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update: function(){
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data), //http body data
            contentType: "application/json; charset=utf-8", // what kind of body data type(MIME)
            dataType: "json" // get request from the server, basically it's all string type(look like json) => convert to javascript object
        }).done(function(res){
            alert("Editing complete");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replySave: function(){
        let data = {
            userId:  $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val(),
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(res){
            alert("Registered comment complete");
            location.href=`/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replyDelete: function(boardId, replyId){
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function(res){
            alert("Delete comment complete");
            location.href=`/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
}
index.init();