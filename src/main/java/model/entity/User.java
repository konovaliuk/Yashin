package model.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String surname;
    private String phone;

    private String email;
    private String password;

    private Boolean admin;

}
