<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../layout/header.jsp"%>

<div class="container">

    <button class="btn btn-secondary" onclick="history.back()">Back</button>
    <button id="btn-update" class="btn-warning">Edit</button>
    <button id="btn-delete" class="btn-danger">Delete</button>
    <br/><br/>
    <div>
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div>
        <div>${board.content}</div>
    </div>
    <hr/>
</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>