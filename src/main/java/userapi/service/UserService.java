package userapi.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import userapi.dao.entity.UserEntity;
import userapi.dao.repository.UserRepository;
import userapi.mapper.UserMapper;
import userapi.model.criteria.PageCriteria;
import userapi.model.criteria.UserCriteria;
import userapi.model.request.PatchUserRequest;
import userapi.model.request.UpdateUserRequest;
import userapi.model.request.UserRequest;
import userapi.model.response.PageableUserResponse;
import userapi.model.response.UserResponse;
import userapi.service.specification.UserSpecification;

import java.util.List;
import java.util.stream.Collectors;

import static userapi.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public PageableUserResponse getUsers(PageCriteria pageCriteria,
                                         UserCriteria userCriteria) {

        var userPages = userRepository.findAll(
                new UserSpecification(userCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount())
        );


        return mapToPageableResponse(userPages);
    }

    public UserResponse getUserById(Long id) {
        var userEntity = fetchUserIfExist(id);
        return buildUserResponse(userEntity);


    }

    public void saveUser(UserRequest userRequest) {
        userRepository.save(buildUserEntity(userRequest));
    }

    public void updateUser(Long id, UpdateUserRequest request) {
        var user = fetchUserIfExist(id);
        buildUpdateUserRequest(user, request);
        userRepository.save(user);
    }

    public void partialUpdate(Long id, PatchUserRequest request) {
        var user = fetchUserIfExist(id);
        buildPatchUserRequest(user, request);
        userRepository.save(user);
    }


    private UserEntity fetchUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("USER_NOT_FOUND")
        );
    }

    private PageableUserResponse mapToPageableResponse(Page<UserEntity> userEntityPage) {
        return PageableUserResponse.builder()
                .users(userEntityPage.getContent().stream().map(UserMapper::buildUserResponse).collect(Collectors.toList()))
                .hasNextPage(userEntityPage.hasNext())
                .totalElements(userEntityPage.getTotalElements())
                .lastPageNumber(userEntityPage.getTotalPages())
                .build();
    }
}
