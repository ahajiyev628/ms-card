package org.example.spring.model.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageCriteria {
    // if no page number specified in the get request, the default value will be used
    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    private Integer page = 0;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    private Integer count = 5;
}
