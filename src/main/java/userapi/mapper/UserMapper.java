package userapi.mapper;

import userapi.dao.entity.UserEntity;
import userapi.model.request.PatchUserRequest;
import userapi.model.request.UpdateUserRequest;
import userapi.model.request.UserRequest;
import userapi.model.response.UserResponse;

import java.util.List;

public class UserMapper {

    public static UserResponse buildUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .age(userEntity.getAge())
                .email(userEntity.getEmail())
                .birthPlace(userEntity.getBirthPlace())
                .build();
    }

    public static UserEntity buildUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .email(userRequest.getEmail())
                .birthPlace(userRequest.getBirthPlace())
                .build();

    }

    public static void buildUpdateUserRequest(UserEntity userEntity, UpdateUserRequest updateUserRequest) {
        userEntity.setName(updateUserRequest.getName());
        userEntity.setBirthPlace(updateUserRequest.getBirthPlace());
        userEntity.setEmail(updateUserRequest.getEmail());
        userEntity.setAge(updateUserRequest.getAge());

    }

    public static void buildPatchUserRequest(UserEntity userEntity,
                                             PatchUserRequest patchUserRequest) {
        userEntity.setEmail(patchUserRequest.getEmail());
        userEntity.setAge(patchUserRequest.getAge());

    }


}
