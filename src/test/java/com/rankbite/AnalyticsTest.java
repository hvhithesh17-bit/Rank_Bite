package com.rankbite;

import com.rankbite.service.AnalyticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyticsTest {

    private AnalyticsService analyticsService;

    @BeforeEach
    public void setup() {
        analyticsService = new AnalyticsService();
    }

    @Test
    public void testCalculatePercentileEmptyList() {
        double result = analyticsService.calculatePercentile(new ArrayList<>(), 50);
        assertEquals(0.0, result);
    }

    @Test
    public void testCalculatePercentileSingleValue() {
        List<Integer> list = Collections.singletonList(100);
        double result = analyticsService.calculatePercentile(list, 90);
        assertEquals(100.0, result);
    }

    @Test
    public void testCalculatePercentileNormalList() {
        // Values: 10, 20, 30, 40, 50
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        
        // 50th percentile => index = ceil(0.5 * 5) - 1 = ceil(2.5) - 1 = 3 - 1 = 2 (value: 30)
        assertEquals(30.0, analyticsService.calculatePercentile(list, 50));
        
        // 90th percentile => index = ceil(0.9 * 5) - 1 = ceil(4.5) - 1 = 5 - 1 = 4 (value: 50)
        assertEquals(50.0, analyticsService.calculatePercentile(list, 90));
        
        // 10th percentile => index = ceil(0.1 * 5) - 1 = ceil(0.5) - 1 = 1 - 1 = 0 (value: 10)
        assertEquals(10.0, analyticsService.calculatePercentile(list, 10));
    }
}
