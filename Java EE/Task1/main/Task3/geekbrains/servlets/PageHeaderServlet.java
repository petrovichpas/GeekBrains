package ru.geekbrains.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/page_header")
public class PageHeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<title>" + req.getAttribute("title") + "</title>");
        resp.getWriter().println("<a href=" + getServletContext().getContextPath() + "/main>Главная</a>");
        resp.getWriter().println("<a href=" + getServletContext().getContextPath() + "/catalog>Каталог</a>");
        resp.getWriter().println("<a href=" + getServletContext().getContextPath() + "/product>Продукт</a>");
        resp.getWriter().println("<a href=" + getServletContext().getContextPath() + "/cart>Корзина</a>");
        resp.getWriter().println("<a href=" + getServletContext().getContextPath() + "/order>Заказы</a>");
    }
}
