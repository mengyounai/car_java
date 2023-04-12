package com.gasaiyuno.car.po;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_admin")
public class Admin {

    //管理员工号
    @Id
    @GeneratedValue
    private int adminId;
    //管理员姓名
    private String adminName;
    //管理员密码
    private String adminPwd;
    //管理员权限
    private String adminPermission;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;


//    @OneToMany(mappedBy = "admin")
//    private List<Flu> flus = new ArrayList<>();

//    @OneToMany(mappedBy = "admin")
//    private List<Post> posts = new ArrayList<>();
//
//    @OneToMany(mappedBy = "admin")
//    private List<Info> infos = new ArrayList<>();


    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminPermission() {
        return adminPermission;
    }

    public void setAdminPermission(String adminPermission) {
        this.adminPermission = adminPermission;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

//    public List<Flu> getFlus() {
//        return flus;
//    }
//
//    public void setFlus(List<Flu> flus) {
//        this.flus = flus;
//    }

//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<Post> posts) {
//        this.posts = posts;
//    }
//
//    public List<Info> getInfos() {
//        return infos;
//    }
//
//    public void setInfos(List<Info> infos) {
//        this.infos = infos;
//    }


    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminPermission='" + adminPermission + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
