package com.classichotel.service;

import com.classichotel.entity.User;
import com.classichotel.mapper.UserMapper;
import com.classichotel.model.UserDTO;
import com.classichotel.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public UserDTO createUser(UserDTO userDTO) {
    User user = UserMapper.toEntity(userDTO);
    user = userRepository.save(user);
    return UserMapper.fromEntity(user);
  }

  @Transactional(readOnly = true)
  public List<UserDTO> findAllUsers() {
    return userRepository.findAll().stream().map(UserMapper::fromEntity).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public UserDTO findUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    return UserMapper.fromEntity(user);
  }

  @Transactional
  public UserDTO updateUser(Long id, UserDTO userDTO) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found");
    }
    User user = UserMapper.toEntity(userDTO);
    user.setId(id);
    user = userRepository.save(user);
    return UserMapper.fromEntity(user);
  }

  @Transactional
  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found");
    }
    userRepository.deleteById(id);
  }
}
