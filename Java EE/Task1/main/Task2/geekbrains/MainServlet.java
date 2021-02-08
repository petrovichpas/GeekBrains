package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<html> " +
                "<head>" +
                "<title>Главная страница</title>" +
                "</head>" +
                "<body>" +
                "<a href=/first-web-app/main>Главная</a>" +
                "<a href=/first-web-app/catalog>Каталог</a>" +
                "<a href=/first-web-app/product>Продукт</a>" +
                "<a href=/first-web-app/cart>Корзина</a>" +
                "<a href=/first-web-app/order>Заказы</a>" +
                "</body>" +
                "</html>");

        logger.info("Отправлен ответ от сервлета");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
