package top.keir.mybatis.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.keir.mybatis.param.UserParam;

@RestController
@Tag(name = "用户模块")
public class UserApi {

    @Operation(summary = "获取用户信息")
    @PostMapping("/userInfo")
    public ResponseEntity<UserParam> userInfo(@RequestBody UserParam param){
        return ResponseEntity.ok(param);
    }

}
