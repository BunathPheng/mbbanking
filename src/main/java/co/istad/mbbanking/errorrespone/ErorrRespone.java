package co.istad.mbbanking.errorrespone;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class ErorrRespone<T> {
    String code;
    T response;
}
