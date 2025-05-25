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
        Set<Integer> list0 = Set.of(1, 2, 3, 4, 5);
        Set<Integer> list1 = Set.of(1, 2, 3, 4, 5);
        System.out.println("list0.equals(list1) = " + list0.equals(list1));
        Set<Integer> list2 = Set.of(1, 2, 3, 4, 6);
        // 新增
        Set<Integer> addSet = list2.stream().filter(item -> !list0.contains(item)).collect(Collectors.toSet());
        // 删除
        Set<Integer> delSet = list0.stream().filter(item -> !list2.contains(item)).collect(Collectors.toSet());
        log.info("addSet = {}, delSet = {}", addSet, delSet);

    }

}
