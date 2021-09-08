<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../layout/header.jsp"%>

<div class="container">

    <button class="btn btn-secondary" onclick="history.back()">Back</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">Edit</a>
        <button id="btn-delete" class="btn btn-danger">Delete</button>
    </c:if>
    <br/><br/>
    <div>
        Content No : <span id="id"><i>${board.id} </i></span>
        User Name : <span><i>${board.user.username} </i></span>
    </div>
    <br/>
    <div>
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div>
        <div>${board.content}</div>
    </div>
    <hr/>

    <!-- Comment start-->
    <div class="card">
        <form>
            <input type="hidden" id="userId" value="${principal.user.id}"/>
            <input type="hidden" id="boardId" value="${board.id}"/>
            <div class="card-body">
                <textarea id="reply-content" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary">Register</button>
            </div>
        </form>
    </div>
    <br/>
    <div class="card">
        <div class="card-header">comment list</div>
        <ul id="reply-box" class="list-group">
          <c:forEach var="reply" items="${board.replies}">
              <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                <div>${reply.content}</div>
                <div class="d-flex">
                    <div class="font-italic">writer: ${reply.user.username} &nbsp;</div>
                    <c:if test="${reply.user.username eq principal.user.username}">
                        <button onClick="index.replyDelete(${board.id},${reply.id})" class="bagde">Delete</button>
                    </c:if>
                </div>
              </li>
          </c:forEach>
        </ul>
    </div>
    <!-- comment end-->
</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>