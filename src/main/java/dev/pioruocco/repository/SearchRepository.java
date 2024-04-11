package dev.pioruocco.repository;

import dev.pioruocco.model.entity.PostEntity;

import java.util.List;

//filtering of the input for searching to all the various posts
public interface SearchRepository {

    List<PostEntity> findByText(String text);

}
