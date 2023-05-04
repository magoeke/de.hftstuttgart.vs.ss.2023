package de.hftstuttgart.vs.task06.bm;

import de.hftstuttgart.vs.task06.bm.exceptions.PostNotFound;
import de.hftstuttgart.vs.task06.bm.model.Comment;
import de.hftstuttgart.vs.task06.bm.model.Post;
import de.hftstuttgart.vs.task06.bm.model.PostState;

import java.util.List;

public interface PostRepository {

    List<Post> getPosts();

    Post getPost(int postID);

    Post addPost(String userName, String text);

    Post editPost(int postID, String text) throws PostNotFound;

    Post changePostState(int postID, PostState state) throws PostNotFound;

    Comment addComment(int postID, String userName, String comment) throws PostNotFound;

}
