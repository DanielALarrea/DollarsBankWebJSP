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
 * Servlet implementation class TransferServe
 */
@WebServlet("/TransferServe")
public class TransferServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferServe() {
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
		String userTransfer = request.getParameter("userTransfer");
		String transferString = request.getParameter("transfer");
		float transfer = 0.0f;
		
		boolean noErrorNumber = false;
		boolean noErrorUser = false;
		
		String errorMessage = "";
		String errorNumberMessage = "";
		
		String destination = "";
		
		Account account = InputCheckUtility.accountLookUp(userid);
		
		if(InputCheckUtility.isFloat(transferString) && InputCheckUtility.isPositiveNumber(Float.parseFloat(transferString))) {
			transfer = Float.parseFloat(transferString);
			if(InputCheckUtility.isValidWithdraw(transfer, account)) {
				noErrorNumber = true;
			} else {
				errorNumberMessage += ErrorUtility.errorNotEnough();
			}
		} else {
			errorNumberMessage += ErrorUtility.errorNotPositive();
		}
		
		if(InputCheckUtility.isExistingUser(userTransfer)) {
			noErrorUser = true;
		}
		
		
		if(noErrorNumber && noErrorUser) {
			destination = "home.jsp";
			WebAppController.transferFunds(account, userTransfer, transfer);
		} else {
			destination = "transfer.jsp";
			if(!noErrorNumber) {
				request.setAttribute("errorNumber", errorMessage + errorNumberMessage);
			}
			if(!noErrorUser) {
				request.setAttribute("errorUser", errorMessage + ErrorUtility.errorUserNotFound());
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
		requestDispatcher.forward(request, response);
	}

}
