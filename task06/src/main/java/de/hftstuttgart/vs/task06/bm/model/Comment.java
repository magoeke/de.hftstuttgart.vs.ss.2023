package de.hftstuttgart.vs.task06.bm.model;

import java.util.Objects;

public final class Comment {
    private String text;
    private String userName;

    public Comment(final String text, final String userName) {
        this.text = text;
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final var that = (Comment) obj;
        return Objects.equals(this.text, that.text) &&
                Objects.equals(this.userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, userName);
    }

    @Override
    public String toString() {
        return "Comment[" +
                "text=" + text + ", " +
                "userName=" + userName + ']';
    }

}
