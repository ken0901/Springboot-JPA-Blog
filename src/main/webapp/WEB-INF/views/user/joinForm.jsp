<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <!-- action="/user/join" method="post" -->
    <form>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="username" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
              <label for="password">Password :</label>
              <input type="password" class="form-control" placeholder="Enter password" id="password">
            </div>
        <div class="form-group">
              <label for="email">Email address:</label>
              <input type="email" class="form-control" placeholder="Enter email" id="email">
        </div>

    </form>
    <button id="btn-save" class="btn btn-primary">Sign in</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>