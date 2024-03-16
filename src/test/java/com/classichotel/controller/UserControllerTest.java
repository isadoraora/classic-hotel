package com.classichotel.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.classichotel.model.AddressDTO;
import com.classichotel.model.UserDTO;
import com.classichotel.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private UserDTO userDTO;

  @BeforeEach
  void setUp() {
    userDTO = new UserDTO(1L, "USA", "76589766544", "ARDF4578937", "John Doe", "26/01/1987",
        new AddressDTO(2L, "5th avenue", "New York", "New York", "USA", "7657565NY"), "44326678889", "doe@hotmail.com");
  }

  @Test
  void createUser_ShouldReturnNewUser() {
    given(userService.createUser(any(UserDTO.class))).willReturn(userDTO);
    ResponseEntity<UserDTO> response = userController.createUser(userDTO);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(userDTO);
  }

  @Test
  void getAllUsers_ShouldReturnUsersList() {
    given(userService.findAllUsers()).willReturn(Arrays.asList(userDTO));
    ResponseEntity<List<UserDTO>> response = userController.getAllUsers();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).containsExactly(userDTO);
  }

  @Test
  void getUserById_ShouldReturnUser() {
    given(userService.findUserById(1L)).willReturn(userDTO);
    ResponseEntity<UserDTO> response = userController.getUserById(1L);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(userDTO);
  }

  @Test
  void updateUser_ShouldReturnUpdatedUser() {
    given(userService.updateUser(eq(1L), any(UserDTO.class))).willReturn(userDTO);
    ResponseEntity<UserDTO> response = userController.updateUser(1L, userDTO);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(userDTO);
  }

  @Test
  void deleteUser_ShouldReturnNoContent() {
    doNothing().when(userService).deleteUser(1L);
    ResponseEntity<Void> response = userController.deleteUser(1L);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    verify(userService, times(1)).deleteUser(1L);
  }
}
