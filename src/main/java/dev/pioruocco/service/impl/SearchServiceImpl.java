package dev.pioruocco.service.impl;

import dev.pioruocco.model.entity.PostEntity;
import dev.pioruocco.repository.SearchRepository;
import dev.pioruocco.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    //finding a post by text that query
    @Override
    public List<PostEntity> findPostByText(String text) {
        return searchRepository.findByText(text);
    }
}
