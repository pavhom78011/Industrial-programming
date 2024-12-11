import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class factorialTest {

    @org.junit.jupiter.api.Test
    void calculateFactorials() {
        ArrayList<Integer> factorials1 = new ArrayList<>(Arrays.asList(1));
        ArrayList<Integer> factorials5 = new ArrayList<>(Arrays.asList(1, 1, 2, 6, 24));
        ArrayList<Integer> factorials8 = new ArrayList<>(Arrays.asList(1, 1, 2, 6, 24, 120, 720, 5040));
        assertEquals(factorials1, factorial.CalculateFactorials(1));
        assertEquals(factorials5, factorial.CalculateFactorials(5));
        assertEquals(factorials8, factorial.CalculateFactorials(8));
    }

}