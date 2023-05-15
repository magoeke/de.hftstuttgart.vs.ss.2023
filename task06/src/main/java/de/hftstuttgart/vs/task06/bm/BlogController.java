package de.hftstuttgart.vs.task06.bm;

import de.hftstuttgart.vs.task06.bm.exceptions.PostNotFound;
import de.hftstuttgart.vs.task06.bm.exceptions.UserNotFound;
import de.hftstuttgart.vs.task06.bm.model.Comment;
import de.hftstuttgart.vs.task06.bm.model.Post;
import de.hftstuttgart.vs.task06.bm.model.PostState;
import de.hftstuttgart.vs.task06.bm.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogController implements UserRepository, PostRepository {

    private final Map<String, User> users;
    private final Map<Integer, Post> posts;
    private int nextID = 1;

    public BlogController() {
        users = new HashMap<>();
        users.put("mmustermann", new User("mmustermann", "Max", "Mustermann"));
        users.put("jdoe", new User("jdoe", "John", "Doe"));
        posts = new HashMap<>();
        posts.put(1, new Post(1, "This is an example post", PostState.PUBLISHED, "mmustermann", new ArrayList<>()));
        posts.get(1).getComments().add(new Comment("Great post!", "jdoe"));
    }

    @Override
    public List<Post> getPosts() {
        return posts.values().stream().toList();
    }

    @Override
    public Post getPost(final int postID) {
        return posts.get(postID);
    }

    @Override
    public Post addPost(final String userName, final String text) {
        final var postID = nextID++;
        final var post = new Post(postID, text, PostState.UNPUBLISHED, userName, new ArrayList<>());
        posts.put(postID, post);
        return post;
    }

    @Override
    public Post editPost(final int postID, final String text) throws PostNotFound {
        if (!posts.containsKey(postID)) {
            throw new PostNotFound();
        }
        final var post = posts.get(postID);
        post.setText(text);
        return post;
    }

    @Override
    public Post changePostState(final int postID, final PostState state) throws PostNotFound {
        if (!posts.containsKey(postID)) {
            throw new PostNotFound();
        }
        final var post = posts.get(postID);
        post.setState(state);
        return post;
    }

    @Override
    public Comment addComment(final int postID, final String userName, final String commentText) throws PostNotFound {
        if (!posts.containsKey(postID)) {
            throw new PostNotFound();
        }
        final var post = posts.get(postID);
        final var comment = new Comment(commentText, userName);
        post.addComment(comment);
        return comment;
    }

    @Override
    public List<User> getUsers() {
        return users.values().stream().toList();
    }

    @Override
    public User getUser(final String userName) throws UserNotFound {
        final var user = users.get(userName);
        if (user == null) {
            throw new UserNotFound();
        }
        return user;
    }

    public int getCommentCount(final int postID) {
        //TODO 4: implement method counting the comments for a post
        final var post = posts.get(postID);
        return post != null ? post.getComments().size() : 0;
    }
}
