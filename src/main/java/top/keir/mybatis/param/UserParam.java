package top.keir.mybatis.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户参数")
public class UserParam {

    @Schema(description = "用户id", example = "1")
    private Integer userId;

    @Schema(description = "用户名", example = "张三")
    private String username;

    @Schema(description = "用户年龄", example = "18")
    private Integer age;

    @Schema(description = "用户地址",  example = "上海")
    private String address;
}
