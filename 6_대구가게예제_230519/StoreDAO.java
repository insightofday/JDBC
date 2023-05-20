package Store;

import java.util.ArrayList;
import java.util.List;

import DAO.DAO;

public class StoreDAO extends DAO {
	private static StoreDAO sDAO=null;
	public static StoreDAO getInstance() {
		if(sDAO==null) {
			sDAO=new StoreDAO();
		}
		return sDAO;
	}
	
	//모든가게정보조회
	public List<Store> selectAll() {
		List<Store>list=new ArrayList<>();
		Store store=null;
		try {
			conn();
			String sql="select * from store";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				store=new Store();
				store.setStoreId(rs.getInt("store_id"));
				store.setStoreName(rs.getString("store_name"));
				store.setStoreTel(rs.getString("store_tel"));
				store.setStoreAddr(rs.getString("store_addr"));
				store.setStoreSales(rs.getInt("store_sales"));
				list.add(store);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return list;
	}
	//가게정보입력
	public int insert(Store st) {
		int result=0;
		try {
			conn();
			String sql="insert into Store(store_id,store_name,store_tel,store_addr,store_sales) "
					+ "values((select count(*)+1 from store),?,?,?,?)";//subquerry활용
			
			pstmt=conn.prepareStatement(sql);
			//이거는 기존의 값을 할당해주는 방식pstmt.setInt(1, st.getStoreId());
			//서브쿼리로 값을 자동부여하는 방식, 시퀀스를 활용해서 번호를 자동부여하는 방식 또한 있음
			
			pstmt.setString(1, st.getStoreName());
			pstmt.setString(2, st.getStoreTel());
			pstmt.setString(3, st.getStoreAddr());
			pstmt.setInt(4, st.getStoreSales());
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return result;
	}
	//매출수정
	public int updateSales(Store store) {
		int result = 0;
		try {conn();
			String sql="update store set store_sales=? where store_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, store.getStoreSales());
			pstmt.setInt(2, store.getStoreId());
			result=pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return result;
	}
	
	//지역구별 매출 합계 조회
	//1.java로 처리
	//2.DB로 처리
	public List<Store> getStoreSales(){
		List<Store>list=new ArrayList<>();
		Store store=null;
		try {
			conn();
			String sql="select substr(store_addr,4,3)as location, sum(store_sales) \"loc_Sales\"  from store group by  substr(store_addr,4,3)";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				store= new Store();
				store.setStoreAddr(rs.getString(rs.getString("location")));
				store.setStoreSales(rs.getInt("loc_Sales"));
				list.add(store);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return list;
	}

}
