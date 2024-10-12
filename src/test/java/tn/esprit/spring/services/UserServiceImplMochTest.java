package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

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

   /* @Test
    public void testRetrieveAllUsers() {
        // Mocking the findAll() method to return listUsers
        when(userRepository.findAll()).thenReturn(listUsers);

        List<User> users = userService.retrieveAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }*/

    @Test
    public void testAddUser() {
        // Mocking save() method to return the input user
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.addUser(user);
        assertNotNull(savedUser);
        assertEquals("f1", savedUser.getFirstName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        // Mocking save() method to return the updated user
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user);
        assertNotNull(updatedUser);
        assertEquals("f1", updatedUser.getFirstName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        // Deleting a user with a valid ID
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUser("1");
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testRetrieveUser() {
        // Mocking findById() method to return an Optional user
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User retrievedUser = userService.retrieveUser("1");
        assertNotNull(retrievedUser);
        assertEquals("f1", retrievedUser.getFirstName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveUser_NotFound() {
        // Mocking findById() to return an empty Optional
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        User retrievedUser = userService.retrieveUser("1");
        assertNull(retrievedUser);
        verify(userRepository, times(1)).findById(1L);
    }
}
