package de.hftstuttgart.vs.task06.api.graphql.mapper;

import de.hftstuttgart.vs.task06.bm.model.Comment;
import de.hftstuttgart.vs.task06.api.graphql.model.CommentDO;

public class CommentMapper {

    public static CommentDO map(final Comment comment) {
        return CommentDO.builder()
                .setText(comment.getText())
                .build();
    }
}
