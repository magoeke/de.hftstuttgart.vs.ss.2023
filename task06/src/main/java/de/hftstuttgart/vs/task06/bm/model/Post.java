package de.hftstuttgart.vs.task06.bm.model;

import java.util.List;
import java.util.Objects;

public final class Post {
    private final int postID;
    private final String userName;
    private final List<Comment> comments;
    private String text;
    private PostState state;

    public Post(final int postID, final String text, final PostState state, final String userName,
                final List<Comment> comments) {
        this.postID = postID;
        this.text = text;
        this.state = state;
        this.userName = userName;
        this.comments = comments;
    }

    public int getPostID() {
        return postID;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public PostState getState() {
        return state;
    }

    public void setState(final PostState state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(final Comment comment) {
        comments.add(comment);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final var that = (Post) obj;
        return Objects.equals(this.text, that.text) &&
                Objects.equals(this.state, that.state) && Objects.equals(this.userName, that.userName) &&
                Objects.equals(this.comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, state, userName, comments);
    }

    @Override
    public String toString() {
        return "Post[" + "text=" + text + ", " + "state=" + state + ", " + "userName=" +
                userName + ", " + "comments=" + comments + ']';
    }

}
