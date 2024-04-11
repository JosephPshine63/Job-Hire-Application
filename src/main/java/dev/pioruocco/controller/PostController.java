package dev.pioruocco.controller;

import dev.pioruocco.model.entity.PostEntity;
import dev.pioruocco.service.PostService;
import dev.pioruocco.service.SearchService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/rest-api/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;
    private final SearchService searchService;

    public PostController(PostService postService, SearchService searchService) {
        this.postService = postService;
        this.searchService = searchService;
    }

    //getting all the post from the database
    @GetMapping(path = "/all")
    public List<PostEntity> getAllPosts(){
        return postService.findAllPosts();
    }

    //inserting a post
    @PostMapping(path = "/insert")
    public PostEntity savePost(@Valid @RequestBody PostEntity postEntity){
        return postService.savePost(postEntity);
    }

    //search endpoint
    @GetMapping(path = "/{text}")
    public List<PostEntity> search(@PathVariable("text") String text){
        return searchService.findPostByText(text);
    }

    //mapping for swagger
    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/rest-api/posts/swagger-ui.html");
    }
}
