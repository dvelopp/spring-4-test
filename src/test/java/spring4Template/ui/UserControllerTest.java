package spring4Template.ui;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static spring4Template.ui.UserController.USER_LIST_VIEW;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks private UserController testee;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(testee).build();
    }

    @Test
    public void list_ListViewHasBeenReturned(){
        String actualView = testee.list();

        Assert.assertThat(actualView, equalTo(USER_LIST_VIEW));
    }


    @Test
    public void list_UseAssertJ_ListViewHasBeenReturned(){
        String actualView = testee.list();

        Assertions.assertThat(actualView).isEqualTo(USER_LIST_VIEW);
    }

    @Test
    public void list_UseMockMvc_ListViewHasBeenReturned() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name(USER_LIST_VIEW));
    }

}