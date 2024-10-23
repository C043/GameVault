package C043.PowerUp.services;

import C043.PowerUp.entities.Review;
import C043.PowerUp.entities.User;
import C043.PowerUp.exceptions.BadRequestException;
import C043.PowerUp.exceptions.NotFoundException;
import C043.PowerUp.exceptions.UnauthorizedException;
import C043.PowerUp.payloads.ReviewDTO;
import C043.PowerUp.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review postReview(User user, ReviewDTO body) {
        Review found = this.reviewRepository.findByUserAndGameId(user,
                body.gameId());
        if (found != null) throw new BadRequestException("Review already " +
                "posted");
        Review newReview = new Review(body.gameId(), body.rating(), user,
                body.content());
        return this.reviewRepository.save(newReview);
    }

    public List<Review> getAllReviews(int gameId) {
        return this.reviewRepository.findByGameId(gameId);
    }

    public Review getUserReview(User user, int gameId) {
        Review found = this.reviewRepository.findByUserAndGameId(user,
                gameId);
        if (found == null) throw new NotFoundException("Review not found");
        return found;
    }

    public Review updateReview(User user, ReviewDTO body) {
        Review found = this.getUserReview(user, body.gameId());
        if (found.getUser().getId() != user.getId())
            throw new UnauthorizedException("Not " +
                    "authorized to edit this review");
        found.setContent(body.content());
        found.setRating(body.rating());
        return this.reviewRepository.save(found);
    }

    public void deleteReview(User user, int gameId) {
        Review found = this.getUserReview(user, gameId);
        if (user.getId() != found.getUser().getId())
            throw new UnauthorizedException("Not " +
                    "authorized to delete this review");
        this.reviewRepository.delete(found);
    }
}
