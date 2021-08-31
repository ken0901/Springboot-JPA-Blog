<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../layout/header.jsp"%>

<div class="container">
   <form>
       <div class="form-group">
           <label for="title">Title:</label>
           <input type="title"  class="form-control" placeholder="Enter title" id="title">
       </div>
       <div class="form-group">
            <label for="content">Comment:</label>
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
       </div>

   </form>
   <button id="btn-save" class="btn btn-primary">Complete</button>
</div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>