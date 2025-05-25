package top.keir.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.keir.mybatis.MybatisPlusApplicationTests;
import top.keir.mybatis.entity.User;

import java.util.List;

@Slf4j
class UserMapperTest extends MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }

    @Test
    public void selectList() {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).in(User::getId, 1, 2, 3);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    public void delete() {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).in(User::getId, 1, 2, 3);
        userMapper.delete(queryWrapper);
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("keir");
        user.setAge(18);
        user.setEmail("keir@163.com");
        userMapper.insert(user);
        userMapper.selectList(null).forEach(System.out::println);
    }

}