package com.rankbite.ranking;

import com.rankbite.model.Restaurant;

public class RankingResult {
    private int rank;
    private Restaurant restaurant;
    private double ratingScore;
    private double deliveryScore;
    private double priceScore;
    private double demandScore;
    private double finalScore;

    public RankingResult(Restaurant restaurant, double ratingScore, double deliveryScore, 
                         double priceScore, double demandScore, double finalScore) {
        this.restaurant = restaurant;
        this.ratingScore = ratingScore;
        this.deliveryScore = deliveryScore;
        this.priceScore = priceScore;
        this.demandScore = demandScore;
        this.finalScore = finalScore;
    }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public Restaurant getRestaurant() { return restaurant; }
    public double getRatingScore() { return ratingScore; }
    public double getDeliveryScore() { return deliveryScore; }
    public double getPriceScore() { return priceScore; }
    public double getDemandScore() { return demandScore; }
    public double getFinalScore() { return finalScore; }
}
