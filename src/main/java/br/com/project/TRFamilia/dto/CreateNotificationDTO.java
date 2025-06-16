package br.com.project.TRFamilia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNotificationDTO {

    @NotNull
    private Long user;

    @NotBlank
    private String title;

    @NotBlank
    private String message;
 
    @NotBlank
    private String notificationType;

    public CreateNotificationDTO(Long user, String title, String message, String notificationType) {
        this.user = user;
        this.title = title;
        this.message = message;
        this.notificationType = notificationType;
    }
}
