//package com.ofs.maven;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.testng.Assert.assertEquals;
//
//import org.mockito.Mockito;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.ofs.maven.Impl.ToDoServiceImpl;
//import com.ofs.maven.model.ToDo;
//import com.ofs.maven.service.ToDoService;
//
//public class ToDoListTest {
//
//	private static ToDoServiceImpl mockToDo = mock(ToDoServiceImpl.class);
//	ToDo actual;
//	ToDo expected;
//	
//	@BeforeTest
//	public void setup() {
//		actual = new ToDo();
//		expected = new ToDo();
//	}
//	
//	@Test(dataProvider="testCreatePositive")
//	public ToDoServiceImpl testCreatePositive(ToDo actual, ToDo expected) {
//		when(mockToDo.save(actual)).thenReturn(expected);
//		assertEquals(1, expected.getId());
//	}
//	
//	@DataProvider(name="testCreatePositive")
//	public Object [ ] [ ] saveToDo() {
//		actual.setTasks("Buy Milk");
//		actual.setStatus("In Progress");
//		actual.setDue_date("2019-08-10");
//		expected.setId(1);
//		expected.setTasks("Buy Milk");
//		expected.setStatus("In Progress");
//		expected.setDue_date("2019-08-10");
//		return new Object [ ] [ ] {{ actual, expected}};
//	}
//}
