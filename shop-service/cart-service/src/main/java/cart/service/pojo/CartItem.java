package cart.service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuangGuiZhao
 * @Date 2019/3/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable{

    private Long productId;
    private Integer count;
    private Date updateTime;


}
