package ru.shur.instazoo.facade;

import org.springframework.stereotype.Component;
import ru.shur.instazoo.dto.PostDto;
import ru.shur.instazoo.entity.Post;

@Component
public class PostFacade {

    public PostDto postToPostDto(Post post) {

        PostDto postDto = new PostDto();
        postDto.setUsername(post.getUser().getUsername());
        postDto.setId(post.getId());
        postDto.setCaption(post.getCaption());
        postDto.setLikes(post.getLikes());
        postDto.setUsersLiked(post.getLikedUsers());
        postDto.setLocation(post.getLocation());
        postDto.setTitle(post.getTitle());

        return postDto;
    }
}
