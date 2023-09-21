package ma.cih.stockmanagementbackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusCmd {
    CREATED, PENDING, DELIVERED, CANCELED;

    @JsonCreator
    public static StatusCmd fromString(String value) {
        for (StatusCmd status : StatusCmd.values()) {
            if (status.toString().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.name();
    }
}
