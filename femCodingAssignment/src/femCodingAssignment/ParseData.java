package femCodingAssignment;

import java.util.ArrayList;
// Class formation given like json 
public class ParseData {
	private String description;
	private ArrayList<String> categories;
	private String title;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	
	

	
}
