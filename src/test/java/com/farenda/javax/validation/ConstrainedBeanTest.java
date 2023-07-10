package com.farenda.javax.validation;

import com.google.common.collect.Sets;
import jakarta.validation.ConstraintViolation;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import static com.farenda.javax.validation.Bean.validator;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConstrainedBeanTest {

    private boolean isValid(String propertyName, Object value) {
        Set<ConstraintViolation<ConstrainedBean>> violations = validator.validateValue(
                ConstrainedBean.class, propertyName, value);
        if (violations.size() == 0) {
            return true;
        }
        violations.forEach(v -> System.out.printf("%s = %s: %s%n",
                v.getPropertyPath(), value, v.getMessage()));
        return false;
    }

    @Test
    public void checkAssertTrue() {
        assertTrue(isValid("strategic", true));
        assertTrue(isValid("strategic", null));
        assertFalse(isValid("strategic", false));
    }

    @Test
    public void checkAssertFalse() {
        assertFalse(isValid("withDebts", true));
        assertTrue(isValid("withDebts", null));
        assertTrue(isValid("withDebts", false));
    }

    @Test
    public void checkNotNull() {
        assertTrue(isValid("title", ""));
        assertTrue(isValid("title", "Catch 22"));
        assertFalse(isValid("title", null));
    }

    @Test
    public void checkNull() {
        assertFalse(isValid("translatedTitle", ""));
        assertFalse(isValid("translatedTitle", "Paragraf 22"));
        assertTrue(isValid("translatedTitle", null));
    }

    @Test
    public void checkPast() {
        assertTrue(isValid("publicationDate", new Date(1)));
        assertTrue(isValid("publicationDate", null));
        long in10Secs = System.currentTimeMillis()
                + SECONDS.toMillis(10);
        assertFalse(isValid("publicationDate", new Date(in10Secs)));
    }

    @Test
    public void checkFuture() {
        assertFalse(isValid("nextReleaseDate", new Date(1)));
        assertTrue(isValid("nextReleaseDate", null));
        long in10Secs = System.currentTimeMillis()
                + SECONDS.toMillis(10);
        assertTrue(isValid("nextReleaseDate", new Date(in10Secs)));
    }

    @Test
    public void checkPattern() {
        assertTrue(isValid("postalCode", "12-345"));
        assertTrue(isValid("postalCode", null));
        assertFalse(isValid("postalCode", ""));
        assertFalse(isValid("postalCode", "ab-345"));
        assertFalse(isValid("postalCode", "12345"));
        assertFalse(isValid("postalCode", "123-456"));
    }

    @Test
    public void checkDecimalMin() {
        assertTrue(isValid("minPrice", null));
        assertTrue(isValid("minPrice", new BigDecimal(10)));
        assertFalse(isValid("minPrice", new BigDecimal("9.98")));
    }

    @Test
    public void checkDecimalMax() {
        assertTrue(isValid("maxPrice", null));
        assertTrue(isValid("maxPrice", new BigDecimal(20)));
        assertFalse(isValid("maxPrice", new BigDecimal(42)));
    }

    @Test
    public void checkMin() {
        assertTrue(isValid("happyCustomers", null));
        assertTrue(isValid("happyCustomers", 101));
        assertFalse(isValid("happyCustomers", 20));
    }

    @Test
    public void checkMax() {
        assertTrue(isValid("complaints", null));
        assertTrue(isValid("complaints", 1));
        assertFalse(isValid("complaints", 2));
    }

    @Test
    public void checkDigits() {
        assertTrue(isValid("price", null));
        assertTrue(isValid("price", 2));
        assertTrue(isValid("price", 9.99));
        assertFalse(isValid("price", 1.955));
        assertFalse(isValid("price", 10));
    }

    @Test
    public void checkSize() {
        assertTrue(isValid("names", null));
        assertFalse(isValid("names", Sets.newHashSet()));
        assertTrue(isValid("names", Sets.newHashSet("a")));
        assertTrue(isValid("names", Sets.newHashSet("a", "b")));
        assertFalse(isValid("names", Sets.newHashSet("a", "b", "c")));
    }
}