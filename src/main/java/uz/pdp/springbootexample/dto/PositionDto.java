package uz.pdp.springbootexample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PositionDto {
    private Integer id;
    @NotNull
    @NotBlank
    @NotEmpty(message = "Ubu narsa yoz baraka topkur....")
    private String name;

    @NotNull(message = "Position tanlash shart!!!")
    private String  description;
}