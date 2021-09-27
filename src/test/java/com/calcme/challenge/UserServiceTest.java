package com.calcme.challenge;

import com.calcme.challenge.model.User;
import com.calcme.challenge.repository.UserRepository;
import com.calcme.challenge.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    private UserRepository repository;

    @Test
    void shouldSaveAllUsers(){
        User user = createUser();

        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = service.save(user);

        Assert.assertEquals(user.getId(), savedUser.getId());
        Assert.assertEquals(user.getName(), savedUser.getName());
        Assert.assertEquals(user.getPhone(), savedUser.getPhone());
        Assert.assertEquals(user.getEmail(), savedUser.getEmail());

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void shouldReturnAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(createUser());

        Mockito.when(repository.findAll()).thenReturn(users);

        service.findAll();

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    private User createUser(){
        return new User("000", "Bruno Couto Matsuo", "brunocomat@gmail.com", "1199999999");
    }

}
