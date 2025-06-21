package com.hylle.dto.shelf;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShelfResponseDTO {
    long id;
    String name;
    long bookQuantity;
}
