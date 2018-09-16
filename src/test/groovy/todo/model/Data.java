package todo.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Data{

	@SerializedName("todos")
	private List<TodosItem> todos;

	public void setTodos(List<TodosItem> todos){
		this.todos = todos;
	}

	public List<TodosItem> getTodos(){
		return todos;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"todos = '" + todos + '\'' + 
			"}";
		}
}