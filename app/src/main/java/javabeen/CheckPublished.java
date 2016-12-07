package javabeen;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class CheckPublished {

    /**
     * status : 200
     * info : 成功
     * data : {"size":2,"list":[{"id":11,"title":"123","image":"","price":"123","issue_time":"2016-12-02 05:49:48","state":0},{"id":10,"title":"123","image":"","price":"123","issue_time":"2016-12-02 05:45:15","state":0}]}
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
         * size : 2
         * list : [{"id":11,"title":"123","image":"","price":"123","issue_time":"2016-12-02 05:49:48","state":0},{"id":10,"title":"123","image":"","price":"123","issue_time":"2016-12-02 05:45:15","state":0}]
         */

        private int size;
        private List<ListBean> list;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 11
             * title : 123
             * image :
             * price : 123
             * issue_time : 2016-12-02 05:49:48
             * state : 0
             */

            private int id;
            private String title;
            private String image;
            private String price;
            private String issue_time;
            private int state;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIssue_time() {
                return issue_time;
            }

            public void setIssue_time(String issue_time) {
                this.issue_time = issue_time;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
