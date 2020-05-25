package by.nareiko.xml.entity;

import java.util.Date;
import java.util.Objects;

public class Paper {
    private long id;
    private String title;
    private PaperType paperType;
    private PeriodicalType periodical;
    private boolean subscription;
    private PublishingDate publishingDate;
    private PaperCharacteristics paperCharacteristics = new PaperCharacteristics();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    public PeriodicalType getPeriodical() {
        return periodical;
    }

    public void setPeriodical(PeriodicalType periodical) {
        this.periodical = periodical;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public PublishingDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(PublishingDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public PaperCharacteristics getPaperCharacteristics() {
        return paperCharacteristics;
    }

    public void setPaperCharacteristics(PaperCharacteristics paperCharacteristics) {
        this.paperCharacteristics = paperCharacteristics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return id == paper.id &&
                paperCharacteristics.equals(paper.getPaperCharacteristics()) &&
                subscription == paper.isSubscription() &&
                Objects.equals(title, paper.getTitle()) &&
                Objects.equals(periodical, paper.getPeriodical()) &&
                Objects.equals(publishingDate, paper.getPublishingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, periodical, paperCharacteristics, subscription, publishingDate);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append(getClass().getName())
                .append(", id=" + id)
                .append(", title='" + title)
                .append(", periodical='" + periodical)
                .append(", subscription=" + subscription)
                .append(", publishingDate=" + publishingDate)
                .append(paperCharacteristics.toString())
                .toString();
    }

    public static class PaperCharacteristics {
        private boolean isColour;
        private int volume;
        private boolean isGlossy;

        public boolean isColour() {
            return isColour;
        }

        public void setColour(boolean colour) {
            isColour = colour;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public boolean isGlossy() {
            return isGlossy;
        }

        public void setGlossy(boolean glossy) {
            isGlossy = glossy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PaperCharacteristics that = (PaperCharacteristics) o;
            return isColour == that.isColour() &&
                    volume == that.getVolume()&&
                    isGlossy == that.isGlossy();
        }

        @Override
        public int hashCode() {
            return Objects.hash(isColour, volume, isGlossy);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            return sb.append(getClass().getName())
                    .append(", isColour=" + isColour)
                    .append(", volume=" + volume)
                    .append(", isGlossy=" + isGlossy).toString();
        }
    }
}
