package de.hftstuttgart.vs.task06.api.graphql.mapper;

import de.hftstuttgart.vs.task06.api.graphql.model.PostDO;
import de.hftstuttgart.vs.task06.bm.model.Post;
public class PostMapper {

    public static PostDO map(final Post post) {
        return PostDO.builder()
                .setPostID(post.getPostID())
                .setText(post.getText())
                .setState(PostStateMapper.map(post.getState()))
                .build();
    }

}
