package Queries;

public interface Statements {

	String create = "INSERT INTO todo (id, tasks, status, due_date)" + " VALUES (?, ?, ?, ?)";
	String read = "SELECT * FROM todo WHERE id=?";
	String readAll = "SELECT * FROM todo";
	String update = "UPDATE todo SET tasks=?, status=?, due_date=? WHERE id=?";
	String delete = "DELETE FROM todo WHERE id=?";
	String search = "SELECT id,tasks,status,due_date FROM todo WHERE tasks LIKE  ? ";
}
