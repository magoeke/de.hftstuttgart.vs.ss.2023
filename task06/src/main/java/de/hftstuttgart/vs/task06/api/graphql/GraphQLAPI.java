package de.hftstuttgart.vs.task06.api.graphql;


import de.hftstuttgart.vs.task06.api.graphql.mapper.CommentMapper;
import de.hftstuttgart.vs.task06.api.graphql.mapper.PostMapper;
import de.hftstuttgart.vs.task06.api.graphql.mapper.PostStateMapper;
import de.hftstuttgart.vs.task06.api.graphql.model.CommentDO;
import de.hftstuttgart.vs.task06.api.graphql.model.CommentInputDO;
import de.hftstuttgart.vs.task06.api.graphql.model.PostDO;
import de.hftstuttgart.vs.task06.api.graphql.model.PostStateDO;
import de.hftstuttgart.vs.task06.bm.BlogController;
import jakarta.annotation.Nonnull;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLAPI implements QueryResolver, MutationResolver {

    private final BlogController blogController;

    public GraphQLAPI(final BlogController blogController) {
        this.blogController = blogController;
    }

    // TODO:

    @Nonnull
    @Override
    @QueryMapping
    public List<PostDO> posts() {
        return blogController.getPosts().stream().map(PostMapper::map).toList();
    }

    @Override
    @MutationMapping
    public PostDO addPost(@Nonnull final String text, @Nonnull final String userName) {
        final var post = blogController.addPost(userName, text);
        return PostMapper.map(post);
    }

    @Override
    @MutationMapping
    public PostDO editPost(final int postID, @Nonnull final String text) throws Exception {
        final var post = blogController.editPost(postID, text);
        return PostMapper.map(post);
    }

    @Override
    @MutationMapping
    public PostDO deletePost(int postID) throws Exception {
        return null;
    }

    @Override
    @MutationMapping
    public CommentDO addComment(final int postID, @Nonnull final CommentInputDO commentInput) throws Exception {
        final var comment = blogController.addComment(postID, commentInput.getUserName(), commentInput.getText());
        return CommentMapper.map(comment);
    }

    @Override
    @MutationMapping
    public PostDO changePostState(final int postID, @Nonnull final PostStateDO state) throws Exception {
        final var post = blogController.changePostState(postID, PostStateMapper.map(state));
        return PostMapper.map(post);
    }

    // TODO: Field mapping
    @SchemaMapping(typeName = "Post")
    public List<CommentDO> comments(final PostDO post) {
        return blogController.getPost(post.getPostID()).getComments().stream().map(CommentMapper::map).toList();
    }

    @SchemaMapping(typeName = "Post")
    public int commentCount(final PostDO post) {
        return blogController.getCommentCount(post.getPostID());
    }
}
