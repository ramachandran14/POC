<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Add New ToDo</title>
</head>
<body>
	<c:out value="${msg}"/>
	<center>
		<p id="demo"></p>
		<h1>Add New ToDo</h1>
			<table>
				<tr>
					<td>tasks :</td>
					<td><input type="text" id="tasks" name="tasks" class="form-control" /></td>
				</tr>
				<tr>
					<td>status :</td>
					<td><input type="text" id="status" name="status" class="form-control" /></td>
				</tr> 
				<tr>
					<td>due_date :</td>
					<td><input type="date" id="due_date" name="due_date" class="form-control" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Save" class="btn btn-default" id="newToDo"/></td>
				</tr>
			</table>
	</center>
	<script>
		$(document).ready(function() {
			$('#newToDo').click(function() {
				var tasks = $('#tasks').val();
				var status = $('#status').val();
				var due_date = $('#due_date').val();
				
				$.ajax({
					type: "POST",
					url: "/SpringWeb/save?",
					data: $.param({"tasks" : tasks, "status": status, "due_date": due_date}),
					success: function(data) {
						$("body").html(data);
			        }
				});
			});
		});
	</script>
</body>
</html>