package hrd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
	tbl_score_201905, tbl_student_201905 테이블 자료를
	따로 가져와서 자바로 처리
*/
@WebServlet("/ScoreList2")
public class ScoreList2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("score", Dao.getScore());
		request.setAttribute("student", Dao.getStudent());
		
		request.setAttribute("view", "ScoreList2.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	

}
