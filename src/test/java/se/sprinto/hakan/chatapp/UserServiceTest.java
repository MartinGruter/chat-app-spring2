package se.sprinto.hakan.chatapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.UserRepository;
import se.sprinto.hakan.chatapp.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void testLogin(){
        User fakeUser = new User("Martin", "Hejsvejs");
        when(userRepository.findByUsernameAndPassword("Martin", "Hejsvejs"))
                .thenReturn(fakeUser);

        User result = userService.login("Martin", "Hejsvejs");

        assertNotNull(result);
        assertEquals("Martin", result.getUsername());
        assertEquals("Hejsvejs", result.getPassword());
    }

    @Test
    void testRegister(){
        User userToSave = new User("Martin", "Hejsvejs");
        when(userRepository.save(userToSave))
                .thenReturn(userToSave);

        User savedUser = userService.register(userToSave);

        assertNotNull(savedUser);
        assertEquals("Martin", savedUser.getUsername());
        assertEquals("Hejsvejs", savedUser.getPassword());
    }
}
