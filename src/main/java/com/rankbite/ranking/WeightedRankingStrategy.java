package com.rankbite.ranking;

import com.rankbite.model.Restaurant;
import com.rankbite.util.NormalizationUtil;

public class WeightedRankingStrategy implements RankingStrategy {
    private double ratingWeight;
    private double deliveryWeight;
    private double priceWeight;
    private double demandWeight;

    public WeightedRankingStrategy() {
        // Default weights
        this(40, 25, 15, 20);
    }

    public WeightedRankingStrategy(double ratingWeight, double deliveryWeight, 
                                   double priceWeight, double demandWeight) {
        double total = ratingWeight + deliveryWeight + priceWeight + demandWeight;
        if (Math.abs(total - 100.0) > 0.001) {
            throw new IllegalArgumentException("Weights must total 100%");
        }
        this.ratingWeight = ratingWeight / 100.0;
        this.deliveryWeight = deliveryWeight / 100.0;
        this.priceWeight = priceWeight / 100.0;
        this.demandWeight = demandWeight / 100.0;
    }

    @Override
    public double calculateRatingScore(Restaurant restaurant) {
        return (restaurant.getRating() / 5.0) * 100.0;
    }

    @Override
    public double calculateDeliveryScore(Restaurant restaurant, RankingData data) {
        return NormalizationUtil.normalizeLowerBetter(restaurant.getAverageDeliveryTime(), 
                                                      data.getMinDeliveryTime(), 
                                                      data.getMaxDeliveryTime());
    }

    @Override
    public double calculatePriceScore(Restaurant restaurant, RankingData data) {
        return NormalizationUtil.normalizeLowerBetter(restaurant.getAveragePrice(), 
                                                      data.getMinPrice(), 
                                                      data.getMaxPrice());
    }

    @Override
    public double calculateDemandScore(Restaurant restaurant, RankingData data) {
        return NormalizationUtil.normalizeHigherBetter(restaurant.getOrderCount(), 
                                                       data.getMinDemand(), 
                                                       data.getMaxDemand());
    }

    @Override
    public double calculateFinalScore(Restaurant restaurant, RankingData data) {
        double ratingScore = calculateRatingScore(restaurant) * ratingWeight;
        double deliveryScore = calculateDeliveryScore(restaurant, data) * deliveryWeight;
        double priceScore = calculatePriceScore(restaurant, data) * priceWeight;
        double demandScore = calculateDemandScore(restaurant, data) * demandWeight;
        
        return ratingScore + deliveryScore + priceScore + demandScore;
    }
}
