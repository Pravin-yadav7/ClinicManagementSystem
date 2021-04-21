package connection;

import java.sql.*;

public class Select {

	public static ResultSet getData(String query) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionProvider.getCon();
			st = con.createStatement();
			rs = st.executeQuery(query);
			return rs;

		} catch (Exception e) {

			System.out.println(e);
			return null;
		}

	}

}
