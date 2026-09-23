package com.rankbite.ranking;

import com.rankbite.model.Restaurant;

public class RatingOnlyStrategy implements RankingStrategy {

    @Override
    public double calculateRatingScore(Restaurant restaurant) {
        return (restaurant.getRating() / 5.0) * 100.0;
    }

    @Override
    public double calculateDeliveryScore(Restaurant restaurant, RankingData data) {
        return 0;
    }

    @Override
    public double calculatePriceScore(Restaurant restaurant, RankingData data) {
        return 0;
    }

    @Override
    public double calculateDemandScore(Restaurant restaurant, RankingData data) {
        return 0;
    }

    @Override
    public double calculateFinalScore(Restaurant restaurant, RankingData data) {
        return calculateRatingScore(restaurant);
    }
}
