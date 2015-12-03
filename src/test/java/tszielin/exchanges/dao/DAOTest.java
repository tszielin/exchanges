package tszielin.exchanges.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
        "classpath*:/META-INF/spring/spring-security.xml",
        "classpath*:/META-INF/spring/spring-context.xml" 
})
@Transactional
public abstract class DAOTest {
    @Test
    abstract public void testGet();

    @Test
    abstract public void testNotFound();
}
