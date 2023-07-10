package com.farenda.javax.validation;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class ConstrainedBean {

    @AssertTrue
    public Boolean strategic;

    @AssertFalse
    public Boolean withDebts;

    @NotNull
    public String title;

    @Null
    public String translatedTitle;

    @Past // accepts also Calendar
    public Date publicationDate;

    @Future // accepts also Calendar
    public Date nextReleaseDate;

    @Pattern(regexp = "\\d{2}-\\d{3}")
    public String postalCode;

    @DecimalMin("9.99")
    public BigDecimal minPrice;

    @DecimalMax("20.1234")
    public BigDecimal maxPrice;

    @Min(100)
    public int happyCustomers;

    @Max(1)
    public int complaints;

    // specifies max digits:
    @Digits(integer = 1, fraction = 2)
    public BigDecimal price;

    @Size(min = 1, max = 2)
    public Set<String> names;

    @Min(0)
    @Max(150)
    public int passengers;
}
