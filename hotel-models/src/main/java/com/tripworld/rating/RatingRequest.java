package com.tripworld.rating;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record RatingRequest(@Min(1)
                            @Max(5)
                            @NotNull
                            int value) {
}
