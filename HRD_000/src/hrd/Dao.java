package hrd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	
	public static Connection getCon() throws Exception {		
		Class.forName("oracle.jdbc.OracleDriver");		
		String url = "jdbc:oracle:thin:@//localhost:1521/xe";
		String user = "hr";
		String pw = "hkit2019";
		Connection con = DriverManager.getConnection(url, user, pw);
		System.out.println("DB연결!!");
		return con;
	}
	
	private static void close(ResultSet rs, PreparedStatement ps, Connection con) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
	
	public static int studentReg(List<String> params) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO TBL_STUDENT_201905"
				+ " (syear, sclass, sno, sname, birth, gender, tel1, tel2, tel3)"
				+ " VALUES"
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			for(int i=0; i<params.size(); i++) {
				ps.setString(i + 1, params.get(i));
			}
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, ps, con);
		}
		
		return result;
	}
	
	public static List<ScoreListVo> getScoreList() {
		List<ScoreListVo> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "  SELECT (A.syear || '-' || A.sclass || '-' || A.sno) as pk\r\n" + 
				", A.sname\r\n" + 
				", decode(A.gender, 'F', '여', 'M', '남') as gender\r\n" + 
				", B.kor, B.eng, B.math\r\n" + 
				", (B.kor + B.eng + B.math) as totalSum\r\n" + 
				", ROUND(((B.kor + B.eng + B.math) / 3), 2) as totalAvg\r\n" + 
				"FROM TBL_STUDENT_201905 A\r\n" + 
				"INNER JOIN TBL_SCORE_201905 B\r\n" + 
				"ON a.syear = b.syear\r\n" + 
				"AND a.sclass = b.sclass\r\n" + 
				"AND a.sno = b.sno";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String pk = rs.getString("pk");
				String sname = rs.getString("sname");
				String gender = rs.getString("gender");
				int kor = rs.getInt("kor");
				int eng = rs.getInt("eng");
				int math = rs.getInt("math");
				int totalSum = rs.getInt("totalSum");
				double totalAvg = rs.getDouble("totalAvg");
				
				ScoreListVo vo = new ScoreListVo(
						pk, sname, gender, kor, eng, math, totalSum, totalAvg);
				
				list.add(vo);
						
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
		
		return list;
	}
	
	public static GroupVo getGroup() {
		GroupVo vo = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT\r\n" + 
				"syear\r\n" + 
				", sum(kor) as sumKor\r\n" + 
				", sum(eng) as sumEng\r\n" + 
				", sum(math) as sumMath\r\n" + 
				", avg(kor) as avgKor\r\n" + 
				", avg(eng) as avgEng\r\n" + 
				", avg(math) as avgMath\r\n" + 
				"FROM TBL_Score_201905\r\n" + 
				"GROUP BY syear";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int sumKor = rs.getInt("sumKor");
				int sumEng = rs.getInt("sumEng");
				int sumMath = rs.getInt("sumMath");
				double avgKor = rs.getDouble("avgKor");
				double avgEng = rs.getDouble("avgEng");
				double avgMath = rs.getDouble("avgMath");
				
				vo = new GroupVo(sumKor, sumEng, sumMath, avgKor, avgEng, avgMath);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
		return vo;
	}
	
	
	public static List<ScoreVo> getScore() {
		List<ScoreVo> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT *\r\n" + 
				"FROM tbl_score_201905\r\n" + 
				"ORDER BY syear, sclass, sno";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ScoreVo vo = new ScoreVo();
				vo.setSyear(rs.getString("syear"));
				vo.setSclass(rs.getString("sclass"));
				vo.setSno(rs.getString("sno"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMath(rs.getInt("math"));	
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
		
		return list;
	}
	
	public static List<StudentVo> getStudent() {
		List<StudentVo> list = new ArrayList();
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT *\r\n" + 
				"FROM tbl_student_201905\r\n" + 
				"ORDER BY syear, sclass, sno";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				StudentVo vo = new StudentVo();
				vo.setSyear(rs.getString("syear"));
				vo.setSclass(rs.getString("sclass"));
				vo.setSno(rs.getString("sno"));
				vo.setSname(rs.getString("sname"));
				vo.setGender(rs.getString("gender"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
		
		return list;
	}
	
	public static List<StudentScoreVo> getStudentScoreList() {
		List<StudentScoreVo> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT\r\n" + 
				"A.syear, A.sclass, A.sno, A.sname, A.gender\r\n" + 
				", B.kor, B.eng, B.math\r\n" + 
				", (B.kor + B.eng + B.math) as totalSum\r\n" + 
				"FROM tbl_student_201905 A\r\n" + 
				"INNER JOIN tbl_score_201905 B\r\n" + 
				"ON A.syear = B.syear\r\n" + 
				"AND A.sclass = B.sclass\r\n" + 
				"AND A.sno = B.sno";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				StudentScoreVo vo = new StudentScoreVo();
				vo.setSyear(rs.getString("syear"));
				vo.setSclass(rs.getString("sclass"));
				vo.setSno(rs.getString("sno"));
				vo.setSname(rs.getString("sname"));
				vo.setGender(rs.getString("gender"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMath(rs.getInt("math"));
				vo.setTotalSum(rs.getInt("totalSum"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, con);
		}
		
		return list;
	}
	
	//반별통계
	public static List<TeacherScore> getTeacherScore() {
		List<TeacherScore> list = new ArrayList();
		
		String sql = " SELECT  A.syear, A.sclass, min(tname) as tname\r\n" + 
				"    , sum(kor) as sumKor, sum(eng) as sumEng, sum(math) as sumMath\r\n" + 
				"    , avg(kor) as avgKor, avg(eng) as avgEng, avg(math) as avgMath\r\n" + 
				"    FROM tbl_dept_201905 A\r\n" + 
				"    INNER JOIN tbl_score_201905 B\r\n" + 
				"    ON A.syear = B.syear\r\n" + 
				"    AND A.sclass = B.sclass\r\n" + 
				"    GROUP BY A.syear, A.sclass";
		
		
		
		return list;
	}
}




















