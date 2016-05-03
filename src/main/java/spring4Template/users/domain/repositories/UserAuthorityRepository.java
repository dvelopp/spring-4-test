package spring4Template.users.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring4Template.users.domain.entities.UserAuthority;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String>, UserAuthorityCustomRepository {

}
