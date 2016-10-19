package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
@RequestMapping(value = "/users")
@Controller
public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    @Autowired
    private AdminRestController adminController;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        response.sendRedirect("meals");
    }

/*    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
       // model.addAttribute("users", service.getAll());
        return "/users";
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(Model model) throws ServletException, IOException {


        model.addAttribute("users", adminController.getAll());
        return "/users";
    }
}
