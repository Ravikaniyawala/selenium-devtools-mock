package utilities.DataObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserModel {
    public String email;
    public String password;
    public String banner;
}
