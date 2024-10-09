package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;
    User user = new User("f1", "l1", new Date(), Role.ADMINISTRATEUR);
    List<User> listUsers = new ArrayList<User>() {
        {
            add(new User("f2", "l2", new Date(), Role.ADMINISTRATEUR));
            add(new User("f3", "l3", new Date(), Role.ADMINISTRATEUR));
        }
    };
    @Test
    public void testRetrieveUser() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
        User user1 = userService.retrieveUser("2");
        Assertions.assertNotNull(user1);
    }

}
