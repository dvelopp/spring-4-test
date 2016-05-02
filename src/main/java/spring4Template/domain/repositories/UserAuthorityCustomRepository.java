package spring4Template.domain.repositories;

import spring4Template.domain.entities.UserAuthority;

public interface UserAuthorityCustomRepository {

    UserAuthority findById(String userId);
}
