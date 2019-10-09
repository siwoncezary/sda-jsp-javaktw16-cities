package util;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Paginator {
    private final int sizeCollection;
    private final int pageSize;
    private final int currentPage;

    private Paginator(int sizeCollection, int current, int sizePage) {
        this.sizeCollection = sizeCollection;
        this.pageSize = sizePage;
        this.currentPage = current;
    }

    public static Optional<Paginator> of(int size, int page, int pageSize){
        if (isValid(size, page, pageSize)) {
            return Optional.of(new Paginator(size, page, pageSize));
        }
        return Optional.empty();
    }

    public int getPageSize() {
        return pageSize;
    }

    public Optional<Integer> next(){
        return currentPage + 1 <= count() ? Optional.ofNullable(currentPage + 1) : Optional.empty();
    }

    public Optional<Integer> prev(){
        return currentPage -1 > 0 ? Optional.ofNullable(currentPage - 1) : Optional.empty();
    }

    public int count(){
        return sizeCollection / pageSize + (sizeCollection % pageSize == 0 ? 0 : 1);
    }

    public <T> List<T> getPaged(@NotNull Stream<T> stream){
        int page  = Integer.max(1, currentPage);
        page = Integer.min(page, count());
        return stream.skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    private static int clip(int size, int page, int pageSize){
        return Integer.min(Integer.max(1, page) , size / pageSize + (size % pageSize == 0 ? 0 : 1));
    }

    private static boolean isValid(int size, int page, int pageSize){
        return clip(size, page, pageSize) == page;
    }
}