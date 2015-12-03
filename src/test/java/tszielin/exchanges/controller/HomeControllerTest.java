package tszielin.exchanges.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import tszielin.exchanges.currencylayer.Request;

public class HomeControllerTest {
    @InjectMocks
    HomeController controller;

    private ModelAndView mav = new ModelAndView();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHome() {
        Request request = Mockito.mock(Request.class);
        Model model = Mockito.mock(Model.class);
        model.addAttribute("exchanges", request.getResponse());
        
        String viewName = controller.home(model, "all");
        assertEquals("rates", viewName);
    }
    
    @Test
    public void testLogin() {
        mav.setViewName("login");
        assertEquals("login", mav.getViewName());
        assertTrue(mav.getModelMap().isEmpty());
    }

    @Test
    public void testLoginInvalid() {
        mav.addObject("error", "Invalid username or password");
        mav.setViewName("login");
        assertEquals("login", mav.getViewName());
        assertTrue(mav.getModelMap().containsAttribute("error"));
        assertEquals("Invalid username or password", mav.getModelMap().get("error"));
    }
    
    @Test
    public void testRegister() {
        mav.addObject("msg", "Log in with your credentials.");
        mav.setViewName("login");
        assertEquals("login", mav.getViewName());
        assertTrue(mav.getModelMap().containsAttribute("msg"));
        assertEquals("Log in with your credentials.", mav.getModelMap().get("msg"));        
    }
}
