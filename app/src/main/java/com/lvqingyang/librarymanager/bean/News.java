package com.lvqingyang.librarymanager.bean;

import java.io.Serializable;

/**
 * 　　┏┓　　  ┏┓+ +
 * 　┏┛┻━ ━ ━┛┻┓ + +
 * 　┃　　　             ┃
 * 　┃　　　━　　   ┃ ++ + + +
 * ████━████     ┃+
 * 　┃　　　　　　  ┃ +
 * 　┃　　　┻　　  ┃
 * 　┃　　　　　　  ┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽保佑
 * 　　　┃　　　┃    代码无bug！
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * ━━━━━━神兽出没━━━━━━
 * Author：LvQingYang
 * Date：2017/7/8
 * Email：biloba12345@gamil.com
 * Info：
 */

public class News implements Serializable{

    /**
     * Admin : {"Id":10000,"Name":"何泽鹏","Password":"123456","Authority":3,"Phone":"18696162662"}
     * Id : 10006
     * Title : 第六届大赛高校巡展圆满落幕
     * Content :          4月5日，第六届“中国软件杯”大学生软件设计大赛全国高校巡展暨重点软件企业校园招聘行活动西北工业大学站在西北工业大学友谊路校区成功举办。西北工业大学软件与微电子学院副院长郑江滨、“中国软件杯”大学生软件设计大赛组委会代表韩英，大赛评审委员会专家、北京工商大学计算机与信息工程学院计算机系主任韩忠明，浪潮集团数据服务事业部副总经理毛宪阳、华为技术有限公司高级经理刘敏以及100余位软件与微电子学院师生共同出席了本次活动。
     作为工信部直属的7所重点高校之一和西北地区有影响力的综合性985重点院校，西北工业大学历年来非常重视软件人才的培养。西北工业大学软件与微电子学院郑江滨副院长表示，软件专业的教学具有其特殊性，需要紧跟行业发展趋势，不断进行实践与创新，而“中国软件杯”大赛“企业出题、高校参与”的模式，使得大赛赛题原型能够来源于企业研发和实际技术需求，也直指开发项目中的技术难点，紧贴实际应用的技术热点，能够以赛代练，充分考察参赛学生解决现实问题的方法、创意和能力，因而成为众多高校进行学生实践创新的重要平台。
     中国软件杯大学生软件设计大赛已经连续成功举办了五届。经过五年的沉淀和积累，大赛已经得到越来越多高校和企业的认与支持，真正秉承“政府指导，企业出题，高校参与，专家评审，育才选才”方针，实现 “催生多重效应，引领产业创新”的宗旨。五年一个大跨越，目前第六届大赛报名工作正在如火如荼地进行着，第六届大赛在往届基础上，将进一步扩大大赛参赛范围，广泛邀请海外院校参与，提高大赛的国际影响力和知名度;开通全新的线上赛事支撑平台，可同步举办线上赛事;丰富大赛的赛题类型，新开辟开源和安全可靠两个板块专题赛事，欢迎大家积极参与，赛事进程让我们试目以待。

     * OperatorID : 10000
     * CreateTime : 2017-06-27T21:24:14.68
     * PageView : 0
     * Rank : 0
     * Delete : false
     */

    private AdminEntity Admin;
    private int Id;
    private String Title;
    private String Content;
    private int OperatorID;
    private String CreateTime;
    private int PageView;
    private int Rank;
    private boolean Delete;

    public void setAdmin(AdminEntity Admin) {
        this.Admin = Admin;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setOperatorID(int OperatorID) {
        this.OperatorID = OperatorID;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public void setPageView(int PageView) {
        this.PageView = PageView;
    }

    public void setRank(int Rank) {
        this.Rank = Rank;
    }

    public void setDelete(boolean Delete) {
        this.Delete = Delete;
    }

    public AdminEntity getAdmin() {
        return Admin;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getContent() {
        return Content;
    }

    public int getOperatorID() {
        return OperatorID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public int getPageView() {
        return PageView;
    }

    public int getRank() {
        return Rank;
    }

    public boolean getDelete() {
        return Delete;
    }

    public static class AdminEntity implements Serializable {
        /**
         * Id : 10000
         * Name : 何泽鹏
         * Password : 123456
         * Authority : 3
         * Phone : 18696162662
         */

        private int Id;
        private String Name;
        private String Password;
        private int Authority;
        private String Phone;

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public void setAuthority(int Authority) {
            this.Authority = Authority;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public int getId() {
            return Id;
        }

        public String getName() {
            return Name;
        }

        public String getPassword() {
            return Password;
        }

        public int getAuthority() {
            return Authority;
        }

        public String getPhone() {
            return Phone;
        }
    }
}
