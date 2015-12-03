package tszielin.exchanges.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import tszielin.exchanges.dao.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/META-INF/spring/spring-security.xml","classpath*:/META-INF/spring/spring-context.xml"})
@WebAppConfiguration
public class HomeControllerTestMVC {
    @InjectMocks
    HomeController controller;
    
    @InjectMocks
    UserDAO userDAO;
     
    @Mock
    View mockView;
    
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testRegisterFai() throws Exception {
        this.mockMvc.perform(post("/register")
                .param("username", "demo")
                .param("password", "password")).andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testRegisterOk() throws Exception {
        this.mockMvc.perform(post("/register")
                .param("username", "username")
                .param("password", "password")
                .param("firstname", "firstname")
                .param("lastname", "lastname")
                .param("email", "email")
                .param("birthday", "birthday")
                .param("phone", "phone")
                .param("address", "address")
                .param("town", "town")
                .param("country", "country")).andExpect(status().isOk()).andExpect(view().name("login"));
    }
    
    @Test
    public void testRatesMissingParam() throws Exception {
        this.mockMvc.perform(get("/rates")).andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testRatesMissing() throws Exception {
        this.mockMvc.perform(get("/rates/set")).andExpect(status().isOk()).andExpect(view().name("rates"));
    }
    
    @Test
    public void testLogin() throws Exception {
        this.mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
    }
}
