package test.java.rating;


import main.java.rating.AritaRating;
import org.junit.Assert;
import org.junit.Test;
public class AritaRatingTest {

        private final String INPUT = "123456";
        private String student="Bhagath";
    AritaRating aritaObj = new AritaRating();


    @Test
    public void testLoadStudent() {
        aritaObj.loadStudent(student);
    }

        @Test
        public void loadProp() {
           // aritaObj.loadData();
            Assert.assertEquals(6, aritaObj.sha256hex(INPUT).length());
        }

        @Test
        public void testHex() {
            String expected = ""+123456;
            Assert.assertEquals(expected, aritaObj.sha256hex(INPUT));
        }

}
