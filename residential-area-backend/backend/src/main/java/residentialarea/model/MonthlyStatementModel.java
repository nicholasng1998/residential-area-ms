package residentialarea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyStatementModel {
    private int residentId;
    private String name;
    private String email;
    private String phoneNo;
    private String unitNo;
}