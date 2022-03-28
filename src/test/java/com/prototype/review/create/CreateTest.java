package com.prototype.review.create;


import com.prototype.review.domain.ProductScore;
import com.prototype.review.domain.Review;
import com.prototype.review.domain.ReviewComment;
import com.prototype.review.domain.ReviewRepository;
import com.prototype.review.service.ReviewService;
import com.prototype.user.service.UserDto;
import com.prototype.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    UserService userService;


    @DisplayName("Create test 1. Normal Condition : score - 10")
    @Test
    public void test1(){
        ReviewService sut = new ReviewService(reviewRepository, userService);

        Long loggedInUserId =1L;
        String loggedInUserName = "user";
        String loggedINUserPassword = "password";
        String loggedInUserGender = "MALE";

        Long productId = 1L;

        UserDto loggedInUser = new UserDto(
                loggedInUserId,
                loggedInUserName,
                loggedINUserPassword,
                loggedInUserGender,
                false,
                LocalDate.now());

        String comment = "comment";
        int maxScore = 10;
        int minScore = 1;

        Review created = Review.create(
                productId, loggedInUser.getUserId(), ReviewComment.create(comment), ProductScore.create(maxScore)
        );

        when(userService.getLoggedInUser()).thenReturn(loggedInUser);
        when(reviewRepository.existsById(any())).thenReturn(false);
        when(reviewRepository.save(any())).thenReturn(created);

        sut.create(productId, comment, maxScore);

        verify(reviewRepository, times(1)).save(any());

    }

    @DisplayName("Create test 2. Normal Condition : score - min")
    @Test
    public void test2(){
        ReviewService sut = new ReviewService(reviewRepository, userService);

        Long loggedInUserId =1L;
        String loggedInUserName = "user";
        String loggedINUserPassword = "password";
        String loggedInUserGender = "MALE";

        Long productId = 1L;

        UserDto loggedInUser = new UserDto(
                loggedInUserId,
                loggedInUserName,
                loggedINUserPassword,
                loggedInUserGender,
                false,
                LocalDate.now());

        String comment = "comment";
        int maxScore = 10;
        int minScore = 1;

        Review created = Review.create(
                productId, loggedInUser.getUserId(), ReviewComment.create(comment), ProductScore.create(maxScore)
        );

        when(userService.getLoggedInUser()).thenReturn(loggedInUser);
        when(reviewRepository.existsById(any())).thenReturn(false);
        when(reviewRepository.save(any())).thenReturn(created);

        sut.create(productId, comment, maxScore);

        verify(reviewRepository, times(1)).save(any());

    }
}