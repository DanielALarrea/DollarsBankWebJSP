package com.dollarsbank.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dollarsbank.model.Account;
import com.dollarsbank.utility.ErrorUtility;
import com.dollarsbank.utility.InputCheckUtility;

/**
 * Servlet implementation class DepositServe
 */
@WebServlet("/DepositServe")
public class DepositServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServe() {
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
		String depositString = request.getParameter("deposit");
		float deposit = 0.0f;
		
		String errorMessage = ErrorUtility.errorDepositPrefix();
		
		Account account = InputCheckUtility.accountLookUp(userid);
		
		if(InputCheckUtility.isFloat(depositString)) {
			deposit = Float.parseFloat(depositString);
			if(InputCheckUtility.isPositiveNumber(deposit)) {
				
			} else {
				errorMessage += ErrorUtility.errorNotPositive();
			}
		} else {
			errorMessage += ErrorUtility.errorNotPositive();
		}
		
		if(true) {
			String destination = "home.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
			requestDispatcher.forward(request, response);
		} else {
			response.sendRedirect("deposit.jsp");
		}
	}

}
