package com.dollarsbank.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dollarsbank.controller.WebAppController;
import com.dollarsbank.model.Account;
import com.dollarsbank.utility.ErrorUtility;
import com.dollarsbank.utility.InputCheckUtility;
import com.dollarsbank.utility.SuccessUtility;

/**
 * Servlet implementation class PasswordServe
 */
@WebServlet("/PasswordServe")
public class PasswordServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordServe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String passwordVerify = request.getParameter("passwordVerify");
		String formPage = request.getParameter("formPage");
		String returnPage = request.getParameter("returnPage");
		
		Account account = new Account();
		
		boolean noErrorPass = false;
		boolean noErrorMatch = false;
		boolean noErrorUser = false;
		
		String destination = "";
		
		if(InputCheckUtility.isExistingUser(userid)) {
			noErrorUser = true;
			account = InputCheckUtility.accountLookUp(userid);
		}
		
		if(InputCheckUtility.matchesPasswordCriteria(password)) {
			noErrorPass = true;
		}
		
		if(password.equals(passwordVerify)) {
			noErrorMatch = true;
		}
		
		if(noErrorUser && noErrorPass && noErrorMatch) {
			destination = returnPage;
			WebAppController.updatePassword(account, password);
			request.setAttribute("success", SuccessUtility.successPasswordUpdate());
		} else {
			destination = formPage;
			if(!noErrorPass) {
				request.setAttribute("errorPass", ErrorUtility.errorNotPasswordCriteria());
			}
			if(!noErrorMatch) {
				request.setAttribute("errorMatch", ErrorUtility.errorPasswordMismatch());
			}
			if(!noErrorUser) {
				request.setAttribute("errorUser", ErrorUtility.errorUserNotFound());
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
		requestDispatcher.forward(request, response);
	}

}
