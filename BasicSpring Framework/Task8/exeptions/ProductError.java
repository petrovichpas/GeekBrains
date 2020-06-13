package ru.geekbrains.springbootlesson.exeptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductError {
    private int status;
    private String message;
    private long timestamp;

    public ProductError(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
