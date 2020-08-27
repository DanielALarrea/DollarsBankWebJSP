package com.dollarsbank.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dollarsbank.controller.WebAppController;
import com.dollarsbank.utility.ErrorUtility;
import com.dollarsbank.utility.InputCheckUtility;

/**
 * Servlet implementation class RegisterServe
 */
@WebServlet("/RegisterServe")
public class RegisterServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServe() {
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
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String contactNum = request.getParameter("contactNum");
		String userid = request.getParameter("userID");
		String password = request.getParameter("password");
		String initialDepositString = request.getParameter("intialDeposit");
		float initialDeposit = 0.0f;
		
		boolean noErrorNumber = false;
		boolean noErrorUser = false;
		boolean noErrorPhone = false;
		boolean noErrorPassword = false;
		
		String destination = "";
				
		if(InputCheckUtility.isFloat(initialDepositString) && InputCheckUtility.isPositiveNumber(Float.parseFloat(initialDepositString))) {
			initialDeposit = Float.parseFloat(initialDepositString);
			noErrorNumber = true;
		}
		
		if(!InputCheckUtility.isExistingUser(userid)) {
			noErrorUser = true;
		}
		
		if(InputCheckUtility.isValidPhoneNum(contactNum)) {
			noErrorPhone = true;
		}
		
		if(InputCheckUtility.matchesPasswordCriteria(password)) {
			noErrorPassword = true;
		}
		
		if(noErrorNumber && noErrorUser && noErrorPhone && noErrorPassword) {
			destination = "index.jsp";
			WebAppController.createAccount(name, address, contactNum, userid, password, initialDeposit);
		} else {
			destination = "register.jsp";
			if(!noErrorNumber) {
				request.setAttribute("errorNumber", ErrorUtility.errorNotPositive());
			}
			if(!noErrorUser) {
				request.setAttribute("errorUser", ErrorUtility.errorExistingUser());
			}
			if(!noErrorPhone) {
				request.setAttribute("errorPhone", ErrorUtility.errorNotPhone());
			}
			if(!noErrorPassword) {
				request.setAttribute("errorPass", ErrorUtility.errorNotPasswordCriteria());
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
		requestDispatcher.forward(request, response);
	}

}
