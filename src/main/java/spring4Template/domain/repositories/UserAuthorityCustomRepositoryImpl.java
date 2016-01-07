package spring4Template.domain.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring4Template.domain.entities.UserAuthority;

@Repository
public class UserAuthorityCustomRepositoryImpl implements UserAuthorityCustomRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void save(UserAuthority userAuthority) {
        sessionFactory.getCurrentSession().save(userAuthority);
    }

    @Override
    public UserAuthority findById(String userId) {
        return sessionFactory.getCurrentSession().get(UserAuthority.class, userId);
    }
}
