package userapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class UpdateUserRequest {
    private String name;

    private String birthPlace;

    private String  email;

    private Integer age;
}
