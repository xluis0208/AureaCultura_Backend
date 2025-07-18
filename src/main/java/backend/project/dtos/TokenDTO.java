package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Commentario

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TokenDTO {
    private String jwtToken;
    private Long userId;
    private String authorities;
}
