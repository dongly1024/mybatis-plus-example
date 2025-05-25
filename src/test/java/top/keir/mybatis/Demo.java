package top.keir.mybatis;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.data.id.Snowflake;
import org.dromara.hutool.core.text.StrUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Demo {

    @Test
    public void test() {
        String  s = ",a,b,c,";
        List<String> list = Arrays.stream(s.split(",")).filter(StrUtil::isNotBlank).toList();
        String[] split = s.split(",");
        System.out.println(Arrays.toString(split));
    }

}
// 1925742086911107073
// 1925742086911107074
// 1925742086911107075