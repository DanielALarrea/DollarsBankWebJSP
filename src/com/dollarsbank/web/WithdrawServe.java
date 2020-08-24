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

/**
 * Servlet implementation class WithdrawServe
 */
@WebServlet("/WithdrawServe")
public class WithdrawServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServe() {
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
		String withdrawString = request.getParameter("withdraw");
		float withdraw = 0.0f;
		
		String errorMessage = ErrorUtility.errorWithdrawPrefix();
		
		String destination = "";
		
		Account account = InputCheckUtility.accountLookUp(userid);
		
		if(InputCheckUtility.isFloat(withdrawString) && InputCheckUtility.isPositiveNumber(Float.parseFloat(withdrawString))) {
			withdraw = Float.parseFloat(withdrawString);
			if(InputCheckUtility.isValidWithdraw(withdraw, account)) {
				destination = "home.jsp";
				WebAppController.withdrawFromAccount(withdraw, account);
			} else {
				errorMessage += ErrorUtility.errorNotEnough();
				destination = "withdraw.jsp";
				request.setAttribute("error", errorMessage);
			}
		} else {
			errorMessage += ErrorUtility.errorNotPositive();
			destination = "withdraw.jsp";
			request.setAttribute("error", errorMessage);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
		requestDispatcher.forward(request, response);
	}

}
