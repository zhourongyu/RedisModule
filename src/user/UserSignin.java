package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.IUserManager;
import manager.impl.UserManager;
import net.sf.json.JSONObject;
import bean.User;

/**
 * Servlet implementation class UserSignin
 */
@WebServlet(urlPatterns={"/htm/signin","/htm/Login"})
public class UserSignin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid = request.getParameter("uid");
		IUserManager um = new UserManager();
		try {
			um.logout(uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "signin.html";
		JSONObject data = new JSONObject();
		data.put("url", url);
		
		PrintWriter pw = response.getWriter();  
        pw.print(data.toString()); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html;charset=utf-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setEmail(username);
		user.setPassword(password);
		IUserManager um = new UserManager();
		HashMap<String, String> hm = null;
		try {
			hm = um.login(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject data = new JSONObject();
		data.put("url", hm.get("url"));
		data.put("message", hm.get("message"));
		data.put("flag", hm.get("flag"));
		
		PrintWriter pw = response.getWriter();  
        pw.print(data.toString()); 

	}

}
