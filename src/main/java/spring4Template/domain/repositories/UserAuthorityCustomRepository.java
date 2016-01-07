package spring4Template.domain.repositories;

import spring4Template.domain.entities.UserAuthority;

public interface UserAuthorityCustomRepository {

    void save(UserAuthority userAuthority);
    UserAuthority findById(String userId);
}
