public class Media {
	
	private String title;
	private String creator;
	private String length;
	
	public Media(String title, String creator, String length) {
		
		this.title = title;
		this.creator = creator;
		this.length = length;
	}
	
	public String getTitle() {
		
		return title;
	}
	
	public String getCreator() {
		
		return creator;
	}
	
	public String getLength() {
		
		return length;
	}
	
	@Override public String toString() {
		
		return title + "\n" + 
				creator + "\n" +
				length + "\n";
	}
}
