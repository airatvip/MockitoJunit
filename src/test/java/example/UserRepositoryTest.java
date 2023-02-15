package example;

import org.example.User;
import org.example.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    User user = new User("ghost", "ghost123");
    User user1 = new User("faster", "forceF");

    @Test
    public void getEmptyUsersList() {
        Assertions.assertEquals(userRepository.getAllUsers(), Collections.EMPTY_LIST);
    }

    @Test
    public void getAllUserWhenCollectionFull() {
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Assertions.assertEquals(userRepository.getAllUsers(), List.of(user, user1));

    }

    @Test
    public void findUserByLoginWhenLoginMatches() {
        userRepository.addUser(user);
        Assertions.assertEquals(userRepository.findUserByLogin("ghost"), user);
    }

    @Test
    public void findUserByLoginWhenLoginDoesNotMatches() {
        userRepository.addUser(user);
        Assertions.assertNull(userRepository.findUserByLogin("dad"));
    }

    @Test
    public void getUserByLoginAndPasswordWhenLoginAndPasswordExists() {
        userRepository.addUser(user);
        Assertions.assertEquals(userRepository.findUserByLoginAndPassword("ghost", "ghost123"), user);
    }

    @Test
    public void findUserByLoginAndPasswordWhenLoginMatchesPasswordDoesNotMatches() {
        userRepository.addUser(user);
        Assertions.assertNull(userRepository.findUserByLoginAndPassword("ghost", "ghost12"));
    }

    @Test
    public void findUserByLoginAndPasswordWhenLoginDoesNotMatchesPasswordMatches() {
        userRepository.addUser(user);
        Assertions.assertNotEquals(userRepository.findUserByLoginAndPassword("ghos", "ghost123"), user);
    }


}