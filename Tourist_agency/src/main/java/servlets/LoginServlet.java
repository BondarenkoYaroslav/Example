package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.DefaultUserDao;
import dto.UserData;
import utilities.PasswordValidate;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if (DefaultUserDao.getDefaultUserDao().loginVerification(login)) {

			UserData userData = DefaultUserDao.getDefaultUserDao().getUser(login);

			try {
				if (PasswordValidate.passwordHashCheck(password, userData.getUserPassword())) {

					if (userData.getUserRole().equalsIgnoreCase("user")) {
						session.removeAttribute("loginFailed");
						session.setAttribute("id", userData.getUserId());
						session.setAttribute("role", userData.getUserRole());
						getServletContext().getRequestDispatcher("/WEB-INF/views/accountUser.jsp").forward(request,
								response);
					} else if (userData.getUserRole().equalsIgnoreCase("admin")) {
						session.removeAttribute("loginFailed");
						session.setAttribute("id", userData.getUserId());
						session.setAttribute("role", userData.getUserRole());
						getServletContext().getRequestDispatcher("/WEB-INF/views/accountAdmin.jsp").forward(request,
								response);
					}

				} else {
					getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
					session.setAttribute("loginFailed",
							"Wrong password. Try again or click Forgot password to reset it.");
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			}
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			session.setAttribute("loginFailed", "Could not find your account.");
		}
	}
}
