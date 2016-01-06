package spring4Template.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import spring4Template.Application;
import spring4Template.domain.entities.Identifiable;

@WebAppConfiguration
@Rollback(value = true)
@Transactional(transactionManager = "txManager")
@TestPropertySource(locations="classpath:test.properties")
@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class HibernateIntegrationTest<Entity extends Identifiable> {

    @Autowired private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    protected void save(Entity entity){
        getSession().save(entity);
    }

    protected Entity getById(String entityId){
        return getSession().get(getEntityClass(), entityId);
    }

    protected abstract Class<Entity> getEntityClass();

}
