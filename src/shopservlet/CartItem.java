package shopservlet;


public class CartItem extends Product{
	static int Thecartid=1;
    private int quantity;
    private int cartid;
    public CartItem(int q,Product p){
    	super(p);
     	quantity=q;
     	cartid =CartItem.Thecartid++;
    }
	public int getCartId() {
		return cartid;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void Print(){
		System.out.print("cart id "+cartid);
		System.out.print("quantity "+quantity);
		super.Print();
		
	}
}
