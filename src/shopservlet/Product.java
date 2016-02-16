package shopservlet;


public class Product {
	static int ProductId=0;
	private int id;
	private String name;
	private String description;
	private double price;
	private String imageFile;
	Product (Product p){
		id=p.id;
		name=p.name;
		description=p.getDescription();
		price=p.getPrice();
		imageFile=p.imageFile;
	}
	Product (String n, String d, double p,String i){
		id=ProductId++;
		name=n;
		description=d;
		price=p;
		imageFile=i;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	} 
	public void Print(){
	
		System.out.println(" Product id = "+ id);
		System.out.print(" name= "+name);
		System.out.print(" description= "+description);
		System.out.print(" price= "+price);
		System.out.println(" imageFile= "+imageFile);
	}
}
