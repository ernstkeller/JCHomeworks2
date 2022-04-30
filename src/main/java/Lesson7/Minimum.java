package Lesson7;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Minimum {

    @JsonProperty(value = "Value")
    private String value;
    @JsonProperty(value = "Unit")
    private String unit;
    @JsonProperty(value = "UnitType")
    private String unitType;

}
