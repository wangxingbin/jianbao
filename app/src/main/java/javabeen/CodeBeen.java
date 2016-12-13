package javabeen;

/**
 * Created by Administrator on 2016/12/7.
 */

public class CodeBeen {
    /**
     * status : 200
     * info : 成功
     * data : {"code":"9A1158154D","state":0}
     */

    private String status;
    private String info;
    /**
     * code : 9A1158154D
     * state : 0
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
        private String code;
        private int state;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
