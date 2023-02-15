package example;

import org.example.User;
import org.example.UserRepository;
import org.example.UserService;
import org.example.exception.UserNonUniqueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user = new User("ghost", "ghost123");
    User user1 = new User("faster", "forceF");
    User user2 = new User("", "mock");

    @Test
    public void getAllLogins() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("ghost", "123"), new User("faster", "forceF")));
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Assertions.assertEquals(List.of("ghost", "faster"), userService.getAllLogins());
    }


@Test
void whenLoginIsEmptyThenReturnThrowsException() {
    assertThatThrownBy(() -> userService.addNewUser("", "mock"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Логин не может быть пустым");
    verify(userRepository, new NoInteractions()).getAllUsers();
    verify(userRepository, new NoInteractions()).addUser(any());
}

    @Test
    void whenPasswordIsEmptyThenReturnThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.addNewUser("mock", ""));

    }

    @Test
    public void whenLoginAndPasswordMatchesThenReturnThrowsException () {
        when(userRepository.getAllUsers()).thenReturn(List.of(user));
        assertThatThrownBy(() -> userService.addNewUser("ghost", "ghost123")).
                isInstanceOf(UserNonUniqueException.class)
                .hasMessage("Такой пользователь уже существует");
    }

}