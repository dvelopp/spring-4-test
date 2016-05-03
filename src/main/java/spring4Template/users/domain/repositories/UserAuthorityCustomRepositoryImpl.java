package spring4Template.users.domain.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring4Template.users.domain.entities.UserAuthority;

@Repository
public class UserAuthorityCustomRepositoryImpl implements UserAuthorityCustomRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public UserAuthority findById(String userId) {
        return sessionFactory.getCurrentSession().get(UserAuthority.class, userId);
    }
}
