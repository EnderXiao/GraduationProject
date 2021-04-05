package top.ender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Ender-PC
 * @date 2021/2/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String name;
    //autowired先ByType再ByName
    @Autowired
    //qualifier通过ByName匹配
    @Qualifier("address")
    private Address address;
    //爱好
    private String[] hobbies;
    //职务
    private List<String> duties;
    //家庭关系
    private Map<String,String> familyTies;
    //购物车商品
    private Set<String> carts;
    //工作经历
    private Properties workExperience;
    //女儿 null注入
    private String daughter;
}
