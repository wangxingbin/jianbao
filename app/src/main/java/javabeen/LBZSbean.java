package javabeen;

import java.util.List;

/**
 * Created by ti on 2016/12/5.
 */

public class LBZSbean {


    /**
     * status : 200
     * info : 成功
     * data : {"size":5,"list":[{"id":21,"title":"11","image":"","price":"11","issue_time":1480662916000,"state":0},{"id":22,"title":"11","image":"","price":"11","issue_time":1480662932000,"state":0},{"id":23,"title":"11","image":"","price":"11","issue_time":1480662944000,"state":0},{"id":24,"title":"1","image":"","price":"1","issue_time":1480663472000,"state":0},{"id":25,"title":"cccc","image":"25_0.jpeg","price":"ccc","issue_time":1480663994000,"state":0}]}
     */

    private String status;
    private String info;
    /**
     * size : 5
     * list : [{"id":21,"title":"11","image":"","price":"11","issue_time":1480662916000,"state":0},{"id":22,"title":"11","image":"","price":"11","issue_time":1480662932000,"state":0},{"id":23,"title":"11","image":"","price":"11","issue_time":1480662944000,"state":0},{"id":24,"title":"1","image":"","price":"1","issue_time":1480663472000,"state":0},{"id":25,"title":"cccc","image":"25_0.jpeg","price":"ccc","issue_time":1480663994000,"state":0}]
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
        private int size;
        /**
         * id : 21
         * title : 11
         * image :
         * price : 11
         * issue_time : 1480662916000
         * state : 0
         */

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
