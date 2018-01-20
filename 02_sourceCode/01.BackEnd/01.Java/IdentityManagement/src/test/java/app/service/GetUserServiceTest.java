package app.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import app.entity.Role;
import app.entity.User;
import app.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class GetUserServiceTest {
    // テスト対象
    @InjectMocks
    private UserService target;

    // Mock
    @Mock
    private UserRepository userRepository;

    /*
     * GetUserService#getAllUser tests.
     */

    @Before
    public void init() {
        // Mockの初期化
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUser_expectAllUser() {
        // Mockが返す値を作成
        final String testName = "永井　健二";
        final String testUserId = "nagai";
        final String testRoleName = "ROLE_ADMIN";
        final String testRoleAlias = "管理者";

        final User expUser = new User();
        expUser.setName(testName);
        expUser.setUserId(testUserId);
        final Role expRole = new Role();
        expRole.setId(1);
        expRole.setName(testRoleName);
        expRole.setAlias(testRoleAlias);
        expUser.setRoleList(Arrays.asList(expRole));
        final List<User> exp = Arrays.asList(expUser);

        // Mockのthis.userRepository.findAll()が呼ばれた際に、上記作成した値を返すように設定する。
        when(this.userRepository.findAll()).thenReturn(exp);

        // テスト対象メソッド実施
        final List<User> act = this.target.getAllUser();

        // 期待と実際を検証する
        assertThat(act.size(), is(exp.size()));

        final User actUser = act.get(0);
        assertThat(actUser.getName(), is(testName));
        assertThat(actUser.getUserId(), is(testUserId));
        assertThat(actUser.getRoleList().get(0), is(expRole));
    }

    @Test
    public void throwTest() {
        final User user = new User();
        this.target.saveUser(user);
    }
}
