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
import com.dollarsbank.utility.SuccessUtility;

/**
 * Servlet implementation class InformationServe
 */
@WebServlet("/InformationServe")
public class InformationServe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationServe() {
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
		String userid = request.getParameter("userid");
		
		String destination = "";
		
		boolean noErrorPhone = false;
		
		if(InputCheckUtility.isValidPhoneNum(contactNum)) {
			noErrorPhone = true;
		}
		
		if(noErrorPhone) {
			destination = "information.jsp";
			WebAppController.changeCustomerDetails(userid, name, address, contactNum);
			request.setAttribute("success", SuccessUtility.successAccountDetailUpdate());
		} else {
			destination = "infoedit.jsp";
			request.setAttribute("errorPhone", ErrorUtility.errorNotPhone());
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
		requestDispatcher.forward(request, response);
	}

}
