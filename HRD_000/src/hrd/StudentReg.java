package hrd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//학생 등록

@WebServlet("/StudentReg")
public class StudentReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "StudentReg.jsp");
		
		request.getRequestDispatcher("index.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> params = new ArrayList();		
		String[] keys = {"syear", "sclass", "sno", "sname", "birth", "gender", "tel1", "tel2", "tel3" };
		
		for(String key : keys) {
			params.add(request.getParameter(key));
		}
		
		int result = Dao.studentReg(params);
		
		request.setAttribute("result", result);
		doGet(request, response);
		
	}

}












