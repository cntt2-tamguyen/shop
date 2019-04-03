package vn.edu.leading.shop.services;

import org.springframework.stereotype.Service;
import vn.edu.leading.shop.models.UserModel;
import vn.edu.leading.shop.repositories.UserRepository;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {

    private final UserRepository userRepository;

    public UserServicelmpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserModel> search(String term) {
        return userRepository.findAllByUsernameContaining(term);
    }

    @Override
    public UserModel findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean update(UserModel user) {
        UserModel userModel = userRepository.findById(user.getId()).orElse(null);
        if (userModel == null)
            return false;
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(UserModel user) {
        userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        UserModel userModel = userRepository.findById(id).orElse(null);
        if (userModel == null)
            return false;
        userRepository.delete(userModel);
        return true;
    }
}
