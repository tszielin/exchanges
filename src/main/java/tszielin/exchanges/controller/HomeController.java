package tszielin.exchanges.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tszielin.exchanges.currencylayer.CurrenciesRequest;
import tszielin.exchanges.currencylayer.Request;
import tszielin.exchanges.dao.UserDAO;
import tszielin.exchanges.domain.Address;
import tszielin.exchanges.domain.User;

/**
 * Web controller
 * 
 * @author Thomas Zielinski
 * @since 2015-11-29
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserDAO userDAO;    

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/rates/{type}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable("type") String type) {
        logger.debug("Get {} currencies.", type);
        Request request = "all".equalsIgnoreCase(type) ? new Request() : 
            new CurrenciesRequest(new String[] {
                    "EUR","PLN","CAD","GBP","CZK","CHF","SEK","DKK","IRL","HUF","ZAR"}); 
        model.addAttribute("exchanges", request.getResponse());
        return "rates";
    }

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        logger.debug("Main screen");
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", error.trim().isEmpty() ? "Invalid username or password" : error);
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        logger.debug("Register new user.");
        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "firstname", required = true) String firstname,
            @RequestParam(value = "lastname", required = true) String lastname,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "birthday", required = true) Object birthday,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "address", required = true) String street,
            @RequestParam(value = "town", required = true) String town,
            @RequestParam(value = "pobox", required = false) String pobox,
            @RequestParam(value = "country", required = true) String country) {
        logger.debug("Register new user.");
        
        ModelAndView model = new ModelAndView();
            model.setViewName("login");
        User user = new User(username, password, firstname, lastname, email, phone, 
                toDate(birthday), new Address(street, pobox, town, country));
        try {
            userDAO.save(user);
            logger.debug("{} stored.");
            model.addObject("msg", "Log in with your credentials.");
        } catch (Exception ex) {
            model.addObject("error", ex.toString());
        }
        return model;
    }
    
    private Date toDate(Object obj) {
        if (obj instanceof Date) {
            return (Date)obj;
        }
        if (obj instanceof String) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse((String)obj);
            } catch (ParseException ignored) {
                
            }
        }
        return new Date();
    }
}
