package userapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import userapi.model.criteria.PageCriteria;
import userapi.model.criteria.UserCriteria;
import userapi.model.request.PatchUserRequest;
import userapi.model.request.UpdateUserRequest;
import userapi.model.request.UserRequest;
import userapi.model.response.PageableUserResponse;
import userapi.model.response.UserResponse;
import userapi.service.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public PageableUserResponse getUsers(PageCriteria pageCriteria,
                                       UserCriteria userCriteria ) {
        return userService.getUsers(pageCriteria,userCriteria);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);

    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable Long id,
                           @RequestBody UpdateUserRequest request) {
        userService.updateUser(id, request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void partialUpdate(@PathVariable Long id,
                              @RequestBody PatchUserRequest request){
        userService.partialUpdate(id,request);
    }

}
