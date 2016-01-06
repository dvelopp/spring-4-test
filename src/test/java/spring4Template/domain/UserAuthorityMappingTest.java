package spring4Template.domain;

import spring4Template.domain.entities.UserAuthority;

import static org.assertj.core.api.Assertions.assertThat;
import static spring4Template.domain.entities.UserAuthorityFixture.createDefaultUserAuthority;

public class UserAuthorityMappingTest extends MappingTest<UserAuthority> {

    @Override
    protected UserAuthority getEntityToSave() {
        return createDefaultUserAuthority();
    }

    @Override
    protected void performChecks(UserAuthority expected, UserAuthority actual) {
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }

    @Override
    protected Class<UserAuthority> getEntityClass() {
        return UserAuthority.class;
    }
}
