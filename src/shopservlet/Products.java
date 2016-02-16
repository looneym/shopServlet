package shopservlet;


public class Products {
	private Product[] products;
	private int noproducts;
	private int size;
	
	public void addProduct(Product p){
		products[noproducts++]=p;
	}
	public int getNoproducts() {
		return noproducts;
	}


	public void setNoproducts(int noproducts) {
		this.noproducts = noproducts;
	}

    public Product getProduct(int id){
    	 for (int y=0;y<noproducts;y++){
         	if (products[y].getId() == id){
     	       return products[y];
     	       }
         	}
    return null;
    }
	public Product[] getProducts() {
		Product[] p = new Product[noproducts];
		for (int i=0;i<noproducts;i++){
			p[i]=products[i];
		}
		return p;
	}
	
	
	public Products(){
		noproducts =2;
		products = new Product[20];
		products[0] = new Product("car1",
				"Sports cars may be spartan"+
	
			 "or luxurious, but high maneuverability", 
			 11.2,"car1.jpeg");
		products[1]= 
	         new Product("car2","Sports cars may be spartan"+
	           "or luxurious, but high maneuverability", 
	        21.2,"car2.jpeg");
	               	    		  
		
	}
}
