package by.nareiko.xml.entity;

import java.util.Date;

public class PublishingDate {
    private Date publishingDate;

    public PublishingDate(Date publishingDate){
        this.publishingDate = publishingDate;
    }

    public PublishingDate(){

    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingDate date = (PublishingDate) o;
        return publishingDate != null ? publishingDate.equals(date.publishingDate) : date.publishingDate == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (publishingDate != null ? publishingDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(", publishingDate= ").append(publishingDate);
        return sb.toString();
    }
}
