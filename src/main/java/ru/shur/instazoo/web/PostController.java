package ru.shur.instazoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shur.instazoo.dto.PostDto;
import ru.shur.instazoo.entity.Post;
import ru.shur.instazoo.facade.PostFacade;
import ru.shur.instazoo.payload.response.MessageResponse;
import ru.shur.instazoo.services.PostService;
import ru.shur.instazoo.validations.ResponseErrorValidation;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostFacade postFacade;
    @Autowired
    private PostService postService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(
            @Valid @RequestBody PostDto postDto,
            BindingResult bindingResult,
            Principal principal) {

        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if(!ObjectUtils.isEmpty(errors)) return errors;

        Post post = postService.createPost(postDto, principal);
        PostDto createdPost = postFacade.postToPostDto(post);

        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> postDtoList = postService.getAllPosts()
                .stream()
                .map(postFacade::postToPostDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/user/posts")
    public ResponseEntity<List<PostDto>> getAllPostsForUser(Principal principal) {
        List<PostDto> postDtoList = postService.getAllPostForUser(principal)
                .stream()
                .map(postFacade::postToPostDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @PostMapping("/{postId}/{username}/like")
    public ResponseEntity<PostDto> likePost(
            @PathVariable("postId") String postId,
            @PathVariable("username") String username) {

        Post post = postService.likePost(Long.parseLong(postId), username);
        PostDto postDto = postFacade.postToPostDto(post);

        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PostMapping("/{postId}/delete")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") String postId, Principal principal) {
        postService.deletePost(Long.parseLong(postId), principal);

        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }
}
