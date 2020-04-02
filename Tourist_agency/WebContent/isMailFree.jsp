<%@ page import="java.sql.*"%>
<%
	String email = request.getParameter("email");
	if (email.contains("@") && email.contains(".")) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/touristagency?useUnicode=true&allowPublicKeyRetrieval=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&useSSL=false",
					"root", "1qwerty");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM touristagency.users WHERE user_mail = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				out.println("<img width=2% src='http://drive.google.com/uc?export=view&id=1E37G0iP49sPJBG1iBwj_RsZ3Y3Id8OPK'/>");
			} else {
				out.print("<img width=2% src='http://drive.google.com/uc?export=view&id=16rPl29BkBGolXfqsD5unSwzqZZ1l5-JM'/>");
			}
		} catch (Exception e) {
			out.print(e);
		}
	} else if (email.trim().isEmpty()){
		out.print("");
	} else {
		out.print("Invalid email!");
	}
%>
