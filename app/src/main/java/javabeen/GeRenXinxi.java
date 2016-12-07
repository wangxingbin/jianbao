package javabeen;

/**
 * Created by Administrator on 2016/12/6.
 */

public class GeRenXinxi {

    /**
     * status : 200
     * info : 成功
     * data : {"id":2,"mobile":"13800138000","name":"钟移动","gender":"男","idcard":"","photo":"1424349642438.jpeg","last_time":"2016-12-06 11:09:02","state":0}
     */

    private String status;
    private String info;
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
        /**
         * id : 2
         * mobile : 13800138000
         * name : 钟移动
         * gender : 男
         * idcard :
         * photo : 1424349642438.jpeg
         * last_time : 2016-12-06 11:09:02
         * state : 0
         */

        private int id;
        private String mobile;
        private String name;
        private String gender;
        private String idcard;
        private String photo;
        private String last_time;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
