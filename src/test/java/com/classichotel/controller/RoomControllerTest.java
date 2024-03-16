package com.classichotel.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.classichotel.model.RoomDTO;
import com.classichotel.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RoomService roomService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  private RoomDTO roomDTO;

  @BeforeEach
  void setUp() {
    roomDTO = new RoomDTO(1L, "Room Type", 2, 2, "Other Furniture", new BigDecimal("10.00"), 1L);
  }

  @Test
  void createRoom_ShouldReturnNewRoom() throws Exception {
    given(roomService.createRoom(any(RoomDTO.class))).willReturn(roomDTO);

    mockMvc.perform(post("/rooms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(roomDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(roomDTO.id()))
        .andExpect(header().string("Location", containsString("rooms/1"))); // Ajuste o valor esperado conforme necessário
  }

  @Test
  void getAllRooms_ShouldReturnRoomsList() throws Exception {
    given(roomService.getAllRooms()).willReturn(Collections.singletonList(roomDTO));

    mockMvc.perform(get("/rooms")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id").value(roomDTO.id()));
  }

  @Test
  void getRoomById_ShouldReturnRoom() throws Exception {
    given(roomService.getRoomById(roomDTO.id())).willReturn(roomDTO);

    mockMvc.perform(get("/rooms/{id}", roomDTO.id())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(roomDTO.id()));
  }

  @Test
  void updateRoom_ShouldReturnUpdatedRoom() throws Exception {
    given(roomService.updateRoom(any(RoomDTO.class))).willReturn(roomDTO);

    mockMvc.perform(put("/rooms/{id}", roomDTO.id())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(roomDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(roomDTO.id()));
  }

  @Test
  void deleteRoom_ShouldReturnNoContent() throws Exception {
    doNothing().when(roomService).deleteRoom(roomDTO.id());

    mockMvc.perform(delete("/rooms/{id}", roomDTO.id()))
        .andExpect(status().isNoContent());

    verify(roomService, times(1)).deleteRoom(roomDTO.id());
  }

  @Test
  void updateRoom_ShouldReturnBadRequestWhenIdMismatch() throws Exception {
    RoomDTO roomWithDifferentId = new RoomDTO(2L, "Room Type", 2, 2, "table", new BigDecimal("10.00"), 1L);

    mockMvc.perform(put("/rooms/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(roomWithDifferentId)))
        .andExpect(status().isBadRequest());
  }

}
