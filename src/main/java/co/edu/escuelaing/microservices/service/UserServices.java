package co.edu.escuelaing.microservices.service;

import co.edu.escuelaing.microservices.model.User;
import co.edu.escuelaing.microservices.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServices {

    @Inject
    UserRepository userRepository;
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }
}
