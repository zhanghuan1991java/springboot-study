package com.didispace.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my")
public class MyServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2347671869961612948L;
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("name>>>"+req.getParameter("name"));
    }
}