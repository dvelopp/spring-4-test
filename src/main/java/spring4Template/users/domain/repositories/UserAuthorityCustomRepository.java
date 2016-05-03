package spring4Template.users.domain.repositories;

import spring4Template.users.domain.entities.UserAuthority;

public interface UserAuthorityCustomRepository {

    UserAuthority findById(String userId);
}
