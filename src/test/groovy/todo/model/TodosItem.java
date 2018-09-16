package todo.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TodosItem{

	@SerializedName("text")
	private String text;

	@SerializedName("complete")
	private boolean complete;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setComplete(boolean complete){
		this.complete = complete;
	}

	public boolean isComplete(){
		return complete;
	}

	@Override
 	public String toString(){
		return 
			"TodosItem{" + 
			"text = '" + text + '\'' + 
			",complete = '" + complete + '\'' + 
			"}";
		}
}