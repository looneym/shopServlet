package shopservlet;


import java.util.ArrayList;

public class Cart {
	private ArrayList<CartItem> theCart;
	
    public Cart(){
    	theCart = new ArrayList<CartItem>();
    }

	public ArrayList<CartItem> getCart() {
		return theCart;
	}
	public void add(int qty,Product p){
	    theCart.add(new CartItem(qty,p));
	}
	public void deleteItem(int cartid){
	    for (int y=0;y<theCart.size();y++){
      	    if (theCart.get(y).getCartId() == cartid){
  	            theCart.remove(y);
  	            System.out.println("deleted "+theCart.get(y).getId());
  	            break;
      	}
      }
	}
	public void Print(){
		 for (int y=0;y<theCart.size();y++){
	      	CartItem item = theCart.get(y);
	  	    item.Print();
	      	}
	}
	
}
