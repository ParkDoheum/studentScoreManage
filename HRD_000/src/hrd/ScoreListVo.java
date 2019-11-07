package hrd;

public class ScoreListVo {
	private String pk;
	private String sname;
	private String gender;
	private int kor;
	private int eng;
	private int math;
	private int totalSum;
	private double totalAvg;
	
	public ScoreListVo(String pk, String sname, String gender, int kor, int eng, int math, int totalSum,
			double totalAvg) {
		super();
		this.pk = pk;
		this.sname = sname;
		this.gender = gender;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.totalSum = totalSum;
		this.totalAvg = totalAvg;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}

	public double getTotalAvg() {
		return totalAvg;
	}

	public void setTotalAvg(double totalAvg) {
		this.totalAvg = totalAvg;
	}
}
