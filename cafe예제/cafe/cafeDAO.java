package cafe;

import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class cafeDAO extends DAO {
	
	private static cafeDAO cDAO=null;
	private cafeDAO() {
		
	}
	cafe cafe= new cafe();
	public static cafeDAO getInstance() {
		if(cDAO==null) {
			cDAO=new cafeDAO();
		}return cDAO;
	}
	
	public List<cafe> search() {
		List<cafe> list= new ArrayList<>();
		cafe cafe=null;
		try {
			conn();
			String sql="select * from cafe";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cafe=new cafe();
				cafe.setCoffeeMenu(rs.getString("coffee_menu"));
				cafe.setCoffeePrice(rs.getInt("coffee_price"));
				cafe.setCoffeeExplain(rs.getString("coffee_explain"));
				cafe.setCoffeSales(rs.getInt("coffee_sales"));
				list.add(cafe);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		try {
			conn();
			String sql="update cafe set coffe_sales=0";
			pstmt=conn.prepareStatement(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public cafe searchDetail(String menu) {
		cafe cafe=null;
		try {
			conn();
			String sql="select * from cafe where coffee_menu=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,menu);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cafe=new cafe();
				cafe.setCoffeeMenu(rs.getString("coffee_menu"));
				cafe.setCoffeePrice(rs.getInt("coffee_price"));
				cafe.setCoffeeExplain(rs.getString("coffee_explain"));
				cafe.setCoffeSales(0);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return cafe;
	}
	
	public int add(String menu, int price, String explain) {
		int result=0;
		try {
			conn();
			String sql="insert into cafe(coffee_menu, coffee_price, coffee_explain)"
					+ "values(?,?,?) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, menu);
			pstmt.setInt(2, price);
			pstmt.setString(3, explain);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return result;
	}
	public int sell(String menu) {
		int result=0;
		try {
			conn();
			//String sql="insert into cafe( coffee_sales) values(coffee_sales+1) where coffee_menu=?";
			if(cafe.getCoffeSales()==0) {
				String sql="update cafe set coffee_sales=1 where coffee_menu=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, menu);
				result=pstmt.executeUpdate();
			}
			String sql="update cafe set coffee_sales=coffee_sales+1 where coffee_menu=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, menu);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return result;
	}
	public int deleteMenu(String menu) {
		int result=0;
		try {
			conn();
			String sql="delete from cafe where coffee_menu=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, menu);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return result;
	}
	
	
	public List<cafe> view(){
		List<cafe> list=new ArrayList<>();
		cafe cafe=null;
	
		try {
			conn();
			String sql="select coffee_menu, coffee_price*coffee_sales coffee_price from cafe";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cafe=new cafe();
				cafe.setCoffeeMenu(rs.getString("coffee_menu"));
				cafe.setCoffeePrice(rs.getInt("coffee_price"));
				cafe.setCoffeSales(rs.getInt("coffee_sales"));
				list.add(cafe);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return list;
	}
	
	
	
	
	
	
}
