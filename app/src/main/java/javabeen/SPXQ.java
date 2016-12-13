package javabeen;

import java.util.List;

/**
 * Created by ti on 2016/12/5.
 */

public class SPXQ {
    /**
     * status : 200
     * info : 成功
     * data : {"id":6,"user_id":1,"title":"title","description":"desc","price":"price","contact":"杜先生","mobile":"mobile","qq":"qq","wechat":"wechat","email":"email","photos":["6_0.png","6_1.png","6_2.png","6_3.png","6_4.png","6_5.png","6_6.png","6_7.png","6_8.png"],"issue_time":"2016-12-01 01:54:23","follow":2,"state":1,"followed":false,"owned":false}
     */

    private String status;
    private String info;
    /**
     * id : 6
     * user_id : 1
     * title : title
     * description : desc
     * price : price
     * contact : 杜先生
     * mobile : mobile
     * qq : qq
     * wechat : wechat
     * email : email
     * photos : ["6_0.png","6_1.png","6_2.png","6_3.png","6_4.png","6_5.png","6_6.png","6_7.png","6_8.png"]
     * issue_time : 2016-12-01 01:54:23
     * follow : 2
     * state : 1
     * followed : false
     * owned : false
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private int user_id;
        private String title;
        private String description;
        private String price;
        private String contact;
        private String mobile;
        private String qq;
        private String wechat;
        private String email;
        private String issue_time;
        private int follow;
        private int state;
        private boolean followed;
        private boolean owned;
        private List<String> photos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIssue_time() {
            return issue_time;
        }

        public void setIssue_time(String issue_time) {
            this.issue_time = issue_time;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public boolean isOwned() {
            return owned;
        }

        public void setOwned(boolean owned) {
            this.owned = owned;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
        }
    }


//    /**
//     * status : 200
//     * info : 成功
//     * data : {"id":6,"user_id":1,"title":"title","description":"desc","price":"price","contact":"杜先生","mobile":"mobile","qq":"qq","wechat":"wechat","email":"email","photos":["6_0.png","6_1.png","6_2.png","6_3.png","6_4.png","6_5.png","6_6.png","6_7.png","6_8.png"],"issue_time":1480557263000,"follow":0,"state":0,"owner":0}
//     */
//
//    private String status;
//    private String info;
//    /**
//     * id : 6
//     * user_id : 1
//     * title : title
//     * description : desc
//     * price : price
//     * contact : 杜先生
//     * mobile : mobile
//     * qq : qq
//     * wechat : wechat
//     * email : email
//     * photos : ["6_0.png","6_1.png","6_2.png","6_3.png","6_4.png","6_5.png","6_6.png","6_7.png","6_8.png"]
//     * issue_time : 1480557263000
//     * follow : 0
//     * state : 0
//     * owner : 0
//     */
//
//    private DataBean data;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getInfo() {
//        return info;
//    }
//
//    public void setInfo(String info) {
//        this.info = info;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        private int id;
//        private int user_id;
//        private String title;
//        private String description;
//        private String price;
//        private String contact;
//        private String mobile;
//        private String qq;
//        private String wechat;
//        private String email;
//        private String issue_time;
//        private int follow;
//        private int state;
//        private int owner;
//        private List<String> photos;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(int user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//
//        public void setPrice(String price) {
//            this.price = price;
//        }
//
//        public String getContact() {
//            return contact;
//        }
//
//        public void setContact(String contact) {
//            this.contact = contact;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public String getQq() {
//            return qq;
//        }
//
//        public void setQq(String qq) {
//            this.qq = qq;
//        }
//
//        public String getWechat() {
//            return wechat;
//        }
//
//        public void setWechat(String wechat) {
//            this.wechat = wechat;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getIssue_time() {
//            return issue_time;
//        }
//
//        public void setIssue_time(String issue_time) {
//            this.issue_time = issue_time;
//        }
//
//        public int getFollow() {
//            return follow;
//        }
//
//        public void setFollow(int follow) {
//            this.follow = follow;
//        }
//
//        public int getState() {
//            return state;
//        }
//
//        public void setState(int state) {
//            this.state = state;
//        }
//
//        public int getOwner() {
//            return owner;
//        }
//
//        public void setOwner(int owner) {
//            this.owner = owner;
//        }
//
//        public List<String> getPhotos() {
//            return photos;
//        }
//
//        public void setPhotos(List<String> photos) {
//            this.photos = photos;
//        }
//
//        @Override
//        public String toString() {
//            return title+price+description;
//        }
//
//
//
//    }
//
//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
