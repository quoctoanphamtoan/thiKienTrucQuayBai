package toanKienTruc.entity;

public class BenhNhan {
	private int id;
	private String ten;
	private String benh;
	private String diaChi;
	private String ghiChu;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getBenh() {
		return benh;
	}
	public void setBenh(String benh) {
		this.benh = benh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public BenhNhan(int id, String ten, String benh, String diaChi, String ghiChu) {
		super();
		this.id = id;
		this.ten = ten;
		this.benh = benh;
		this.diaChi = diaChi;
		this.ghiChu = ghiChu;
	}
	public BenhNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BenhNhan [id=" + id + ", ten=" + ten + ", benh=" + benh + ", diaChi=" + diaChi + ", ghiChu=" + ghiChu
				+ "]";
	}
	
	
	
}
