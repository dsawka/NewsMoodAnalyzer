package pl.coderslab.newsmoodanalyzer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.newsmoodanalyzer.dto.EvaluationDTO;
import pl.coderslab.newsmoodanalyzer.dto.PostDTO;
import pl.coderslab.newsmoodanalyzer.model.Evaluation;
import pl.coderslab.newsmoodanalyzer.model.Post;
import pl.coderslab.newsmoodanalyzer.service.AuthorService;
import pl.coderslab.newsmoodanalyzer.service.DairyService;
import pl.coderslab.newsmoodanalyzer.service.PostService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/posts")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @Autowired
    private final AuthorService authorService;
    @Autowired
    private final DairyService dairyService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDTO> postDTOs = posts.stream()
                .map(this::mapPostToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);
        PostDTO postDTO = mapPostToDto(post);
        return ResponseEntity.ok(postDTO);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        Post post = mapDtoToPost(postDTO);
        Post createdPost = postService.createPost(post);
        PostDTO createdPostDTO = mapPostToDto(createdPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPostDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO postDTO) {
        Post existingPost = postService.getPostById(id);
        Post updatedPost = mapDtoToPost(postDTO);
        updatedPost.setId(existingPost.getId());
        Post savedPost = postService.updatePost(updatedPost);
        PostDTO savedPostDTO = mapPostToDto(savedPost);
        return ResponseEntity.ok(savedPostDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    private PostDTO mapPostToDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setAuthorId(post.getAuthor().getId());
        postDTO.setContent(post.getContent());
        postDTO.setCreationDate(post.getCreationDate());
        postDTO.setEvaluations(post.getEvaluations().stream()
                .map(this::mapEvaluationToDto)
                .collect(Collectors.toList()));
        postDTO.setDairyId(post.getDairy().getId());
        return postDTO;
    }

    private EvaluationDTO mapEvaluationToDto(Evaluation evaluation) {
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationDTO.setId(evaluation.getId());
        evaluationDTO.setJoy(evaluation.getJoy());
        evaluationDTO.setSadness(evaluation.getSadness());
        evaluationDTO.setFear(evaluation.getFear());
        evaluationDTO.setAnger(evaluation.getAnger());
        evaluationDTO.setSurprise(evaluation.getSurprise());
        evaluationDTO.setNeutrality(evaluation.getNeutrality());
        evaluationDTO.setSatisfaction(evaluation.getSatisfaction());
        evaluationDTO.setTrust(evaluation.getTrust());
        evaluationDTO.setEnvy(evaluation.getEnvy());
        evaluationDTO.setDisgust(evaluation.getDisgust());
        evaluationDTO.setExcitement(evaluation.getExcitement());
        evaluationDTO.setPositiveSentiment(evaluation.getPositiveSentiment());
        evaluationDTO.setNegativeSentiment(evaluation.getNegativeSentiment());
        return evaluationDTO;
    }


    private Post mapDtoToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setAuthor(authorService.getAuthorById(postDTO.getAuthorId()));
        post.setContent(postDTO.getContent());
        post.setCreationDate(postDTO.getCreationDate());
        List<Evaluation> evaluations = postDTO.getEvaluations().stream()
                .map(this::mapDtoToEvaluation)
                .collect(Collectors.toList());
        post.setEvaluations(evaluations);
        post.setDairy(dairyService.getDairyById(postDTO.getDairyId()));
        return post;
    }

    private Evaluation mapDtoToEvaluation(EvaluationDTO evaluationDTO) {
        Evaluation evaluation = new Evaluation();
        evaluation.setId(evaluationDTO.getId());
        evaluation.setJoy(evaluationDTO.getJoy());
        evaluation.setSadness(evaluationDTO.getSadness());
        evaluation.setFear(evaluationDTO.getFear());
        evaluation.setAnger(evaluationDTO.getAnger());
        evaluation.setSurprise(evaluationDTO.getSurprise());
        evaluation.setNeutrality(evaluationDTO.getNeutrality());
        evaluation.setSatisfaction(evaluationDTO.getSatisfaction());
        evaluation.setTrust(evaluationDTO.getTrust());
        evaluation.setEnvy(evaluationDTO.getEnvy());
        evaluation.setDisgust(evaluationDTO.getDisgust());
        evaluation.setExcitement(evaluationDTO.getExcitement());
        evaluation.setPositiveSentiment(evaluationDTO.getPositiveSentiment());
        evaluation.setNegativeSentiment(evaluationDTO.getNegativeSentiment());
        return evaluation;
    }

}
