package com.dnb.logger.model;

import java.util.Date;

public class Log {

    private final String user;
    private final String website;
    private final String activityTypeDescription;
    private final Date signedInTime;

    public Log(LogBuilder logBuilder) {
        this.user = logBuilder.user;
        this.website = logBuilder.website;
        this.activityTypeDescription = logBuilder.activityTypeDescription;
        this.signedInTime = logBuilder.signedInTime;
    }

    public String getUser() {
        return user;
    }

    public String getWebsite() {
        return website;
    }

    public String getActivityTypeDescription() {
        return activityTypeDescription;
    }

    public Date getSignedInTime() {
        return signedInTime;
    }

    @Override
    public String toString() {
        return "Log { " +
                "user='" + user + '\'' +
                ", website='" + website + '\'' +
                ", activityTypeDescription='" + activityTypeDescription + '\'' +
                ", signedInTime=" + signedInTime +
                '}';
    }

    public static class LogBuilder {

        private String user;
        private String website;
        private String activityTypeDescription;
        private Date signedInTime;

        public LogBuilder() {
        }

        public LogBuilder user(String user) {
            this.user = user;
            return this;
        }

        public LogBuilder website(String website) {
            this.website = website;
            return this;
        }

        public LogBuilder activityTypeDescription(String activityTypeDescription) {
            this.activityTypeDescription = activityTypeDescription;
            return this;
        }

        public LogBuilder signedInTime(Date signedInTime) {
            this.signedInTime = signedInTime;
            return this;
        }

        public Log build() {
            return new Log(this);
        }
    }

}
