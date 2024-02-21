package com.bananaapps.bananamusic.controller;

import com.bananaapps.bananamusic.domain.StatusMessage;
import com.bananaapps.bananamusic.domain.music.PurchaseOrderLineSong;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.exception.SongNotfoundException;
import com.bananaapps.bananamusic.persistence.music.SongRepository;
import com.bananaapps.bananamusic.service.music.Catalog;
import com.bananaapps.bananamusic.service.music.ShoppingCart;
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
@RequestMapping(value = "/cart")
//@CrossOrigin(origins = "*")
@Validated
public class ShoppingServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingServiceController.class);

    @Autowired
    ShoppingCart cart;

    @ApiOperation(value = "Get the cart balance", notes = "Returns the total amount to pay")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
    })
    @GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getBalance() {
        return cart.getBalance();
    }

    @ApiOperation(value = "Add an item line", notes = "Returns a confirmation code")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully added"),
            @ApiResponse(code = 400, message = "Bad request"),
    })
    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addItem(@RequestBody @Valid PurchaseOrderLineSong item) {
        cart.addItem(item);
        return new ResponseEntity<>(new StatusMessage(HttpStatus.ACCEPTED.value(), "Added"), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Delete an item line", notes = "Returns a confirmation code")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully removed"),
            @ApiResponse(code = 404, message = "Item does not exist"),
    })
    @DeleteMapping(value = "/{item}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeItem(@PathVariable @ApiParam(name = "item id", value = "Order line number", example = "234") @Min(1) Long item) {
        cart.removeItem(item);
        return new ResponseEntity<>(new StatusMessage(HttpStatus.ACCEPTED.value(), "Removed"), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Count the items number", notes = "Returns the number of items")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned"),
    })
    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getItemCount() {
        return cart.getItemCount();
    }

    @ApiOperation(value = "Empty the shopping cart", notes = "Returns a confirmation code")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully empty"),
    })
    @DeleteMapping(value = "/empty", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity empty() {
        cart.empty();
        return new ResponseEntity<>(new StatusMessage(HttpStatus.ACCEPTED.value(), "Emptied"), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Execute the purchase of items in cart", notes = "Returns a confirmation code")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully saved"),
            @ApiResponse(code = 500, message = "Some error in purchase saving"),
    })
    @PostMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buy() {
        cart.buy();
        return new ResponseEntity<>(new StatusMessage(HttpStatus.ACCEPTED.value(), "Saved"), HttpStatus.ACCEPTED);
    }

}