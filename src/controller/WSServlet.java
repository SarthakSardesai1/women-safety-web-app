package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Handler.WS_Handler;

/**
 * Servlet implementation class WSServlet
 */
public class WSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String callFlag=request.getParameter("callFlag");
		System.out.println("---callFlag---"+callFlag);
		if(callFlag.equals("registerData")){
		String name=request.getParameter("Name");
		System.out.println("Name---"+name);
		
		String mob=request.getParameter("mob");
		System.out.println("number---"+mob);
		
		String pass=request.getParameter("pass");
		System.out.println("pass---"+pass);
		
		String num1=request.getParameter("num1");
		System.out.println("num1---"+num1);
		
		String num2=request.getParameter("num2");
		System.out.println("num2---"+num2);
		
		String num3=request.getParameter("num3");
		System.out.println("num3---"+num3);
		WS_Handler data=new WS_Handler();
		System.out.println("name---"+name);
		System.out.println("mob---"+mob);
		String returnData=data.saveData(name,mob,pass,num1,num2,num3);
		out.write(returnData);
		
		}else if(callFlag.equals("loginVerifyData"))
		{
			String cust_mob=request.getParameter("cust_mob");
			System.out.println("cust_mob---"+cust_mob);
			
			String cust_pwd=request.getParameter("cust_pwd");
			System.out.println("cust_pwd---"+cust_pwd);
			WS_Handler handler=new WS_Handler();
			String data=handler.loginVerifyData(cust_mob, cust_pwd);
			System.out.println("data---"+data);
			out.write(data);
		}
		
		else if(callFlag.equals("getReceiptNumbers"))
		{
			String cust_mob=request.getParameter("cust_mob");
			System.out.println("cust_mob---"+cust_mob);
			
			WS_Handler handler=new WS_Handler();
			String data=handler.getReceiptNumbers(cust_mob);
			System.out.println("data---"+data);
			out.write(data);
		}

	}

}


