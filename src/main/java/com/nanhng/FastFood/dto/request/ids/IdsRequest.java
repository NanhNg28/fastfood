package com.nanhng.FastFood.dto.request.ids;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdsRequest {
    @NotNull("ids must not be null")
    @Size(min = 1)
    private List<Integer> ids;
}
