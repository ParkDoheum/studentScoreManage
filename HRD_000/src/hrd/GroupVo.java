package hrd;

public class GroupVo {
	private int sumKor;
	private int sumEng;
	private int sumMath;
	private double avgKor;
	private double avgEng;
	private double avgMath;
	
	public GroupVo(int sumKor, int sumEng, int sumMath, double avgKor, double avgEng, double avgMath) {
		super();
		this.sumKor = sumKor;
		this.sumEng = sumEng;
		this.sumMath = sumMath;
		this.avgKor = avgKor;
		this.avgEng = avgEng;
		this.avgMath = avgMath;
	}
	
	public int getSumKor() {
		return sumKor;
	}
	public void setSumKor(int sumKor) {
		this.sumKor = sumKor;
	}
	public int getSumEng() {
		return sumEng;
	}
	public void setSumEng(int sumEng) {
		this.sumEng = sumEng;
	}
	public int getSumMath() {
		return sumMath;
	}
	public void setSumMath(int sumMath) {
		this.sumMath = sumMath;
	}
	public double getAvgKor() {
		return avgKor;
	}
	public void setAvgKor(double avgKor) {
		this.avgKor = avgKor;
	}
	public double getAvgEng() {
		return avgEng;
	}
	public void setAvgEng(double avgEng) {
		this.avgEng = avgEng;
	}
	public double getAvgMath() {
		return avgMath;
	}
	public void setAvgMath(double avgMath) {
		this.avgMath = avgMath;
	}
	
	
}
