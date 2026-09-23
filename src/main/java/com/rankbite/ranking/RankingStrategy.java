package com.rankbite.ranking;

import com.rankbite.model.Restaurant;

public interface RankingStrategy {
    double calculateRatingScore(Restaurant restaurant);
    double calculateDeliveryScore(Restaurant restaurant, RankingData data);
    double calculatePriceScore(Restaurant restaurant, RankingData data);
    double calculateDemandScore(Restaurant restaurant, RankingData data);
    double calculateFinalScore(Restaurant restaurant, RankingData data);
}
