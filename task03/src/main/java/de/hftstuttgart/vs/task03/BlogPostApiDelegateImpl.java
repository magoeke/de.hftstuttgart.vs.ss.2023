package de.hftstuttgart.vs.task03;

import de.hftstuttgart.vs.task03.openapi.api.BlogPostsApiDelegate;
import de.hftstuttgart.vs.task03.openapi.model.BlogPost;
import de.hftstuttgart.vs.task03.openapi.model.BlogPostRequest;
import de.hftstuttgart.vs.task03.openapi.model.BlogPosts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostApiDelegateImpl implements BlogPostsApiDelegate {

    @Override
    public ResponseEntity<BlogPosts> getBlogPosts() {
        final var blogPost = new BlogPost()
                .blogPostId(1L)
                .content("Hello World")
                .blogPostAuthorId(7L)
                .locked(false)
                .status(BlogPost.StatusEnum.DRAFT);

        final var blogPosts = new BlogPosts()
                .blogPosts(List.of(blogPost));

        return ResponseEntity.ok(blogPosts);
    }

    @Override
    public ResponseEntity<BlogPost> addBlockPost(BlogPostRequest blogPostRequest) {
        final var blogPost = new BlogPost()
                .blogPostId(2L)
                .content(blogPostRequest.getContent())
                .blogPostAuthorId(blogPostRequest.getBlogPostAuthorId())
                .locked(blogPostRequest.getLocked())
                .status(BlogPost.StatusEnum.fromValue(blogPostRequest.getStatus().getValue()));

        return ResponseEntity.status(201).body(blogPost);
    }
}
