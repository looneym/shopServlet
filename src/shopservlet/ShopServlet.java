package shopservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet(name="ShopServlet", urlPatterns={"/app/*"})
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String requestMessageLine;
	String command;
	Products products = new Products();
	Cart theCart = new Cart();
	Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        System.out.println("options");
    	//The following are CORS headers. Max age informs the 
        //browser to keep the results of this call for 1 day.
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Max-Age", "86400");
        //Tell the browser what requests we allow.
        resp.setHeader("Allow", "GET, HEAD, POST, TRACE, OPTIONS");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 command = request.getRequestURI();
		 System.out.println("pathinfo "+ request.getPathInfo());
	     String Data="";
	      // remove leading slash from line if exists
	   
	      if (command.startsWith("/") == true)
	          command = command.substring(1);
	      System.out.println("command "+command);
	      int pos = command.lastIndexOf('/');
	      if (pos !=-1){
	    	  command = command.substring(pos+1, command.length());
	      }
	      System.out.println("actual command "+command);
	      response.addHeader("Access-Control-Allow-Origin", "*");
	      
	      
//	      if (command.equals("shop.html")){
//		    	 System.out.println("Java sucks");
//		    	 RequestDispatcher view = request.getRequestDispatcher("/shop.html");
//			        // don't add your web-app name to the path
//
//			        view.forward(request, response); 
//		    	 }
//	      
	      
	      
	     
	      
	      
	  if (command.equals("getProducts")){
		 String xx=gson.toJson(products.getProducts());
    	 System.out.println("xx "+xx);
		response.getWriter().append(xx);
	  }
	  else
		  if (command.equals("getCart")){
		     	 String xx=gson.toJson(theCart.getCart());
		     	 System.out.println("xx "+xx);
		     	response.getWriter().append(xx);
		  }
		  else
		  if (command.compareTo("addToCart")==0 ){
			  StringBuffer jb = new StringBuffer();
			  String line = null;
			  try {
			    BufferedReader reader = request.getReader();
			    while ((line = reader.readLine()) != null)
			      jb.append(line);
			  } catch (Exception e) { /*report an error*/ }

			  line = jb.toString();
    		  System.out.println("data="+line);
    		  JsonReader reader = new JsonReader(new StringReader(line));
    		  reader.setLenient(true);
    		  addToCartMessage m = gson.fromJson(reader, addToCartMessage.class); 
    		  System.out.println("got addToCart"+ " mess "+m);
    		  Product p = products.getProduct(m.getProdid());
    		  if (p!=null){
    			  theCart.add(m.getQty(),p);
    		  }
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
