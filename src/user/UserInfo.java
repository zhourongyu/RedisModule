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

import util.MD5Util;
import manager.IUserManager;
import manager.impl.UserManager;
import net.sf.json.JSONObject;
import bean.User;

/**
 * Servlet implementation class UserSignin
 */
@WebServlet(urlPatterns={"/htm/userInfo"})
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfo() {
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
		HashMap<String, String> hm;
		if(uid != null){
			try {
				String status = um.loginStatus(uid);
				if(status != null && status.equals("1")){
					hm = um.getUserInfo(uid);
					request.setAttribute("uid", uid);
					request.setAttribute("email", hm.get("email"));
					request.setAttribute("nickname",  hm.get("nickname"));
					request.setAttribute("sex", hm.get("sex") );
					request.setAttribute("desc",  hm.get("desc"));
					request.setAttribute("userImageUrl", "http://www.gravatar.com/avatar/"+MD5Util.md5Hex(hm.get("email")));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/htm/userInfo.jsp");
					dispatcher.forward(request, response);
				}else{
					response.sendRedirect("signin.html");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html;charset=utf-8");

		String uid = request.getParameter("uid");
		String nickname = request.getParameter("nickname");
		String desc = request.getParameter("desc");
		
		User user = new User();
		user.setUid(uid);
		user.setNickname(nickname);
		user.setDescription(desc);
		
		IUserManager um = new UserManager();
		JSONObject data = null;
		try {
			data = um.updateUserInfo(user);
			PrintWriter pw = response.getWriter();  
		    pw.print(data.toString());  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
