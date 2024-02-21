package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.StatusMessage;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.exception.SongNotfoundException;
import com.bananaapps.bananamusic.persistence.music.SongRepository;
import com.bananaapps.bananamusic.service.music.Catalog;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/catalog")
//@CrossOrigin(origins = "*")
@Validated
public class SongServiceController {
    private static final Logger logger = LoggerFactory.getLogger(SongServiceController.class);


    @Autowired
    Catalog catalog;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Get a product by id", notes = "Returns a product as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The product was not found")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSongById(
            @PathVariable @Min(1) @ApiParam(name = "id", value = "Song id", example = "1") Long id
    ) {
        Song song = catalog.findById(id);
        if (song == null) throw new SongNotfoundException("Song not found");
        if (song != null) return new ResponseEntity<>(song, HttpStatus.OK);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.NOT_FOUND.value(), "Not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/keyword/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Song>> getSongsByKeywords(@PathVariable @NotNull @NotBlank @ApiParam(name = "keyword", value = "Song text in title", example = "mamma") String keyword) {
        Collection<Song> songs = catalog.findByKeyword(keyword);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSong(@RequestBody @Valid Song newSong) {
        catalog.save(newSong);
        if (newSong != null && newSong.getId() > 0) return new ResponseEntity<>(newSong, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Not found"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveSongs(@RequestBody @Valid Collection<Song> songs) {
        catalog.saveCollection(songs);

        return new ResponseEntity<>(new StatusMessage(HttpStatus.OK.value(), "Songs saved"), HttpStatus.OK);
    }


}