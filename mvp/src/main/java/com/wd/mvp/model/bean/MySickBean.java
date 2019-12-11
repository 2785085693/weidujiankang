package com.wd.mvp.model.bean;

import java.util.List;

public class MySickBean {


    /**
     * result : {"otherSickCircleCommentList":[{"commentId":7242,"commentTime":1575620050000,"commentUserId":418,"content":"xxx","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_4.jpg","nickName":"DF_GBDKN","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6585,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6594,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6590,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6592,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6587,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6593,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6591,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6589,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":6586,"commentTime":1574424073000,"commentUserId":260,"content":"建议内容","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_3.jpg","nickName":"Ro_VELLO","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2}]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        private List<OtherSickCircleCommentListBean> otherSickCircleCommentList;

        public List<OtherSickCircleCommentListBean> getOtherSickCircleCommentList() {
            return otherSickCircleCommentList;
        }

        public void setOtherSickCircleCommentList(List<OtherSickCircleCommentListBean> otherSickCircleCommentList) {
            this.otherSickCircleCommentList = otherSickCircleCommentList;
        }

        public static class OtherSickCircleCommentListBean {
            /**
             * commentId : 7242
             * commentTime : 1575620050000
             * commentUserId : 418
             * content : xxx
             * headPic : http://172.17.8.100/images/health/user/head_pic/default/default_head_4.jpg
             * nickName : DF_GBDKN
             * opinion : 0
             * opposeNum : 0
             * supportNum : 0
             * whetherDoctor : 2
             */

            private int commentId;
            private long commentTime;
            private int commentUserId;
            private String content;
            private String headPic;
            private String nickName;
            private int opinion;
            private int opposeNum;
            private int supportNum;
            private int whetherDoctor;

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public long getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(long commentTime) {
                this.commentTime = commentTime;
            }

            public int getCommentUserId() {
                return commentUserId;
            }

            public void setCommentUserId(int commentUserId) {
                this.commentUserId = commentUserId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getOpinion() {
                return opinion;
            }

            public void setOpinion(int opinion) {
                this.opinion = opinion;
            }

            public int getOpposeNum() {
                return opposeNum;
            }

            public void setOpposeNum(int opposeNum) {
                this.opposeNum = opposeNum;
            }

            public int getSupportNum() {
                return supportNum;
            }

            public void setSupportNum(int supportNum) {
                this.supportNum = supportNum;
            }

            public int getWhetherDoctor() {
                return whetherDoctor;
            }

            public void setWhetherDoctor(int whetherDoctor) {
                this.whetherDoctor = whetherDoctor;
            }
        }
    }
}
