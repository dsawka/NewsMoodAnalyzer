package pl.coderslab.newsmoodanalyzer.service;

import pl.coderslab.newsmoodanalyzer.model.Post;

import java.util.List;


public interface PostService {

    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post createPost(Post post);

    Post updatePost(Post post);

    void deletePost(Long id);
}


