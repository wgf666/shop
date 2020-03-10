package cart.service.vo;


import entity.TProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuangGuiZhao
 * @Date 2019/3/25
 * 用于页面展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVO implements Serializable{
    private TProduct product;
    private Integer count;
    private Date updateTime;




}
