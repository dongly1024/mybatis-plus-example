package top.keir.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Demo {

    @Test
    public void test() {
        System.out.println("System.getenv(\"JAVA_HOME\") = " + System.getenv("JAVA_HOME"));
    }

}
