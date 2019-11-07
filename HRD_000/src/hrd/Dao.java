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
				", decode(A.gender, 'F', '남', 'M', '여') as gender\r\n" + 
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
}










