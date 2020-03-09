package entity;

import java.util.Date;

public class TOrder {
    private Integer id;

    private String oSendtype;

    private String oPaytype;

    private Double oPaycount;

    private Date oOrderdate;

    private Integer oCheckstate;

    private Date oCheckdate;

    private String oCheckperson;

    private Integer userid;

    private String oShperson;

    private String oShphone;

    private String oShaddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getoSendtype() {
        return oSendtype;
    }

    public void setoSendtype(String oSendtype) {
        this.oSendtype = oSendtype == null ? null : oSendtype.trim();
    }

    public String getoPaytype() {
        return oPaytype;
    }

    public void setoPaytype(String oPaytype) {
        this.oPaytype = oPaytype == null ? null : oPaytype.trim();
    }

    public Double getoPaycount() {
        return oPaycount;
    }

    public void setoPaycount(Double oPaycount) {
        this.oPaycount = oPaycount;
    }

    public Date getoOrderdate() {
        return oOrderdate;
    }

    public void setoOrderdate(Date oOrderdate) {
        this.oOrderdate = oOrderdate;
    }

    public Integer getoCheckstate() {
        return oCheckstate;
    }

    public void setoCheckstate(Integer oCheckstate) {
        this.oCheckstate = oCheckstate;
    }

    public Date getoCheckdate() {
        return oCheckdate;
    }

    public void setoCheckdate(Date oCheckdate) {
        this.oCheckdate = oCheckdate;
    }

    public String getoCheckperson() {
        return oCheckperson;
    }

    public void setoCheckperson(String oCheckperson) {
        this.oCheckperson = oCheckperson == null ? null : oCheckperson.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getoShperson() {
        return oShperson;
    }

    public void setoShperson(String oShperson) {
        this.oShperson = oShperson == null ? null : oShperson.trim();
    }

    public String getoShphone() {
        return oShphone;
    }

    public void setoShphone(String oShphone) {
        this.oShphone = oShphone == null ? null : oShphone.trim();
    }

    public String getoShaddress() {
        return oShaddress;
    }

    public void setoShaddress(String oShaddress) {
        this.oShaddress = oShaddress == null ? null : oShaddress.trim();
    }
}