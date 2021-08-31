<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../layout/header.jsp"%>

<div class="container">
   <form>
        <input type="hidden" id="id" value="${board.id}"/>
        <div class="form-group">
           <input type="title" value="${board.title}" class="form-control" placeholder="Enter title" id="title">
        </div>
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
   </form>
   <button id="btn-update" class="btn btn-primary">Edit</button>
</div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>