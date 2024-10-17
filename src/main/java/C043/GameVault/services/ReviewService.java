package C043.GameVault.services;

import C043.GameVault.entities.Review;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.BadRequestException;
import C043.GameVault.payloads.ReviewDTO;
import C043.GameVault.repositories.ReviewRepository;
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
}
