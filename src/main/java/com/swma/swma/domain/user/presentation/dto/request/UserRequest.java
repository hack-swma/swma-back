package com.swma.swma.domain.user.presentation.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.swma.swma.domain.user.entity.type.LanguageType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank
    private String img;

    @NotBlank
    @Size(min = 20, max = 500)
    private String description;

    private List<LanguageType> language;

}
