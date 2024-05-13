package service;

import model.User;
import repository.UserRepository;
import model.dto.LoginUserDto;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(LoginUserDto loginUserDto) {
        User user = userRepository.findUserByUsername(loginUserDto.getUsername());
        if (user != null) {
            return PasswordHasher.compareSaltedHash(loginUserDto.getPassword(), user.getSalt(), user.getPasswordHash());
        }
        return false;
    }
}
