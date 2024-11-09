package org.mysecurityproject.springsecurityproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
