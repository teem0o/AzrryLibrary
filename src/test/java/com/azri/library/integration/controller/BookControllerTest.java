package com.azri.library.integration.controller;

import com.azri.library.entity.Status;
import com.azri.library.entity.User;
import com.azri.library.security.JWTService;
import com.azri.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerTest {
    public static final String USERNAME = "testuser";
    public static final long BOOK_ID = 10000;
    public static final String API_V_1_BOOK = "/api/v1/book/";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    @Test
    @Sql(statements = {
            "INSERT INTO \"user\" (id, username, password, role, deleted) " +
                    "VALUES (10000, 'testuser', '$2a$10$z4RSdl0PaNMB1GhOLsUREemhn6dXOUaLY65oAHnTSgJR86ijcSMKO', 0, false)",
            "INSERT INTO book (id, title, author, isbn, status, deleted) " +
                    "VALUES (10000, 'Test Book', 'Test Author', '1234567890', 'AVAILABLE', false)"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testBorrowBookEndpoint() throws Exception {
        User user = modelMapper.map(userService.getUserByUsername(USERNAME), User.class);
        String token = jwtService.generateToken(user);
        mockMvc.perform(MockMvcRequestBuilders.patch(API_V_1_BOOK + "borrow/{bookId}", BOOK_ID)
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(BOOK_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Status.BORROWED.toString()));
    }

    @Test
    @Sql(statements = {
            "INSERT INTO \"user\" (id, username, password, role, deleted) " +
                    "VALUES (10000, 'testuser', '$2a$10$z4RSdl0PaNMB1GhOLsUREemhn6dXOUaLY65oAHnTSgJR86ijcSMKO', 0, false)",
            "INSERT INTO book (id, title, author, isbn, status,user_id, deleted) " +
                    "VALUES (10000, 'Test Book', 'Test Author', '1234567890', 'BORROWED',10000, false)"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testReturnBookEndpoint() throws Exception {
        User user = modelMapper.map(userService.getUserByUsername(USERNAME), User.class);
        String token = jwtService.generateToken(user);
        mockMvc.perform(MockMvcRequestBuilders.patch(API_V_1_BOOK + "return/{bookId}", BOOK_ID)
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(BOOK_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Status.AVAILABLE.toString()));
    }

}
