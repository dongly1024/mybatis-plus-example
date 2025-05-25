package top.keir.mybatis.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.keir.mybatis.MybatisPlusApplicationTests;
import top.keir.mybatis.entity.MaterialRelation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
class MaterialRelationMapperTest extends MybatisPlusApplicationTests {

    @Autowired
    MaterialRelationMapper mapper;

    @BeforeEach
    void setUp() {
        mapper.selectList(null).stream()
                .map(MaterialRelation::toString).forEach(log::info);
    }


    @Test
    public void addG() {
        // A + G
        List<String> codes = List.of("A", "G");
        // 判断添加是元素是否存在，不存在则添加
        List<MaterialRelation> existCodes = mapper.selectList(Wrappers.lambdaQuery(MaterialRelation.class)
                .in(MaterialRelation::getCode, codes));
        Map<String, String> relationMap = existCodes.stream()
                .collect(Collectors.toMap(MaterialRelation::getCode, MaterialRelation::getRelation));
        if (!Objects.equals(codes.size(), existCodes.size())) {
            codes.forEach(code -> {
                if (!relationMap.containsKey(code)) {
                    mapper.insert(new MaterialRelation(code, code));
                }
            });
        }
        // 将A和D的关系查出来，并获取并集
        Set<String> relations = existCodes
                .stream().map(MaterialRelation::getRelation)
                .filter(StrUtil::isNotBlank)
                .flatMap(relation -> Arrays.stream(relation.split(",")))
                .collect(Collectors.toSet());
        relations.addAll(codes);
        // 3.所有的进行更新保存
        mapper.update(Wrappers.lambdaUpdate(MaterialRelation.class)
                .set(MaterialRelation::getRelation, String.join(",", relations))
                .in(MaterialRelation::getCode, relations));
    }

    @Test
    public void add() {
        // A + D
        List<String> codes = List.of("A", "D");
        // 1.将A和D的关系查出来，并获取并集
        Set<String> relations = mapper.selectList(Wrappers.lambdaQuery(MaterialRelation.class)
                        .select(MaterialRelation::getRelation)
                        .in(MaterialRelation::getCode, codes))
                .stream().map(MaterialRelation::getRelation)
                .filter(StrUtil::isNotBlank)
                .flatMap(relation -> Arrays.stream(relation.split(",")))
                .collect(Collectors.toSet());
        relations.addAll(codes);
        log.info("relations并集: {}", relations);
        // 3.所有的进行更新保存
        String relation = String.join(",", relations);
        mapper.update(Wrappers.lambdaUpdate(MaterialRelation.class)
                .set(MaterialRelation::getRelation, relation)
                .in(MaterialRelation::getCode, relations));
    }

    @Test
    public void delete() {
        // A - B
        // 移除B所有的关系，更新其余的关系
        String delCode = "B";
        mapper.delete(Wrappers.lambdaQuery(MaterialRelation.class).eq(MaterialRelation::getCode, "B"));
        // 获取A的关系,并移除无用B
        String relation = mapper.selectOne(Wrappers.lambdaQuery(MaterialRelation.class)
                .select(MaterialRelation::getRelation)
                .in(MaterialRelation::getCode, "A")).getRelation();
        Set<String> codes = Arrays.stream(relation.split(",")).collect(Collectors.toSet());
        codes.remove(delCode);
        log.info("codes: {}", codes);
        mapper.update(Wrappers.lambdaUpdate(MaterialRelation.class)
                .in(MaterialRelation::getCode, codes)
                .set(MaterialRelation::getRelation, String.join(",", codes)));
    }

    @Test
    public void deleteF() {
        // A - F
        // 移除B所有的关系，更新其余的关系
        String code = "A";
        String delCode = "F";
        mapper.delete(Wrappers.lambdaQuery(MaterialRelation.class).eq(MaterialRelation::getCode, delCode));
        // 获取A的关系,并移除无用B
        MaterialRelation relation1 = mapper.selectOne(Wrappers.lambdaQuery(MaterialRelation.class)
                .select(MaterialRelation::getRelation)
                .in(MaterialRelation::getCode, code));
        String relation = relation1.getRelation();
        Set<String> codes = Arrays.stream(relation.split(",")).collect(Collectors.toSet());
        codes.remove(delCode);
        mapper.update(Wrappers.lambdaUpdate(MaterialRelation.class)
                .in(MaterialRelation::getCode, codes)
                .set(MaterialRelation::getRelation, String.join(",", codes)));
    }

    @Test
    public void testInsert() {
        MaterialRelation materialRelation = new MaterialRelation();
        materialRelation.setCode("D");
        materialRelation.setRelation("E,F");
        mapper.insert(materialRelation);
    }
}