package util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class PaginatorTest {

    @Test
    public void getPage() {
        //GIVEN
        List<String> testList = Arrays.asList("AAA", "BBB", "CCC","DDD","FFF");
        //WHEN
        Paginator.ofStream(testList.stream(), 2, 2).ifPresent(
                list -> {
                    //THEN
                    List<String> result = list.getPage();
                    assertEquals(2L, result.size());
                    assertEquals("CCC", result.get(0));
                    assertEquals("DDD", result.get(1));
                }
        );
        //GIVEN
        List<String> evenList = Arrays.asList("111","222", "333", "444");
        //WHEN
        Optional<Paginator> testPaginator = Paginator.ofStream(evenList.stream(), 2, 1);
        //THEN
        assertNotNull(testPaginator.get());
        testPaginator.map(op -> op.getPage()).ifPresent(
                list -> {
                    assertEquals("111", list.get(0));
                    assertEquals("222", list.get(1));
                }
        );
        testPaginator.map(op -> op.count()).ifPresent(
                count-> {
                    assertEquals(2 , count.intValue());
                }
                );

        Optional<Paginator> wrongPaginator = Paginator.ofStream(evenList.stream(), 2, 5);
        assertFalse(wrongPaginator.isPresent());

    }
}