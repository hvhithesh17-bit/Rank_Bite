package com.rankbite.service;

import com.rankbite.model.Restaurant;
import com.rankbite.ranking.RankingData;
import com.rankbite.ranking.RankingResult;
import com.rankbite.ranking.RankingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingService {

    public List<RankingResult> rankRestaurants(List<Restaurant> restaurants, RankingStrategy strategy) {
        if (restaurants == null || restaurants.isEmpty()) {
            return new ArrayList<>();
        }

        RankingData rankingData = new RankingData();
        rankingData.calculateMinMax(restaurants);

        List<RankingResult> results = new ArrayList<>();

        for (Restaurant r : restaurants) {
            double ratingScore = strategy.calculateRatingScore(r);
            double deliveryScore = strategy.calculateDeliveryScore(r, rankingData);
            double priceScore = strategy.calculatePriceScore(r, rankingData);
            double demandScore = strategy.calculateDemandScore(r, rankingData);
            double finalScore = strategy.calculateFinalScore(r, rankingData);

            RankingResult result = new RankingResult(r, ratingScore, deliveryScore, 
                                                     priceScore, demandScore, finalScore);
            results.add(result);
        }

        // Sort descending by final score
        Collections.sort(results, new Comparator<RankingResult>() {
            @Override
            public int compare(RankingResult r1, RankingResult r2) {
                return Double.compare(r2.getFinalScore(), r1.getFinalScore());
            }
        });

        // Assign ranks
        int rank = 1;
        for (RankingResult result : results) {
            result.setRank(rank++);
        }

        return results;
    }
}
