package com.rankbite;

import com.rankbite.exception.InvalidDeliveryTimeException;
import com.rankbite.exception.InvalidPriceException;
import com.rankbite.exception.InvalidRatingException;
import com.rankbite.model.Restaurant;
import com.rankbite.ranking.RankingResult;
import com.rankbite.ranking.WeightedRankingStrategy;
import com.rankbite.service.RankingService;
import com.rankbite.util.DataValidator;
import com.rankbite.util.NormalizationUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RankingTest {

    private RankingService rankingService;
    private WeightedRankingStrategy strategy;

    @BeforeEach
    public void setup() {
        rankingService = new RankingService();
        strategy = new WeightedRankingStrategy();
    }

    @Test
    public void testValidRating() {
        assertDoesNotThrow(() -> DataValidator.validateRating(4.5));
    }

    @Test
    public void testInvalidRatingThrowsException() {
        assertThrows(InvalidRatingException.class, () -> DataValidator.validateRating(6.0));
        assertThrows(InvalidRatingException.class, () -> DataValidator.validateRating(-1.0));
    }

    @Test
    public void testInvalidPriceThrowsException() {
        assertThrows(InvalidPriceException.class, () -> DataValidator.validatePrice(-50.0));
        assertThrows(InvalidPriceException.class, () -> DataValidator.validatePrice(0.0));
    }

    @Test
    public void testInvalidDeliveryTimeThrowsException() {
        assertThrows(InvalidDeliveryTimeException.class, () -> DataValidator.validateDeliveryTime(0));
    }

    @Test
    public void testInvalidWeightsThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new WeightedRankingStrategy(50, 50, 10, 10)); // totals 120
    }

    @Test
    public void testNormalizationWithSameMinMax() {
        // If all restaurants have a delivery time of 30, normalization should return 100 to avoid division by zero
        double normalized = NormalizationUtil.normalizeLowerBetter(30.0, 30.0, 30.0);
        assertEquals(100.0, normalized);
    }

    @Test
    public void testRankingCalculationAndSorting() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1, "A", "Indian", "Bangalore", 4.0, 30, 200, 100)); // Average
        restaurants.add(new Restaurant(2, "B", "Indian", "Bangalore", 5.0, 10, 100, 500)); // Best
        restaurants.add(new Restaurant(3, "C", "Indian", "Bangalore", 2.0, 60, 500, 10));  // Worst

        List<RankingResult> results = rankingService.rankRestaurants(restaurants, strategy);

        assertEquals(3, results.size());
        assertEquals("B", results.get(0).getRestaurant().getName()); // B should be Rank 1
        assertEquals(1, results.get(0).getRank());
        
        assertEquals("A", results.get(1).getRestaurant().getName()); // A should be Rank 2
        assertEquals(2, results.get(1).getRank());
        
        assertEquals("C", results.get(2).getRestaurant().getName()); // C should be Rank 3
        assertEquals(3, results.get(2).getRank());
    }

    @Test
    public void testEmptyRestaurantList() {
        List<RankingResult> results = rankingService.rankRestaurants(new ArrayList<>(), strategy);
        assertTrue(results.isEmpty());
    }
}
