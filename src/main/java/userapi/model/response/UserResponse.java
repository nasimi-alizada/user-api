package userapi.model.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String name;

    private String birthPlace;

    private String  email;

    private Integer age;
}
