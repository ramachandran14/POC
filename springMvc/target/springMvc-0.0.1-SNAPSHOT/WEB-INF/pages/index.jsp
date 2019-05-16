<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://bootswatch.com/4/yeti/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>ToDoTable</title>
</head>
<body>
	<div id="demo">
		<c:out value="${msg}"/>
		<div id="header">
			<form method="post" action="search">
				<input type="text" name="tasks" placeholder="Search tasks">
				<button type="submit" name="save" class="btn btn-primary">Search</button>
			</form>
			<center>
				<button><a href="todo/create">Add ToDo</a></button>
			</center>
		</div>
		<table border="1" class="table table-striped mt-5" style="font-size: 20px;">
			<tr>
				<th>id</th>
				<th>tasks</th>
				<th>status</th>
				<th>due_date</th>
				<th>action</th>
			</tr>
			<c:forEach var="todo" items="${listToDo}">
				<tr>
					<td>${todo.id}</td>
					<td>${todo.tasks}</td>
					<td>${todo.status}</td>
					<td>${todo.due_date}</td>
					<td><input type="button" id=${todo.id } value="delete" class="deleteValue btn btn-danger" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		$(document).ready(function() {
			$(".deleteValue").click(function() {
				var value = $(this).attr("id");
				$(this).parents("tr").remove();
				$.ajax({
					type : "POST",
					url : "delete",
					data : {
						"value" : value
					},
					success : function(data) {
						$("#id").setText("Successfully deleted");
					}
				});
			});
		});
	</script>
</body>
</html>