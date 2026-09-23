package com.rankbite.util;

import com.rankbite.exception.InvalidDeliveryTimeException;
import com.rankbite.exception.InvalidPriceException;
import com.rankbite.exception.InvalidRatingException;

public class DataValidator {

    public static void validateRating(double rating) throws InvalidRatingException {
        if (rating < 0.0 || rating > 5.0) {
            throw new InvalidRatingException("Rating must be between 0 and 5. Received: " + rating);
        }
    }

    public static void validatePrice(double price) throws InvalidPriceException {
        if (price <= 0.0) {
            throw new InvalidPriceException("Price must be greater than 0. Received: " + price);
        }
    }

    public static void validateDeliveryTime(int time) throws InvalidDeliveryTimeException {
        if (time <= 0) {
            throw new InvalidDeliveryTimeException("Delivery time must be greater than 0. Received: " + time);
        }
    }
}
