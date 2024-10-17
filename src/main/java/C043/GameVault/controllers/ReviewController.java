package C043.GameVault.controllers;

import C043.GameVault.entities.Review;
import C043.GameVault.entities.User;
import C043.GameVault.payloads.RespDTO;
import C043.GameVault.payloads.ReviewDTO;
import C043.GameVault.security.Validation;
import C043.GameVault.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private Validation validation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postReview(@AuthenticationPrincipal User user,
                              @RequestBody @Validated ReviewDTO body,
                              BindingResult validation) {
        this.validation.validate(validation);
        Review newReview = this.reviewService.postReview(user, body);
        return new RespDTO(newReview.getId());
    }
}
