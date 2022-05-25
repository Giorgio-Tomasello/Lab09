package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries(int anno) {

		String sql = "SELECT c.ccode as code, c.StateAbb as abb, c.StateNme as name FROM country c, contiguity b "
				+ "WHERE c.ccode=b.state1no OR c.ccode=b.state2no AND b.year<=? ORDER BY c.StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Country c = new Country(rs.getString("abb"), rs.getInt("code"), rs.getString("name"));
				result.add(c);
					
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno, Map<Integer,Country> mappa) {
		
		
		String sql = "SELECT * FROM contiguity WHERE conttype=1 AND year<=?";
		
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Border b = new Border(rs.getInt("dyad"),rs.getInt("state1no"), rs.getString("state1ab"),
						rs.getInt("state2no"), rs.getString("state2ab"), 
						rs.getInt("year"), rs.getInt("conttype"), rs.getDouble("version") );
				
				result.add(b);
				
				
					
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
				

	}
}
