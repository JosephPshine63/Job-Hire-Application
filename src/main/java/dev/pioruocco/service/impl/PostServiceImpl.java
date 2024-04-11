package dev.pioruocco.service.impl;

import dev.pioruocco.model.entity.PostEntity;
import dev.pioruocco.repository.PostRepository;
import dev.pioruocco.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostEntity> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public PostEntity savePost(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }
}
