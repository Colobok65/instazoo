package ru.shur.instazoo.facade;

import org.springframework.stereotype.Component;
import ru.shur.instazoo.dto.CommentDto;
import ru.shur.instazoo.entity.Comment;

@Component
public class CommentFacade {

    public CommentDto commentToCommentDto(Comment comment) {

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setUsername(comment.getUsername());
        commentDto.setMessage(comment.getMessage());

        return commentDto;
    }
}
