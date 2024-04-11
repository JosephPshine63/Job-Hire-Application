package dev.pioruocco.service;

import dev.pioruocco.model.entity.PostEntity;

import java.util.List;

public interface PostService {

    List<PostEntity> findAllPosts();

    PostEntity savePost(PostEntity postEntity);
}
