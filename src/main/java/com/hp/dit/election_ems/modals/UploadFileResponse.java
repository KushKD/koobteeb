package com.hp.dit.election_ems.modals;

import java.io.Serializable;

public class UploadFileResponse implements Serializable {

    private String fileDownloadUriOne;
    private String fileDownloadUriTwo;
    private String generateIDCardUrl_;

    public UploadFileResponse(String fileDownloadUriOne, String fileDownloadUriTwo,  String generateIDCardUrl_) {
        this.fileDownloadUriOne = fileDownloadUriOne;
        this.fileDownloadUriTwo = fileDownloadUriTwo;
      //  this.ownerData = ownerData;
        this.generateIDCardUrl_ = generateIDCardUrl_;
    }

    public String getFileDownloadUriOne() {
        return fileDownloadUriOne;
    }

    public void setFileDownloadUriOne(String fileDownloadUriOne) {
        this.fileDownloadUriOne = fileDownloadUriOne;
    }

    public String getFileDownloadUriTwo() {
        return fileDownloadUriTwo;
    }

    public void setFileDownloadUriTwo(String fileDownloadUriTwo) {
        this.fileDownloadUriTwo = fileDownloadUriTwo;
    }



    public String getGenerateIDCardUrl_() {
        return generateIDCardUrl_;
    }

    public void setGenerateIDCardUrl_(String generateIDCardUrl_) {
        this.generateIDCardUrl_ = generateIDCardUrl_;
    }

    @Override
    public String toString() {
        return "UploadFileResponse{" +
                "fileDownloadUriOne='" + fileDownloadUriOne + '\'' +
                ", fileDownloadUriTwo='" + fileDownloadUriTwo + '\'' +
                ", generateIDCardUrl_='" + generateIDCardUrl_ + '\'' +
                '}';
    }
}
