package by.vitstep.organizer.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTxRequestDto {
    Long friendId;

    Long sourceAccountId;

    Long targetAccountId;

    Float amount;


}
