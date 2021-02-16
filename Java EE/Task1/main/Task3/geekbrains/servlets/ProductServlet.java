package ru.geekbrains.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.repository.ProductRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ProductServlet", urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) throw new ServletException("ProductRepository not initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("title", "Товары");
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;

            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }

            Product product = productRepository.findById(id);

            if (product == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("title", "Редактирование товара");
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/edit_product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/add")) {
            req.setAttribute("title", "Добавление товара");
            getServletContext().getRequestDispatcher("/WEB-INF/add_product_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")){
            productRepository.deleteById(Long.parseLong(req.getParameter("id")));
            resp.sendRedirect(getServletContext().getContextPath() + "/product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        BigDecimal price;

        if (req.getPathInfo().equals("/edit")){
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
        }

            try {
                price = new BigDecimal(req.getParameter("price"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }

            productRepository.saveOrUpdate(new Product(id, req.getParameter("name"), req.getParameter("description"), price));
            resp.sendRedirect(getServletContext().getContextPath() + "/product");

    }
}
