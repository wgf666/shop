package entity;

public class TGoodsInfo {
    private Integer id;

    private String goodsName;

    private String goodsDescription;

    private String goodsPic;

    private Double goodsPrice;

    private Integer goodsStock;

    private Double goodsPriceOff;

    private Double goodsDiscount;

    private Integer goodsFatherid;

    private Integer goodsParentid;

    private String isdelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription == null ? null : goodsDescription.trim();
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic == null ? null : goodsPic.trim();
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Double getGoodsPriceOff() {
        return goodsPriceOff;
    }

    public void setGoodsPriceOff(Double goodsPriceOff) {
        this.goodsPriceOff = goodsPriceOff;
    }

    public Double getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Double goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public Integer getGoodsFatherid() {
        return goodsFatherid;
    }

    public void setGoodsFatherid(Integer goodsFatherid) {
        this.goodsFatherid = goodsFatherid;
    }

    public Integer getGoodsParentid() {
        return goodsParentid;
    }

    public void setGoodsParentid(Integer goodsParentid) {
        this.goodsParentid = goodsParentid;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }
}