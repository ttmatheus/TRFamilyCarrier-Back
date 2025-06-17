package br.com.project.TRFamilia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    // Add other fields from User that you want to expose
}