<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body background="https://imgur.com/fdjIjPu.jpg" >
<H1 style="width:1300px;height:200px;text-align:center;line-height:500px">Which cuisine do you want to make?</H1>
<form action='${requestUri}' method='get' style="width:1300px;height:200px;text-align:center;line-height:350px">
<input type='text' name='keyword' placeholder = 'keyword'/>
<input type='submit' value='submit' />
</form>
</body>
</html>