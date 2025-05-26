package top.keir.mybatis.entity;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@TableName("material_relation")
public class MaterialRelation {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String code;
    private String relation;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @TableLogic(value = "0", delval = "1")
    private Integer isDeleted;

    public MaterialRelation(String code, String relation) {
        this.code = code;
        this.relation = relation;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
