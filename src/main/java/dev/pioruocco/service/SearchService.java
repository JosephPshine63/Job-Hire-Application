package dev.pioruocco.service;

import dev.pioruocco.model.entity.PostEntity;

import java.util.List;

public interface SearchService {

    List<PostEntity> findPostByText(String text);

}
